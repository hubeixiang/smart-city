package com.sct.application.business.controller;

import com.sct.application.business.service.business.party.PartyOrganizationsLifeServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScPartyActivitiesCondition;
import com.sct.service.database.condition.ScPartyActivitiesConditionExport;
import com.sct.service.database.condition.ScPartyTopicCondition;
import com.sct.service.database.condition.ScPartyTopicConditionExport;
import com.sct.service.database.entity.ScPartyActivities;
import com.sct.service.database.entity.ScPartyTopic;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(tags = "社区党建->组织生活")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/party/organization/life", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/part/organization/life",})
@RestController
public class PartyOrganizationsLifeController {
    @Autowired
    private PartyOrganizationsLifeServiceImpl partyOrganizationsLifeServiceImpl;

    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("ok");
    }

    /**
     * 概览查询
     *
     * @param condition
     * @return
     */
    @ApiOperation("主题概览")
    @PostMapping("/overview")
    public ResultVOEntity list(@RequestBody @ApiParam(value = "查询条件") ScPartyTopicCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        ResultVOEntity result = partyOrganizationsLifeServiceImpl.overview(condition);
        return result;
    }

    /**
     * 分页查询
     *
     * @param paging
     * @param condition
     * @return
     */
    @ApiOperation("分页查询主题")
    @PostMapping("/page")
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @RequestBody @ApiParam(value = "查询条件") ScPartyTopicCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = partyOrganizationsLifeServiceImpl.listPage(paging, condition);
        return result;
    }

    /**
     * 全部查询
     *
     * @param condition
     * @return
     */
    @ApiOperation("全部查询主题")
    @PostMapping("/all")
    public ResultVOEntity listAll(@RequestBody @ApiParam(value = "查询条件") ScPartyTopicCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return partyOrganizationsLifeServiceImpl.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出主题")
    @PostMapping("/export")
    public FileLocation export(@RequestBody @ApiParam(value = "导出条件") ScPartyTopicConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = partyOrganizationsLifeServiceImpl.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增主题")
    @PostMapping
    public ScPartyTopic create(@RequestBody @ApiParam(value = "主题信息", required = true) ScPartyTopic body) {
        ScPartyTopic add = partyOrganizationsLifeServiceImpl.create(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改主题")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value = "主题id", required = true) Integer id, @RequestBody @ApiParam(value = "党组织信息", required = true) ScPartyTopic body) {
        Assert.notNull(body, "Require body");
        body.setId(id);
        int delete = partyOrganizationsLifeServiceImpl.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }

    @ApiOperation("删除主题")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "主题id", required = true) Integer id) {
        int delete = partyOrganizationsLifeServiceImpl.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("批量删除主题")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody @ApiParam(value = "", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = partyOrganizationsLifeServiceImpl.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    /**
     * 分页查询
     *
     * @param paging
     * @param condition
     * @return
     */
    @ApiOperation("分页查询活动")
    @PostMapping("/act/page")
    public PageResultVO listAct(@ApiParam(value = "分页请求") PageRecord paging, @RequestBody @ApiParam(value = "查询条件") ScPartyActivitiesCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = partyOrganizationsLifeServiceImpl.listPage(paging, condition);
        return result;
    }

    /**
     * 全部查询
     *
     * @param condition
     * @return
     */
    @ApiOperation("全部查询活动")
    @PostMapping("/act/all")
    public ResultVOEntity listAllAct(@RequestBody @ApiParam(value = "查询条件") ScPartyActivitiesCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return partyOrganizationsLifeServiceImpl.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出活动")
    @PostMapping("/act/export")
    public FileLocation exportAct(@RequestBody @ApiParam(value = "导出条件") ScPartyActivitiesConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = partyOrganizationsLifeServiceImpl.export2FileLocationActivities(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增活动")
    @PostMapping("/{topicId}/act/")
    public ScPartyActivities createAct(@PathVariable("topicId") @ApiParam(value = "主题ID", required = true) Integer topicId,
                                       @RequestBody @ApiParam(value = "党组织信息", required = true) ScPartyActivities body) {
        Assert.notNull(body, "Require body");
        Assert.notNull(body.getTitle(), "Require Title");
        body.setTopicId(topicId);
        ScPartyActivities add = partyOrganizationsLifeServiceImpl.create(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改活动")
    @PatchMapping("/{topicId}/act/{id}")
    public EmptyResourceResponse updateAct(@PathVariable("topicId") @ApiParam(value = "主题ID", required = true) Integer topicId,
                                           @PathVariable("id") @ApiParam(value = "党组织id", required = true) Integer id,
                                           @RequestBody @ApiParam(value = "活动信息", required = true) ScPartyActivities body) {
        Assert.notNull(body, "Require body");
        body.setTopicId(topicId);
        body.setId(id);
        int delete = partyOrganizationsLifeServiceImpl.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }

    @ApiOperation("删除活动")
    @DeleteMapping("/{topicId}/act/{id}")
    public EmptyResourceResponse deleteAct(@PathVariable("topicId") @ApiParam(value = "主题ID", required = true) Integer topicId,
                                           @PathVariable("id") @ApiParam(value = "党组织id", required = true) Integer id) {
        int delete = partyOrganizationsLifeServiceImpl.deleteAct(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("批量删除活动")
    @DeleteMapping("/{topicId}/act")
    public EmptyResourceResponse deleteAct(@PathVariable("topicId") @ApiParam(value = "主题ID", required = true) Integer topicId, @RequestBody @ApiParam(value = "", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = partyOrganizationsLifeServiceImpl.deleteAct(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

}
