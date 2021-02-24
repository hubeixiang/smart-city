package com.sct.application.business.controller;


import com.sct.application.business.service.business.grid.GridBuildingServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScBuildingCondition;
import com.sct.service.database.entity.ScBuilding;
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
 * 网格化管理->房屋管理
 */
@Api(tags = "网格化管理->建筑管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/building", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/building",})
@RestController
public class GridBuildingController {

    @Autowired
    private GridBuildingServiceImpl gridBuildingService;

    /**
     * 页面打开需要初始化的相关信息
     *
     * @param model model
     * @return return
     */
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->建筑管理");
    }

    /**
     * 分页查询
     *
     * @param paging    paging
     * @param condition condition
     * @return return
     */
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScBuildingCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridBuildingService.listPage(paging, condition);
    }

    /**
     * 全部查询
     *
     * @param condition condition
     * @return return
     */
    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScBuildingCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridBuildingService.list(condition);
    }

    /**
     * 通过Id查询建筑
     *
     * @param id id
     * @return return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail/{id}")
    public ScBuilding detail(@PathVariable("id") @ApiParam(value = "建筑id", required = true) Integer id) {
        ScBuilding select = gridBuildingService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在",id));
        }
    }

    /**
     * 新增建筑
     *
     * @param body body
     * @return return
     */
    @ApiOperation("新增建筑")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "建筑信息", required = true) ScBuilding body) {
        int add = gridBuildingService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    /**
     * 修改建筑
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改建筑")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value="建筑id",required=true) Integer id, @RequestBody @ApiParam(value = "建筑信息") ScBuilding body) {
        body.setId(id);
        int update = gridBuildingService.update(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在",id));
        }
    }

    /**
     * 删除建筑
     *
     * @param id id
     * @return return
     */
    @ApiOperation("删除建筑")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "建筑id", required = true) Integer id) {
        int delete = gridBuildingService.delete(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在",id));
        }
    }

    /**
     * 批量删除建筑
     *
     * @param ids ids
     * @return return
     */
    @ApiOperation("批量删除建筑")
    @DeleteMapping
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "建筑id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridBuildingService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }
}
