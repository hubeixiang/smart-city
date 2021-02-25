package com.sct.application.business.controller;

import com.sct.application.business.dto.ScCommunityPartyAll;
import com.sct.application.business.service.business.party.PartyOrganizationsServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScCommunityLeaderCondition;
import com.sct.service.database.condition.ScCommunityPartyCondition;
import com.sct.service.database.condition.ScCommunityPartyConditionExport;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.database.entity.ScCommunityParty;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "社区党建->党组织管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/party/organization", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/part/organization",})
@RestController
public class PartyOrganizationsController {

    @Autowired
    private PartyOrganizationsServiceImpl partyOrganizationsService;

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
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScCommunityPartyCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = partyOrganizationsService.listPage(paging, condition);
        return result;
    }

    /**
     * 全部查询
     *
     * @param condition
     * @return
     */
    @ApiOperation("全部查询党组织")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScCommunityPartyCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return partyOrganizationsService.list(condition);
    }

    @ApiOperation("党组织详情+党组织领导班子详情查询")
    @GetMapping("/detail/{id}")
    public ScCommunityPartyAll partyOrganizationsDetail(@PathVariable("id") Integer id) {
        return partyOrganizationsService.partyOrganizationsDetail(id);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出党组织")
    @PostMapping("/export")
    public FileLocation export(@RequestBody @ApiParam(value = "导出条件") ScCommunityPartyConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = partyOrganizationsService.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("新增党组织")
    @PostMapping
    public ScCommunityParty create(@RequestBody @ApiParam(value = "党组织信息", required = true) ScCommunityParty body) {
        ScCommunityParty add = partyOrganizationsService.create(body);
        if (add != null && add.getId() != null) {
            return add;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改党组织")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value = "党组织id", required = true) Integer id, @RequestBody @ApiParam(value = "党组织信息", required = true) ScCommunityParty body) {
        Assert.notNull(body, "Require body");
        body.setId(id);
        int delete = partyOrganizationsService.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功.数量不对");
        }
    }


    @ApiOperation("删除党组织")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "党组织id", required = true) Integer id) {
        int delete = partyOrganizationsService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }

    @ApiOperation("批量删除党组织")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody @ApiParam(value = "党组织id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = partyOrganizationsService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败.数量不对");
        }
    }


    /**
     * 分页查询社区党组织领导班子
     *
     * @param paging    paging
     * @param condition condition
     * @return return
     */
    @ApiOperation("分页查询领导班子")
    @GetMapping("/leader/page")
    public PageResultVO listLeader(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScCommunityLeaderCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        PageResultVO result = partyOrganizationsService.listCommunityLeaderPage(paging, condition);
        return result;
    }

    @ApiOperation("领导详情")
    @GetMapping("/leader/detail/{id}")
    public ScCommunityLeader partyOrganizationsLeaderDetail(@PathVariable("id") @ApiParam(value = "领导id", required = true) Integer id) {
        ScCommunityLeader select = partyOrganizationsService.partyOrganizationsLeaderDetail(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("新增领导")
    @PostMapping("/leader")
    public EmptyResourceResponse createLeader(@RequestBody @ApiParam(value = "领导信息", required = true) ScCommunityLeader body) {
        Assert.notNull(body.getCommunityId(), "Require community id");
        int add = partyOrganizationsService.createLeader(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改领导")
    @PatchMapping("/leader/{id}")
    public EmptyResourceResponse updateLeader(@PathVariable("id") @ApiParam(value = "领导id", required = true) Integer id, @RequestBody @ApiParam(value = "领导信息", required = true) ScCommunityLeader body) {
        Assert.notNull(id, "Require community leader id");
        body.setId(id);
        int update = partyOrganizationsService.updateLeader(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("删除领导")
    @DeleteMapping("/leader/{id}")
    public EmptyResourceResponse deleteLeader(@PathVariable("id") @ApiParam(value = "领导id", required = true) Integer id) {
        int delete = partyOrganizationsService.deleteLeader(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("批量删除领导")
    @DeleteMapping("/leader")
    public EmptyResourceResponse batchDeleteLeader(@RequestBody @ApiParam(value = "领导id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = partyOrganizationsService.deleteLeader(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }

}
