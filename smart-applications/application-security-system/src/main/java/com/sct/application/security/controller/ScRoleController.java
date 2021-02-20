package com.sct.application.security.controller;

import com.sct.application.security.service.users.ScRoleServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScRoleCondition;
import com.sct.service.database.condition.ScRoleConditionExport;
import com.sct.service.database.entity.ScRoleDatas;
import com.sct.service.database.entity.ScRoleOperations;
import com.sct.service.database.entity.ScUserRoleRel;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Api(tags = "系统管理->角色管理->功能角色")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/role", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/role",})
@RestController
public class ScRoleController extends BaseController {

    @Autowired
    private ScRoleServiceImpl scRoleService;

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


    @ApiOperation("分页查询")
    @GetMapping("page")
    public PageResultVO list(PageRecord paging, ScRoleCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = scRoleService.listPage(paging, condition);
        return result;
    }

    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(ScRoleCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return scRoleService.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出")
    @PostMapping("/export")
    public FileLocation export(@RequestBody ScRoleConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = scRoleService.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("用户角色关联增加")
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public EmptyResourceResponse create(@ApiParam(required = true, name = "scUserRoleRel", value = "用户角色关联信息")
                                        @RequestBody ScUserRoleRel scUserRoleRel) {
        scRoleService.inserts(Arrays.asList(scUserRoleRel));
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色关联增加,批量")
    @RequestMapping(value = "/create/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse createBatch(@ApiParam(required = true, name = "scUserRoleRel", value = "用户角色关联信息")
                                             @RequestBody List<ScUserRoleRel> scUserRoleRel) {
        scRoleService.inserts(scUserRoleRel);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色关联删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public EmptyResourceResponse delete(@ApiParam(required = true, name = "scUserRoleRel", value = "用户角色关联信息")
                                        @RequestBody ScUserRoleRel scUserRoleRel) {
        int total = scRoleService.deletes(Arrays.asList(scUserRoleRel));
        if (total != 1) {
            throw ResourceNotFoundException.of(scUserRoleRel);
        }
        return EmptyResourceResponse.INSTANCE;
    }


    @ApiOperation("用户角色关联删除,批量")
    @RequestMapping(value = "/delete/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse deleteBatch(@ApiParam(required = true, name = "scUserRoleRel", value = "用户角色关联信息")
                                             @RequestBody List<ScUserRoleRel> scUserRoleRel) {
        int total = scRoleService.deletes(scUserRoleRel);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色关联删除,按用户")
    @RequestMapping(value = "/delete/{userId}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteByUserId(@PathVariable("userId") Integer userId) {
        int total = scRoleService.delete(userId, null, null);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色关联删除,按用户,按角色")
    @RequestMapping(value = "/delete/{userId}/{roleId}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteByUserId(@PathVariable("userId") Integer userId,
                                                @PathVariable("roleId") Integer roleId) {
        int total = scRoleService.delete(userId, roleId, null);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色关联删除,按用户,按角色,按角色类型")
    @RequestMapping(value = "/delete/{userId}/{roleId}/{devType}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteByUserId(@PathVariable("userId") Integer userId,
                                                @PathVariable("roleId") Integer roleId,
                                                @PathVariable("devType") Integer roleType) {
        int total = scRoleService.delete(userId, roleId, roleType);
        return EmptyResourceResponse.INSTANCE;
    }

    //--------------------------

    @ApiOperation("用户角色操作权限增加")
    @RequestMapping(value = "/function/create", method = {RequestMethod.POST})
    public EmptyResourceResponse functionCreate(@ApiParam(required = true, name = "scRoleOperations", value = "角色操作权限信息")
                                                @RequestBody ScRoleOperations scRoleOperations) {
        scRoleService.insertFunctions(Arrays.asList(scRoleOperations));
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色操作权限增加,批量")
    @RequestMapping(value = "/function/create/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse functionCreateBatch(@ApiParam(required = true, name = "scRoleOperations", value = "角色操作权限信息")
                                                     @RequestBody List<ScRoleOperations> scRoleOperations) {
        scRoleService.insertFunctions(scRoleOperations);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色操作权限删除")
    @RequestMapping(value = "/function/delete", method = {RequestMethod.POST})
    public EmptyResourceResponse functionDelete(@ApiParam(required = true, name = "scRoleOperations", value = "角色操作权限信息")
                                                @RequestBody ScRoleOperations scRoleOperations) {
        int total = scRoleService.deleteFunctions(Arrays.asList(scRoleOperations));
        if (total != 1) {
            throw ResourceNotFoundException.of(scRoleOperations);
        }
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色操作权限删除,批量")
    @RequestMapping(value = "/function/delete/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse functionDeleteBatch(@ApiParam(required = true, name = "scRoleOperations", value = "角色操作权限信息")
                                                     @RequestBody List<ScRoleOperations> scRoleOperations) {
        int total = scRoleService.deleteFunctions(scRoleOperations);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色操作权限删除,按角色")
    @RequestMapping(value = "/function/delete/{roleId}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteByRoleId(@PathVariable("roleId") Integer roleId) {
        int total = scRoleService.deleteFunction(roleId, null);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色操作权限删除,按角色,按应用类型")
    @RequestMapping(value = "/function/delete/{roleId}/{devType}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteByRoleIdAndDevType(@PathVariable("roleId") Integer roleId, @PathVariable("devType") Integer devType) {
        int total = scRoleService.deleteFunction(roleId, devType);
        return EmptyResourceResponse.INSTANCE;
    }

    //--------------------------


    @ApiOperation("用户角色数据权限增加")
    @RequestMapping(value = "/data/create", method = {RequestMethod.POST})
    public EmptyResourceResponse dataCreate(@ApiParam(required = true, name = "scRoleDatas", value = "角色数据权限信息")
                                            @RequestBody ScRoleDatas scRoleDatas) {
        scRoleService.insertDataOpts(Arrays.asList(scRoleDatas));
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色数据权限增加,批量")
    @RequestMapping(value = "/data/create/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse dataCreateBatch(@ApiParam(required = true, name = "scRoleDatas", value = "角色数据权限信息")
                                                 @RequestBody List<ScRoleDatas> scRoleDatas) {
        scRoleService.insertDataOpts(scRoleDatas);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色数据权限删除")
    @RequestMapping(value = "/data/delete", method = {RequestMethod.POST})
    public EmptyResourceResponse dataDelete(@ApiParam(required = true, name = "scRoleDatas", value = "角色数据权限信息")
                                            @RequestBody ScRoleDatas scRoleDatas) {
        int total = scRoleService.insertDataOpts(Arrays.asList(scRoleDatas));
        if (total != 1) {
            throw ResourceNotFoundException.of(scRoleDatas);
        }
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色数据权限删除,批量")
    @RequestMapping(value = "/data/delete/batch", method = {RequestMethod.POST})
    public EmptyResourceResponse dataDeleteBatch(@ApiParam(required = true, name = "scRoleDatas", value = "角色数据权限信息")
                                                 @RequestBody List<ScRoleDatas> scRoleDatas) {
        int total = scRoleService.insertDataOpts(scRoleDatas);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色数据权限删除,按角色")
    @RequestMapping(value = "/data/delete/{roleId}", method = {RequestMethod.GET})
    public EmptyResourceResponse dataDeleteByRoleId(@PathVariable("roleId") Integer roleId) {
        int total = scRoleService.deleteDataOpts(roleId, null, null);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("用户角色数据权限删除,按角色,按网格ID")
    @RequestMapping(value = "/data/delete/{roleId}/{gridId}", method = {RequestMethod.GET})
    public EmptyResourceResponse deleteDataByRoleIdAndDevType(@PathVariable("roleId") Integer roleId, @PathVariable("gridId") Integer gridId) {
        int total = scRoleService.deleteDataOpts(roleId, gridId, null);
        return EmptyResourceResponse.INSTANCE;
    }
}
