package com.sct.application.business.controller;


import com.sct.application.business.service.business.grid.GridHouseServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScHouseCondition;
import com.sct.service.database.entity.ScHouse;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网格化管理->房屋管理
 */
@Api(tags = "网格化管理->房屋管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/house", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/house",})
@RestController
public class GridHouseController {

    @Autowired
    private GridHouseServiceImpl gridHouseService;

    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->房屋管理");
    }

    @ApiOperation("分页查询房屋")
    @GetMapping("/page")
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScHouseCondition condition) {
        condition.checkSQLinjectionException(condition.getBuildingIdExt());
        condition.checkSQLinjectionException(condition.getOwnerIdExt());
        condition.checkSQLinjectionException(condition.getUnit());
        return gridHouseService.listPage(paging, condition);
    }

    @ApiOperation("全部房屋查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScHouseCondition condition) {
        condition.checkSQLinjectionException(condition.getBuildingIdExt());
        condition.checkSQLinjectionException(condition.getOwnerIdExt());
        condition.checkSQLinjectionException(condition.getUnit());
        return gridHouseService.list(condition);
    }

    @ApiOperation("查看房屋详情")
    @GetMapping("/detail/{id}")
    public ScHouse detail(@PathVariable("id") @ApiParam(value = "房屋id", required = true) Integer id) {
        ScHouse select = gridHouseService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("新增房屋")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "房屋信息", required = true) ScHouse body) {
        int add = gridHouseService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改房屋")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value="房屋id",required=true) Integer id, @RequestBody @ApiParam(value = "房屋信息") ScHouse body) {
        body.setId(id);
        int update = gridHouseService.update(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("删除房屋")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "房屋id", required = true) Integer id) {
        int delete = gridHouseService.delete(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在",id));
        }
    }

    @ApiOperation("批量删除房屋")
    @DeleteMapping
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "房屋id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridHouseService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }
}
