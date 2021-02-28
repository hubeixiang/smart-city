package com.sct.application.business.controller;


import com.sct.application.business.service.business.grid.GridEstateServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScEstateCondition;
import com.sct.service.database.condition.ScPropertyStaffCondition;
import com.sct.service.database.entity.ScEstate;
import com.sct.service.database.entity.ScPropertyStaff;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网格化管理->小区管理
 */
@Api(tags = "网格化管理->小区管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/estate", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/estate",})
@RestController
public class GridEstateController {

    @Autowired
    private GridEstateServiceImpl gridEstateService;


    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->小区管理");
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScEstateCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.listPage(paging, condition);
    }

    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScEstateCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.list(condition);
    }

    @ApiOperation("查看详情")
    @GetMapping("/detail/{id}")
    public ScEstate detail(@PathVariable("id") @ApiParam(value = "小区id", required = true) Integer id) {
        ScEstate select = gridEstateService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("新增小区")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "小区信息", required = true) ScEstate body) {
        int add = gridEstateService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改小区")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value="小区id",required=true) Integer id, @RequestBody @ApiParam(value = "小区信息") ScEstate body) {
        body.setId(id);
        int update = gridEstateService.update(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("删除小区")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "小区id", required = true) Integer id) {
        int delete = gridEstateService.delete(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("批量删除小区")
    @DeleteMapping
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "小区id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }


    @ApiOperation("通过社区id获取小区下拉列表")
    @GetMapping("/listEstatesByCommunityId")
    public ResultVOEntity listEstatesByCommunityId(@ApiParam(value = "社区id", required = true) Integer communityId) {
        return gridEstateService.listEstatesByCommunityId(communityId);
    }


    @ApiOperation("分页查询物业人员")
    @GetMapping("/staff/page")
    public PageResultVO listStaff(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScPropertyStaffCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.listPropertyStaffPage(paging, condition);
    }

    @ApiOperation("新增物业人员")
    @PostMapping("/staff")
    public EmptyResourceResponse createStaff(@RequestBody @ApiParam(value = "物业人员信息", required = true) ScPropertyStaff body, @RequestParam("estateId") @ApiParam(value = "小区id", required = true) Integer estateId) {
        int add = gridEstateService.createStaff(body, estateId);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改物业人员")
    @PatchMapping("/staff/{id}")
    public EmptyResourceResponse updateStaff(@PathVariable("id") @ApiParam(value="物业人员id",required=true) Integer id, @RequestBody @ApiParam(value = "物业人员信息", required = true) ScPropertyStaff body) {
        body.setId(id);
        int update = gridEstateService.updateStaff(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("删除物业人员")
    @DeleteMapping("/staff/{id}")
    public EmptyResourceResponse deleteStaff(@PathVariable("id") @ApiParam(value = "物业人员id", required = true) Integer id, @RequestParam("estateId") @ApiParam(value = "小区id", required = true) Integer estateId) {
        int delete = gridEstateService.deleteStaff(id, estateId);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("批量删除物业人员")
    @DeleteMapping("/staff")
    public EmptyResourceResponse batchDeleteStaff(@RequestBody @ApiParam(value = "物业人员id列表", required = true) List<Integer> ids, @RequestParam("estateId") @ApiParam(value = "小区id", required = true) Integer estateId) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.deleteStaff(ids, estateId);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }
}
