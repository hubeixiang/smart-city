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
import com.sct.service.database.condition.ScGridManagerCondition;
import com.sct.service.database.entity.ScGrid;
import com.sct.service.database.entity.ScGridManager;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public PageResultVO list(PageRecord paging, ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        PageResultVO result = gridGridService.listPage(paging, condition);
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
    public ResultVOEntity listAll(ScGridCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridGridService.list(condition);
    }

    /**
     * 通过Id查询网格
     *
     * @param id
     * @return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail")
    public ScGridAll detail(@RequestParam("id") Integer id) {
        ScGridAll select = gridGridService.select(id);
        return select;
    }

    @ApiOperation("新增")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody ScGrid body) {
        int add = gridGridService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改")
    @PatchMapping
    public EmptyResourceResponse update(@RequestBody ScGrid body) {
        Assert.notNull(body, "Require body");
        int delete = gridGridService.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    @ApiOperation("删除")
    @DeleteMapping
    public EmptyResourceResponse delete(@PathVariable("id") Integer id) {
        int delete = gridGridService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchDelete")
    public EmptyResourceResponse batchDelete(@RequestBody List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridGridService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }


    /**
     * 分页查询网格员
     *
     * @param paging
     * @param condition
     * @return
     */
    @ApiOperation("分页查询网格员")
    @GetMapping("/manager/page")
    public PageResultVO listManager(PageRecord paging, ScGridManagerCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        PageResultVO result = gridGridService.listGridManagerPage(paging, condition);
        return result;
    }

    /**
     * 新增网格员
     *
     * @param body
     * @return
     */
    @ApiOperation("新增网格员")
    @PostMapping("/manager")
    public EmptyResourceResponse createManager(@RequestBody ScGridManager body) {
        Assert.notNull(body.getGridId(), "Require grid id");
        int add = gridGridService.createManager(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    /**
     * 修改网格员
     *
     * @param body
     * @return
     */
    @ApiOperation("修改网格员")
    @PatchMapping("/manager")
    public EmptyResourceResponse updateManager(@RequestBody ScGridManager body) {
        Assert.notNull(body, "Require request body");
        Assert.notNull(body.getId(), "Require grid manager id");
        int update = gridGridService.updateManager(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除网格员
     *
     * @param id
     * @return
     */
    @ApiOperation("删除网格员")
    @DeleteMapping("/manager")
    public EmptyResourceResponse deleteManager(@PathVariable("id") Integer id) {
        int delete = gridGridService.deleteManager(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 批量删除网格员
     *
     * @param ids
     * @return
     */
    @ApiOperation("批量删除网格员")
    @DeleteMapping("/manager/batchDelete")
    public EmptyResourceResponse batchDeleteManager(@RequestBody List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridGridService.deleteManager(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }
}
