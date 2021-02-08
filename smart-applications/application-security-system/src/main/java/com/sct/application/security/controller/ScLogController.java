package com.sct.application.security.controller;

import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScLogCondition;
import com.sct.service.database.condition.ScLogConditionExport;
import com.sct.service.database.entity.ScLog;
import com.sct.service.main.ScLogServiceImpl;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "日志管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/log", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/log",})
@RestController
public class ScLogController extends BaseController {


    @Autowired
    private ScLogServiceImpl scLogServiceImpl;


    /**
     * 页面打开需要初始化的相关信息
     *
     * @return
     */
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("ok");
    }

    /**
     * 分页查询
     *
     * @param paging
     * @param condition
     * @return
     */
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public PageResultVO list(PageRecord paging, ScLogCondition condition) {
        ScLogCondition.checkParam(condition);
        PageResultVO result = scLogServiceImpl.listPage(paging, condition);
        return result;
    }

    /**
     * 全部查询
     *
     * @param condition
     * @return
     */
    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(ScLogCondition condition) {
        ScLogCondition.checkParam(condition);
        return scLogServiceImpl.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出")
    @PostMapping("/export")
    public FileLocation export(@RequestBody ScLogConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        Assert.notNull(condition.getCondition(), "Require Query condition");
        Assert.notNull(condition.getExportCondition(), "Require Export Column header field");
        ScLogConditionExport.checkParam(condition);
        FileLocation fileLocation = scLogServiceImpl.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增日志")
    @PostMapping
    public ScLog create(@RequestBody ScLog body) {
        ScLog add = scLogServiceImpl.insert(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

}
