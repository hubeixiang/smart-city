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
    private GridGridServiceImpl gridEstateService;

    /**
     * 页面打开需要初始化的相关信息
     *
     * @return return
     */
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("ok");
    }

    /**
     * 分页查询
     *
     * @param paging paging
     * @param condition condition
     * @return return
     */
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public PageResultVO list(@ApiParam(value="分页请求") PageRecord paging, @ApiParam(value="查询条件") ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.listPage(paging, condition);
    }

    /**
     * 全部查询
     *
     * @param condition condition
     * @return return
     */
    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value="查询条件") ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.list(condition);
    }

    /**
     * 通过Id查询网格
     *
     * @param id id
     * @return return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail")
    public ScGridAll detail(@RequestParam("id") @ApiParam(value="网格id",required=true)  Integer id) {
        return gridEstateService.select(id);
    }

    /**
     * 新增网格信息
     *
     * @param body body
     * @return return
     */
    @ApiOperation("新增")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value="网格信息",required=true) ScGrid body) {
        int add = gridEstateService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    /**
     *  修改网格信息
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改")
    @PatchMapping
    public EmptyResourceResponse update(@RequestBody @ApiParam(value="网格信息",required=true) ScGrid body) {
        int delete = gridEstateService.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除网格信息
     *
     * @param id id
     * @return return
     */
    @ApiOperation("删除")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestParam("id") @ApiParam(value="网格id",required=true) Integer id) {
        int delete = gridEstateService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchDelete")
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value="网格id列表",required=true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 分页查询网格员
     *
     * @param paging paging
     * @param condition condition
     * @return return
     */
    @ApiOperation("分页查询网格员")
    @GetMapping("/manager/page")
    public PageResultVO listManager(@ApiParam(value="分页请求") PageRecord paging, @ApiParam(value="查询条件") ScGridManagerCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.listGridManagerPage(paging, condition);
    }

    /**
     * 新增网格员
     *
     * @param body body
     * @return return
     */
    @ApiOperation("新增网格员")
    @PostMapping("/manager")
    public EmptyResourceResponse createManager(@RequestBody @ApiParam(value="网格员信息",required=true) ScGridManager body) {
        Assert.notNull(body.getGridId(), "Require grid id");
        int add = gridEstateService.createManager(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    /**
     * 修改网格员
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改网格员")
    @PatchMapping("/manager")
    public EmptyResourceResponse updateManager(@RequestBody @ApiParam(value="网格员信息",required=true) ScGridManager body) {
        Assert.notNull(body.getId(), "Require grid manager id");
        int update = gridEstateService.updateManager(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除网格员
     *
     * @param id id
     * @return return
     */
    @ApiOperation("删除网格员")
    @DeleteMapping("/manager")
    public EmptyResourceResponse deleteManager(@RequestParam("id") @ApiParam(value="网格员id",required=true) Integer id) {
        int delete = gridEstateService.deleteManager(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 批量删除网格员
     *
     * @param ids ids
     * @return return
     */
    @ApiOperation("批量删除网格员")
    @DeleteMapping("/manager/batchDelete")
    public EmptyResourceResponse batchDeleteManager(@RequestBody @ApiParam(value="网格员id列表",required=true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.deleteManager(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }


    /**
     * 分页查询网格员考评信息
     *
     * @param paging paging
     * @param condition condition
     * @return return
     */
    @ApiOperation("分页查询网格员考评信息")
    @GetMapping("/evaluation/page")
    public PageResultVO listEvaluation(@ApiParam(value="分页请求") PageRecord paging, @ApiParam(value="查询条件") ScGridEvaluationCondition condition) {
        return gridEstateService.listGridEvaluationPage(paging, condition);
    }

    /**
     * 新增考评信息
     *
     * @param body body
     * @return return
     */
    @ApiOperation("新增考评信息")
    @PostMapping("/evaluation")
    public EmptyResourceResponse createEvaluation(@RequestBody @ApiParam(value = "考评信息", required = true) ScGridEvaluation body) {
        int add = gridEstateService.createEvaluation(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    /**
     * 修改考评信息
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改考评信息")
    @PatchMapping("/evaluation")
    public EmptyResourceResponse updateEvaluation(@RequestBody @ApiParam(value = "考评信息", required = true) ScGridEvaluation body) {
        Assert.notNull(body.getGridId(), "Require grid id");
        Assert.notNull(body.getGridManagerId(), "Require grid manager id");
        int update = gridEstateService.updateEvaluation(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除考评信息
     *
     * @param gridId gridId
     * @param gridManagerId gridManagerId
     * @return return
     */
    @ApiOperation("删除考评信息")
    @DeleteMapping("/evaluation")
    public EmptyResourceResponse deleteEvaluation(@RequestParam("gridId") @ApiParam(value = "网格id", required = true) Integer gridId, @RequestParam("gridManagerId") @ApiParam(value = "网格员id", required = true) Integer gridManagerId) {
        int delete = gridEstateService.deleteEvaluation(gridId, gridManagerId);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 批量删除考评信息
     *
     * @param gridId gridId
     * @param gridManagerIds gridManagerIds
     * @return return
     */
    @ApiOperation("批量删除考评信息")
    @DeleteMapping("/evaluation/batchDelete")
    public EmptyResourceResponse batchDeleteEvaluation(@RequestParam("gridId") @ApiParam(value = "网格id", required = true) Integer gridId, @RequestParam("gridManagerIds") @ApiParam(value = "网格员id列表", required = true) List<Integer> gridManagerIds) {
        int size = gridManagerIds == null ? 0 : gridManagerIds.size();
        int delete = gridEstateService.deleteEvaluation(gridId, gridManagerIds);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }
}
