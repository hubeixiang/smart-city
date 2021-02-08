/*
 *
 */

package com.sct.application.security.controller;

import com.sct.application.security.autoconfigure.SecurityRestConstants;
import com.sct.application.security.service.users.ScUserServiceImpl;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.PasswordResource;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScUserCondition;
import com.sct.service.database.entity.ScUser;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller for users.
 *
 * @author
 * @since 1.0.0
 */
@Api(tags = "用户管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/users", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/users",}, produces = {SecurityRestConstants.WEB_PRODUCES})
@RestController
public class ScUserController extends BaseController {

    @Autowired
    private ScUserServiceImpl scUserService;

    @GetMapping
    public PageResultVO list(PageRecord paging, ScUserCondition condition) {
        ScUserCondition.checkSQLinjectionException(condition);
        PageResultVO result = scUserService.listPage(paging, condition);
        return result;
    }

    @GetMapping("/all")
    public ResultVOEntity listAll(ScUserCondition condition) {
        ScUserCondition.checkSQLinjectionException(condition);
        return scUserService.list(condition);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public EmptyResourceResponse register(
            @ApiParam(required = true, name = "scUser", value = "用户信息")
            @RequestBody ScUser scUser) {
        Assert.hasText(scUser.getUserId(), "Require userId");
        Assert.hasText(scUser.getUserName(), "Require userName");
        scUserService.checkScUserExists(scUser);
        ScUser user = scUserService.create(scUser);
        return EmptyResourceResponse.INSTANCE;
    }

    @PostMapping
    public SimpleResourceResponse<ScUser> create(
            @ApiParam(required = true, name = "scUser", value = "用户信息")
            @RequestBody ScUser scUser) {
        ScUser user = scUserService.create(scUser);
        return SimpleResourceResponse.of(user);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, "Require userIds");
        int size = scUserService.delete(ids);
        return EmptyResourceResponse.INSTANCE;
    }

    @GetMapping("/{id}")
    public SimpleResourceResponse<ScUser> get(@PathVariable("id") String id) {
        ScUser scUser = scUserService.findScUserById(id);
        if (scUser == null) {
            throw ResourceNotFoundException.of(id);
        }
        return SimpleResourceResponse.of(scUser);
    }

    @PatchMapping("/{id}")
    public SimpleResourceResponse<ScUser> update(@PathVariable("id") String id,
                                                 @RequestBody ScUser scUser) {
        Assert.notNull(scUser, "Require body");
        scUser.setUserId(id); // Avoid userId in body
        ScUser user = scUserService.update(scUser, true);
        return SimpleResourceResponse.of(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") String id) {
        if (scUserService.delete(id) != 1) {
            throw ResourceNotFoundException.of(id);
        }
        return EmptyResourceResponse.INSTANCE;
    }

    @PostMapping("/{id}/password")
    public EmptyResourceResponse updatePassword(@PathVariable("id") String id,
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
