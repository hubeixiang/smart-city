package com.sct.application.business.controller;


import com.sct.application.business.dto.ScCommunityAll;
import com.sct.application.business.service.business.grid.GridCommunityServiceImpl;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultMap;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScCommunityCondition;
import com.sct.service.database.condition.ScCommunityLeaderCondition;
import com.sct.service.database.entity.ScCommunityLeader;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 网格化管理->社区管理
 */
@Api(tags = "网格化管理->社区管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/grid/community", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/grid/community",})
@RestController
public class GridCommunityController {

    @Autowired
    private GridCommunityServiceImpl gridCommunityService;

//    @Autowired
//    private ScCommunityImpl scCommunityImpl;

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
    public PageResultVO list(@ApiParam(name="分页请求") PageRecord paging, @ApiParam(name="查询条件") ScCommunityCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        PageResultVO result = gridCommunityService.listPage(paging, condition);
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
    public ResultVOEntity listAll(@ApiParam(name="查询条件") ScCommunityCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        return gridCommunityService.list(condition);
    }


    /**
     * 通过Id查询社区详情
     * @param id
     * @return
     */
    @ApiOperation("查看详情")
    @GetMapping("/detail")
    public ScCommunityAll detail(@RequestParam("id") @ApiParam(name="社区id",required=true) Integer id) {
        ScCommunityAll select = gridCommunityService.select(id);
        return select;
    }

    @ApiOperation("新增")
    @PostMapping
    public EmptyResourceResponse create(@RequestBody @ApiParam(name="社区与社区党组织信息",required=true) ScCommunityAll body) {
        ScCommunityAll add = gridCommunityService.create(body.getScCommunity(), body.getScCommunityParty());
        if (add != null && add.getScCommunityParty().getId() != null) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改")
    @PatchMapping
    public EmptyResourceResponse update(@RequestBody @ApiParam(name="社区与社区党组织信息",required=true) ScCommunityAll body) {
        if (body.getScCommunity() != null) {
            Assert.notNull(body.getScCommunity().getId(), "Require community id");
        }
        if (body.getScCommunityParty() != null) {
            Assert.notNull(body.getScCommunityParty().getId(), "Require community party id");
        }
        ScCommunityAll update = gridCommunityService.update(body.getScCommunity(), body.getScCommunityParty());
        if (update != null && update.getScCommunity().getId() != null) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("删除")
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestParam("id") @ApiParam(name="社区id",required=true) Integer id) {
        int delete = gridCommunityService.delete(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败,未删除任何记录");
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchDelete")
    public EmptyResourceResponse batchDelete(@RequestBody @ApiParam(name="社区id列表",required=true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridCommunityService.delete(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败,未删除任何记录");
        }
    }


    /**
     * 社区id与名字映射
     *
     * @return
     */
    @ApiOperation("社区id与名字映射")
    @GetMapping("/idNameMapping")
    public SimpleResourceResponse getMapping() {
        return SimpleResourceResponse.of(gridCommunityService.getIdNameMapping());
    }


    /**
     * 分页查询领导班子
     *
     * @param paging
     * @param condition
     * @return
     */
    @ApiOperation("分页查询领导班子")
    @GetMapping("/leader/page")
    public PageResultVO listLeader(@ApiParam(name="分页请求") PageRecord paging, @ApiParam(name="查询条件") ScCommunityLeaderCondition condition) {
        condition.checkSQLinjectionException(condition.getName());
        PageResultVO result = gridCommunityService.listCommunityLeaderPage(paging, condition);
        return result;
    }

    @ApiOperation("新增领导")
    @PostMapping("/leader")
    public EmptyResourceResponse createLeader(@RequestBody @ApiParam(name="领导信息", required=true) ScCommunityLeader body) {
        Assert.notNull(body.getCommunityId(), "Require community id");
        int add = gridCommunityService.createLeader(body);
        if (add > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功");
        }
    }

    @ApiOperation("修改领导")
    @PatchMapping("/leader")
    public EmptyResourceResponse updateLeader(@RequestBody @ApiParam(name="领导信息", required=true) ScCommunityLeader body) {
        Assert.notNull(body.getId(), "Require community leader id");
        int update = gridCommunityService.updateLeader(body);
        if (update > 0) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "保存不成功,未更新任何记录");
        }
    }

    @ApiOperation("删除领导")
    @DeleteMapping("/leader")
    public EmptyResourceResponse deleteLeader(@RequestParam("id") @ApiParam(name="领导id", required=true) Integer id) {
        int delete = gridCommunityService.deleteLeader(id);
        if (delete == 1) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败,未删除任何记录");
        }
    }

    @ApiOperation("批量删除领导")
    @DeleteMapping("/leader/batchDelete")
    public EmptyResourceResponse batchDeleteLeader(@RequestBody @ApiParam(name="领导id列表", required=true) List<Integer> ids) {
        int size = ids == null ? 0 : ids.size();
        int delete = gridCommunityService.deleteLeader(ids);
        if (delete == size) {
            return EmptyResourceResponse.INSTANCE;
        } else {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "删除失败,未删除任何记录");
        }
    }

}
