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

    /**
     * 页面打开需要初始化的相关信息
     *
     * @param model model
     * @return return
     */
    @ApiOperation("页面初始化")
    @GetMapping
    public SimpleResourceResponse init(Model model) {
        return SimpleResourceResponse.of("网格化管理->小区管理");
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
    public PageResultVO list(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScEstateCondition condition) {
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
    public ResultVOEntity listAll(@ApiParam(value = "查询条件") ScEstateCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.list(condition);
    }

    /**
     * 通过Id查询小区
     *
     * @param id id
     * @return return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail")
    public ScEstate detail(@RequestParam("id") @ApiParam(value = "小区id", required = true) Integer id) {
        ScEstate select = gridEstateService.select(id);
        if (select != null) {
            return select;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "查询详情失败");
        }
    }

    /**
     * 新增小区
     *
     * @param body body
     * @return return
     */
    @ApiOperation("新增")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(value = "小区信息", required = true) ScEstate body) {
        int add = gridEstateService.create(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    /**
     * 修改小区
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改")
    @PatchMapping
    public EmptyResourceResponse update(@RequestBody @ApiParam(value = "小区信息") ScEstate body) {
        Assert.notNull(body, "Require body");
        int delete = gridEstateService.update(body);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除小区
     *
     * @param id id
     * @return return
     */
    @ApiOperation("删除")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestParam("id") @ApiParam(value = "小区id", required = true) Integer id) {
        int delete = gridEstateService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 批量删除小区
     *
     * @param ids ids
     * @return return
     */
    @ApiOperation("批量删除")
    @DeleteMapping("/batchDelete")
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(value = "小区id列表", required = true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }


    /**
     * 分页查询物业人员
     *
     * @param paging    分页信息
     * @param condition 查询条件
     * @return 分页结果
     */
    @ApiOperation("分页查询物业人员")
    @GetMapping("/staff/page")
    public PageResultVO listStaff(@ApiParam(value = "分页请求") PageRecord paging, @ApiParam(value = "查询条件") ScPropertyStaffCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridEstateService.listPropertyStaffPage(paging, condition);
    }

    /**
     * 新增物业人员
     *
     * @param body body
     * @return return
     */
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

    /**
     * 修改物业人员
     *
     * @param body body
     * @return return
     */
    @ApiOperation("修改物业人员")
    @PatchMapping("/staff")
    public EmptyResourceResponse updateStaff(@RequestBody @ApiParam(value = "物业人员信息", required = true) ScPropertyStaff body) {
        Assert.notNull(body.getId(), "Require property staff id");
        int update = gridEstateService.updateStaff(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未更新任何数据");
        }
    }

    /**
     * 删除物业人员
     *
     * @param id id
     * @return return
     */
    @ApiOperation("删除物业人员")
    @DeleteMapping("/staff")
    public EmptyResourceResponse deleteStaff(@RequestParam("id") @ApiParam(value = "物业人员id", required = true) Integer id, @RequestParam("estateId") @ApiParam(value = "小区id", required = true) Integer estateId) {
        int delete = gridEstateService.deleteStaff(id, estateId);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }

    /**
     * 批量删除物业人员
     *
     * @param ids ids
     * @return return
     */
    @ApiOperation("批量删除物业人员")
    @DeleteMapping("/staff/batchDelete")
    public EmptyResourceResponse batchDeleteStaff(@RequestBody @ApiParam(value = "物业人员id列表", required = true) List<Integer> ids, @RequestParam("estateId") @ApiParam(value = "小区id", required = true) Integer estateId) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridEstateService.deleteStaff(ids, estateId);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "未删除任何数据");
        }
    }
}
