package com.sct.application.business.controller;

import com.sct.application.business.dto.ScPartyMemberDocumentary;
import com.sct.application.business.service.business.party.PartyMemberServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScPartyMemberCondition;
import com.sct.service.database.condition.ScPartyMemberConditionExport;
import com.sct.service.database.entity.ScPartyMember;
import com.sct.service.database.entity.ScPartyMemberRecord;
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

@Api(tags = "社区党建->党员管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/party/member", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/part/member",})
@RestController
public class PartyMemberController {

    @Autowired
    private PartyMemberServiceImpl partyMemberServiceImpl;

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
    public PageResultVO list(PageRecord paging, ScPartyMemberCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = partyMemberServiceImpl.listPage(paging, condition);
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
    public ResultVOEntity listAll(ScPartyMemberCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return partyMemberServiceImpl.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出")
    @PostMapping("/export")
    public FileLocation export(@RequestBody ScPartyMemberConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = partyMemberServiceImpl.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增")
    @PostMapping
    public ScPartyMember create(@RequestBody ScPartyMember body) {
        Assert.notNull(body, "Require body");
        Assert.notNull(body.getCardId(), "Require CardId");
        ScPartyMember add = partyMemberServiceImpl.create(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") Integer id, @RequestBody ScPartyMember body) {
        Assert.notNull(body, "Require body");
        body.setId(id);
        int delete = partyMemberServiceImpl.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }


    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") Integer id) {
        int delete = partyMemberServiceImpl.delete(id);
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
        int delete = partyMemberServiceImpl.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("党员发展全纪实信息")
    @GetMapping("/detail/{id}")
    public ScPartyMemberDocumentary detail(@PathVariable("id") Integer id) {
        return partyMemberServiceImpl.detail(id);
    }

    @ApiOperation("新增纪实")
    @PostMapping("/{partyMemberId}/record")
    public ScPartyMemberRecord create(@PathVariable("partyMemberId") Integer partyMemberId, @RequestBody ScPartyMemberRecord body) {
        body.setPartyMemberId(partyMemberId);
        ScPartyMemberRecord add = partyMemberServiceImpl.createRecord(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改纪实")
    @PatchMapping("/{partyMemberId}/record/{id}")
    public EmptyResourceResponse update(@PathVariable("partyMemberId") Integer partyMemberId, @PathVariable("id") Integer id, @RequestBody ScPartyMemberRecord body) {
        Assert.notNull(body, "Require body");
        body.setId(id);
        body.setPartyMemberId(partyMemberId);
        int delete = partyMemberServiceImpl.updateRecord(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }


    @ApiOperation("删除纪实")
    @DeleteMapping("/{partyMemberId}/record/{id}")
    public EmptyResourceResponse delete(@PathVariable("partyMemberId") Integer partyMemberId, @PathVariable("id") Integer id) {
        int delete = partyMemberServiceImpl.deleteRecord(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("批量删除纪实")
    @DeleteMapping("/{partyMemberId}/record")
    public EmptyResourceResponse delete(@PathVariable("partyMemberId") Integer partyMemberId, @RequestBody List<Integer> ids) {
        Assert.notNull(ids, "Require body");
        int size = ids == null ? 0 : ids.size();
        int delete = partyMemberServiceImpl.deleteRecord(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }
}
