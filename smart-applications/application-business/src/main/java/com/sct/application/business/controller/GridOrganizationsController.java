package com.sct.application.business.controller;


import com.sct.application.business.service.business.grid.GridOrganizationsServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScOrganizationCondition;
import com.sct.service.database.condition.ScOrganizationConditionExport;
import com.sct.service.database.entity.ScOrganization;
import com.sct.service.main.ScOrganizationImpl;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 网格化管理->组织管理
 */
@Api(tags = "网格化管理->组织管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/org", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/org",})
@RestController
public class GridOrganizationsController {

    @Autowired
    private GridOrganizationsServiceImpl gridOrganizationsService;

    @Autowired
    private ScOrganizationImpl scOrganizationImpl;

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
    public PageResultVO list(PageRecord paging, ScOrganizationCondition condition) {
        ScOrganizationCondition.checkSQLinjectionException(condition);
        PageResultVO result = scOrganizationImpl.listPage(paging, condition);
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
    public ResultVOEntity listAll(ScOrganizationCondition condition) {
        ScOrganizationCondition.checkSQLinjectionException(condition);
        return scOrganizationImpl.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出")
    @PostMapping("/export")
    public FileLocation export(@RequestBody ScOrganizationConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        Assert.notNull(condition.getCondition(), "Require Query condition");
        Assert.notNull(condition.getExportCondition(), "Require Export Column header field");
        condition.checkParam(condition);
        FileLocation fileLocation = scOrganizationImpl.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增")
    @PostMapping
    public ScOrganization create(@RequestBody ScOrganization body) {
        ScOrganization add = gridOrganizationsService.create(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") Integer id, @RequestBody ScOrganization body) {
        Assert.notNull(body, "Require body");
        body.setId(id);
        int delete = gridOrganizationsService.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }

    @ApiOperation("注销")
    @PatchMapping("/{id}/cancel")
    public EmptyResourceResponse cancel(@PathVariable("id") Integer id) {
        int delete = gridOrganizationsService.cancel(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") Integer id) {
        int delete = gridOrganizationsService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridOrganizationsService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }
}
