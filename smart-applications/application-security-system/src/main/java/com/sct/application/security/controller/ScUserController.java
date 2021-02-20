/*
 *
 */

package com.sct.application.security.controller;

import com.sct.application.security.autoconfigure.SecurityRestConstants;
import com.sct.application.security.service.users.ScUserServiceImpl;
import com.sct.commons.file.location.FileLocation;
import com.sct.service.core.web.exception.APIException;
import com.sct.service.core.web.exception.ExceptionCode;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.PasswordResource;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScUserCondition;
import com.sct.service.database.condition.ScUserConditionExport;
import com.sct.service.database.entity.ScUser;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理->账号管理->管理账号/居民账号
 *
 * @author
 * @since 1.0.0
 */
@Api(tags = "系统管理->账号管理->管理账号/居民账号")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/users", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/users",}, produces = {SecurityRestConstants.WEB_PRODUCES})
@RestController
public class ScUserController extends BaseController {

    @Autowired
    private ScUserServiceImpl scUserService;

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
    public PageResultVO list(PageRecord paging, ScUserCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        PageResultVO result = scUserService.listPage(paging, condition);
        return result;
    }

    @ApiOperation("全部查询")
    @GetMapping("/all")
    public ResultVOEntity listAll(ScUserCondition condition) {
        condition.checkParam();
        condition.checkSQLinjectionException();
        return scUserService.list(condition);
    }

    /**
     * 导出,此方法只是提交导出参数,并将生成的文件定位信息返回
     * 真正的导出文件下载方法,还需要另外再调用 "/file/download" 进行真正的web文件下载
     *
     * @return
     */
    @ApiOperation("查询导出")
    @PostMapping("/export")
    public FileLocation export(@RequestBody ScUserConditionExport condition) {
        Assert.notNull(condition, "Require Export condition");
        condition.checkParam();
        FileLocation fileLocation = scUserService.export2FileLocation(condition);
        if (fileLocation == null) {
            throw APIException.of(ExceptionCode.SERVER_API_BUSINESS_ERROR, "生成文件失败");
        }
        return fileLocation;
    }

    @ApiOperation("用户注册,必须是居民才能注册")
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public EmptyResourceResponse register(
            @ApiParam(required = true, name = "scUser", value = "用户信息")
            @RequestBody ScUser scUser) {
        Assert.hasText(scUser.getUserId(), "Require userId");
        Assert.hasText(scUser.getUserName(), "Require userName");
        Assert.hasText(scUser.getCardId(), "Require cardId");
        scUserService.checkScUserExists(scUser);
        ScUser user = scUserService.create(scUser, true);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("新建用户,不限制是否是居民")
    @PostMapping(value = "/create")
    public SimpleResourceResponse<ScUser> create(
            @ApiParam(required = true, name = "scUser", value = "用户信息")
            @RequestBody ScUser scUser) {
        ScUser user = scUserService.create(scUser, false);
        return SimpleResourceResponse.of(user);
    }

    @ApiOperation("删除指定用户")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") Integer id) {
        if (scUserService.delete(id) != 1) {
            throw ResourceNotFoundException.of(id);
        }
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("批量删除用户")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody List<Integer> ids) {
        Assert.notEmpty(ids, "Require userIds");
        int size = scUserService.delete(ids);
        return EmptyResourceResponse.INSTANCE;
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/{id}")
    public SimpleResourceResponse<ScUser> get(@PathVariable("id") Integer id) {
        ScUser scUser = scUserService.findScUserById(id);
        if (scUser == null) {
            throw ResourceNotFoundException.of(id);
        }
        return SimpleResourceResponse.of(scUser);
    }

    @ApiOperation("更新用户信息")
    @PatchMapping("/{id}")
    public SimpleResourceResponse<ScUser> update(@PathVariable("id") Integer id,
                                                 @RequestBody ScUser scUser) {
        Assert.notNull(scUser, "Require body");
        scUser.setId(id); // Avoid userId in body
        ScUser user = scUserService.update(scUser, true);
        return SimpleResourceResponse.of(user);
    }

    @ApiOperation("更新用户密码")
    @PostMapping("/{id}/password")
    public EmptyResourceResponse updatePassword(@PathVariable("id") Integer id,
                                                @RequestBody PasswordResource res) {
        Assert.notNull(res, "Require body");
        Assert.hasText(res.getPassword(), "Require new password");
        Assert.hasText(res.getOldPassword(), "Require original password");
        if (scUserService.updatePassword(id, res.getPassword(), res.getOldPassword())) {
            throw ResourceNotFoundException.of(id);
        }
        return EmptyResourceResponse.INSTANCE;
    }
}
