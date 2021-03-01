package com.sct.application.business.controller;


import com.sct.application.business.dto.ScGridAll;
import com.sct.application.business.service.business.grid.GridGridServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScGridCondition;
import com.sct.service.database.condition.ScGridEvaluationCondition;
import com.sct.service.database.condition.ScGridManagerCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridEvaluation;
import com.sct.service.database.entity.ScGridManager;
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
 * 网格化管理->网格管理
 */
@Api(tags = "网格化管理->网格管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/grid", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/grid",})
@RestController
public class GridGridController {

    @Autowired
    private GridGridServiceImpl gridGridService;

    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->网格管理");
    }

    @ApiOperation("分页查询网格")
    @GetMapping("/page")
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridGridService.listPage(paging, condition);
    }

    @ApiOperation("全部网格查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridGridService.list(condition);
    }

    @ApiOperation("查看网格详情")
    @GetMapping("/detail/{id}")
    public ScGridAll detail(@PathVariable("id") @ApiParam(value = "网格id", required = true) Integer id) {
        ScGridAll select = gridGridService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("新增网格")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "网格信息", required = true) ScGrid body) {
        int add = gridGridService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改网格")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value = "网格id", required = true) Integer id, @RequestBody @ApiParam(value = "网格信息", required = true) ScGrid body) {
        body.setId(id);
        int update = gridGridService.update(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("删除网格")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "网格id", required = true) Integer id) {
        int delete = gridGridService.delete(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("批量删除网格")
    @DeleteMapping
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "网格id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridGridService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }

    @ApiOperation("启用和停用网格")
    @PatchMapping("/validStatus/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value = "网格id", required = true) Integer id, @RequestParam @ApiParam(value = "1启用，0禁用", required = true) Integer validStatus) {
        int update = gridGridService.updateValidStatus(id, validStatus);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("通过社区id获取网格下拉列表")
    @GetMapping("/listGridsByCommunityId")
    public ResultVOEntity listGridsByCommunityId(@ApiParam(value = "社区id", required = true) Integer communityId) {
        return gridGridService.listGridsByCommunityId(communityId);
    }


    @ApiOperation("分页查询网格员")
    @GetMapping("/manager/page")
    public PageResultVO listManager(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScGridManagerCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridGridService.listGridManagerPage(paging, condition);
    }

    @ApiOperation("新增网格员")
    @PostMapping("/manager")
    public EmptyResourceResponse createManager(@RequestBody @ApiParam(value = "网格员信息", required = true) ScGridManager body) {
        Assert.notNull(body.getGridId(), "Require grid id");
        int add = gridGridService.createManager(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改网格员")
    @PatchMapping("/manager/{id}")
    public EmptyResourceResponse updateManager(@PathVariable("id") @ApiParam(value = "网格员id", required = true) Integer id, @RequestBody @ApiParam(value = "网格员信息", required = true) ScGridManager body) {
        Assert.notNull(body.getId(), "Require grid manager id");
        body.setId(id);
        int update = gridGridService.updateManager(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("删除网格员")
    @DeleteMapping("/manager/{id}")
    public EmptyResourceResponse deleteManager(@PathVariable("id") @ApiParam(value = "网格员id", required = true) Integer id) {
        int delete = gridGridService.deleteManager(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("批量删除网格员")
    @DeleteMapping("/manager")
    public EmptyResourceResponse batchDeleteManager(@RequestBody @ApiParam(value = "网格员id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridGridService.deleteManager(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }

    @ApiOperation("分页查询网格员考评信息")
    @GetMapping("/evaluation/page")
    public PageResultVO listEvaluation(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScGridEvaluationCondition condition) {
        return gridGridService.listGridEvaluationPage(paging, condition);
    }

    @ApiOperation("新增考评信息")
    @PostMapping("/evaluation")
    public EmptyResourceResponse createEvaluation(@RequestBody @ApiParam(value = "考评信息", required = true) ScGridEvaluation body) {
        int add = gridGridService.createEvaluation(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改考评信息")
    @PatchMapping("/evaluation/{gridId}/{gridManagerId}")
    public EmptyResourceResponse updateEvaluation(@PathVariable("gridId") @ApiParam(value = "网格id", required = true) Integer gridId, @PathVariable("gridManagerId") @ApiParam(value = "网格员id", required = true) Integer gridManagerId, @RequestBody @ApiParam(value = "考评信息", required = true) ScGridEvaluation body) {
        body.setGridId(gridId);
        body.setGridManagerId(gridManagerId);
        int update = gridGridService.updateEvaluation(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：gridId[%s] gridManagerId[%s]不存在", gridId, gridManagerId));
        }
    }

    @ApiOperation("删除考评信息")
    @DeleteMapping("/evaluation/{gridId}/{gridManagerId}")
    public EmptyResourceResponse deleteEvaluation(@PathVariable("gridId") @ApiParam(value = "网格id", required = true) Integer gridId, @PathVariable("gridManagerId") @ApiParam(value = "网格员id", required = true) Integer gridManagerId) {
        int delete = gridGridService.deleteEvaluation(gridId, gridManagerId);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败，未删除任何数据, 可能原因：gridId[%s] gridManagerId[%s]不存在", gridId, gridManagerId));
        }
    }

    @ApiOperation("批量删除考评信息")
    @DeleteMapping("/evaluation")
    public EmptyResourceResponse batchDeleteEvaluation(@RequestParam("gridId") @ApiParam(value = "网格id", required = true) Integer gridId, @RequestParam("gridManagerIds") @ApiParam(value = "网格员id列表", required = true) List<Integer> gridManagerIds) {
        int size = gridManagerIds == null ? 0 : gridManagerIds.size();
        int delete = gridGridService.deleteEvaluation(gridId, gridManagerIds);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }
}
