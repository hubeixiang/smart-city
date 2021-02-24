package com.sct.application.business.controller;


import com.sct.application.business.dto.ScCommunityAll;
import com.sct.application.business.service.business.grid.GridCommunityServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScCommunityCondition;
import com.sct.service.database.entity.ScCommunity;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网格化管理->社区管理
 */
@Api(tags = "网格化管理->社区管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/community", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/community",})
@RestController
public class GridCommunityController {

    @Autowired
    private GridCommunityServiceImpl gridCommunityService;

    /**
     * 页面打开需要初始化的相关信息
     *
     * @return return
     */
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->社区管理");
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
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScCommunityCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridCommunityService.listPage(paging, condition);
    }

    /**
     * 全部查询
     *
     * @param condition condition
     * @return return
     */
    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScCommunityCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridCommunityService.list(condition);
    }


    /**
     * 通过Id查询社区详情
     *
     * @param id id
     * @return return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail/{id}")
    public ScCommunityAll detail(@PathVariable("id") @ApiParam(value = "社区id", required = true) Integer id) {
        ScCommunityAll select = gridCommunityService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("查询详情, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("新增社区")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "社区信息", required = true) ScCommunity body) {
        ScCommunity add = gridCommunityService.create(body);
        if (add != null && add.getId() != null) {
//            return ResponseEntity.ok(HttpResultEntity.ok());
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功, 请检查数据");
        }
    }

    @ApiOperation("修改社区")
    @PatchMapping("/{id}")
    public EmptyResourceResponse update(@PathVariable("id") @ApiParam(value = "社区id", required = true) Integer id, @RequestBody @ApiParam(value = "社区信息", required = true) ScCommunity body) {
        body.setId(id);
        int update = gridCommunityService.update(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("修改失败，未修改任何数据, 可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("删除社区")
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") @ApiParam(value = "社区id", required = true) Integer id) {
        int delete = gridCommunityService.delete(id);
        if (delete > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("删除失败,未删除任何数据,可能原因：id[%s]不存在", id));
        }
    }

    @ApiOperation("批量删除社区")
    @DeleteMapping
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "社区id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridCommunityService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, String.format("实际删除数据[%d]少于计划删除[%d],可能原因：以前删除过", delete, size));
        }
    }

    /**
     * 社区id与名字映射
     *
     * @return return
     */
    @ApiOperation("社区id与名字映射")
    @GetMapping("/idNameMapping")
    public SimpleResourceResponse getMapping() {
        return SimpleResourceResponse.of(gridCommunityService.getIdNameMapping());
    }

}
