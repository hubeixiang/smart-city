/*
 *
 */

package com.sct.application.security.controller;

import com.sct.application.security.service.users.ScManagerUserServiceImpl;
import com.sct.service.core.web.exception.ResourceNotFoundException;
import com.sct.service.core.web.support.collection.PageResultVO;
import com.sct.service.core.web.support.collection.ResultVOEntity;
import com.sct.service.core.web.support.collection.pages.PageRecord;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.core.web.support.simple.PasswordResource;
import com.sct.service.core.web.support.simple.SimpleResourceResponse;
import com.sct.service.database.condition.ScUserManagerCondition;
import com.sct.service.database.entity.ScManager;
import com.sct.service.database.entity.ScRole;
import com.sct.service.database.entity.ScRoleDatas;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
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
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/mgt/users", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/mgt/users",})
@RestController
public class ScUserManagerController extends BaseController {

    @Autowired
    private ScManagerUserServiceImpl scManagerUserService;

    @GetMapping("/list")
    public PageResultVO list(PageRecord paging, ScUserManagerCondition condition) {
        ScUserManagerCondition.checkSQLinjectionException(condition);
        return scManagerUserService.listPage(paging, condition);
    }

    @GetMapping("/all")
    public ResultVOEntity listAll(ScUserManagerCondition condition) {
        ScUserManagerCondition.checkSQLinjectionException(condition);
        return scManagerUserService.list(condition);
    }

    @PostMapping
    public SimpleResourceResponse<ScManager> create(@RequestBody ScManager scManager) {
        Assert.hasText(scManager.getUserId(), "Require userId");
        Assert.hasText(scManager.getUserName(), "Require userName");
        scManagerUserService.checkScUserExists(scManager);
        ScManager user = scManagerUserService.create(scManager);
        return SimpleResourceResponse.of(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/register")
    public EmptyResourceResponse register(@RequestBody ScManager scManager) {
        Assert.hasText(scManager.getUserId(), "Require userId");
        Assert.hasText(scManager.getUserName(), "Require userName");
        scManagerUserService.checkScUserExists(scManager);
        ScManager user = scManagerUserService.create(scManager);
        return EmptyResourceResponse.INSTANCE;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public EmptyResourceResponse delete(@RequestBody List<String> ids) {
        Assert.notEmpty(ids, "Require ids");
        int size = scManagerUserService.delete(ids);
        return EmptyResourceResponse.INSTANCE;
    }

    @GetMapping("/{id}")
    public SimpleResourceResponse<ScManager> get(@PathVariable("id") String id) {
        ScManager scManager = scManagerUserService.findScUserById(id);
        if (scManager == null) {
            throw ResourceNotFoundException.of(id);
        }
        return SimpleResourceResponse.of(scManager);
    }

    @PatchMapping("/{id}")
    public SimpleResourceResponse<ScManager> update(@PathVariable("id") String id,
                                                    @RequestBody ScManager scManager) {
        Assert.notNull(scManager, "Require body");
        scManager.setId(id); // Avoid userId in body
        ScManager user = scManagerUserService.update(scManager, true);
        return SimpleResourceResponse.of(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public EmptyResourceResponse delete(@PathVariable("id") String id) {
        if (scManagerUserService.delete(id) != 1) {
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
        if (scManagerUserService.updatePassword(id, res.getPassword(), res.getOldPassword())) {
            throw ResourceNotFoundException.of(id);
        }
        return EmptyResourceResponse.INSTANCE;
    }

    @GetMapping("/{id}/roles")
    public SimpleResourceResponse<List<ScRole>> listRoles(@PathVariable("id") String id) {
        return null;
    }

    @GetMapping(path = "/{id}/roles", params = {"bind_mode=true"})
    public SimpleResourceResponse<List<ScRole>> listRolesWithBindZone(
            @PathVariable("user_id") String userId,
            @RequestParam("bind_mode") Boolean bindMode) {
        return null;
    }

    @PutMapping("/{id}/roles")
    public EmptyResourceResponse updateAllRoles(@PathVariable("id") String id,
                                                @RequestBody List<String> roleIds) {
        return EmptyResourceResponse.INSTANCE;
    }

    @GetMapping("/{id}/data/roles")
    public SimpleResourceResponse<List<ScRoleDatas>> listDataRoles(
            @PathVariable("id") String id) {
        return null;
    }

    @PutMapping("/{id}/data/roles")
    public EmptyResourceResponse updateAllDataRoles(@PathVariable("id") String id,
                                                    @RequestBody List<String> roleIds) {
        return EmptyResourceResponse.INSTANCE;
    }
//
//    @GetMapping("/{user_id}/operations")
//    public Resources<OperationIdsResource> listOperations(
//            @PathVariable("user_id") String userId,
//            @RequestParam(value = "app_ids", required = false) Integer[] appIds) {
//        Map<Integer, Set<Integer>> operIds = this.operationService
//                .findAllByUserId(userId, appIds == null ? null : Arrays.asList(appIds),
//                        true);
//        List<OperationIdsResource> res = operIds.entrySet().stream()
//                .map(o -> OperationIdsResource.from(o.getKey(), o.getValue()))
//                .collect(Collectors.toList());
//        return new Resources<>(res,
//                linkTo(methodOn(UserManagerController.class).listOperations(userId, appIds))
//                        .withSelfRel());
//    }
//
//    @GetMapping("/{user_id}/data/operations")
//    public Resources<DataOperationMapResource> listDataOperations(
//            @PathVariable("user_id") String userId,
//            @RequestParam(value = "app_ids", required = false) Integer[] appIds) {
//        Map<Integer, Set<DataOperationResource>> operIds = this.dataOperationService
//                .findAllByUserId(userId, appIds == null ? null : Arrays.asList(appIds),
//                        true);
//        List<DataOperationMapResource> res = operIds.entrySet().stream()
//                .map(o -> DataOperationMapResource.from(o.getKey(), o.getValue()))
//                .collect(Collectors.toList());
//        return new Resources<>(res,
//                linkTo(methodOn(UserManagerController.class).listDataOperations(userId, appIds))
//                        .withSelfRel());
//    }
//
//    @GetMapping("/{user_id}/zones")
//    public Resources<ZoneResource> listZones(@PathVariable("user_id") String userId) {
//        List<ZoneResource> res;
//        if (this.extraTablesProperties.getZone().isEnabled()
//                && this.extraTablesProperties.getZone().getZoneType() != null) {
//            List<Zone> zoneIds = this.zoneService.findZonesByRelation(
//                    this.extraTablesProperties.getZone().getZoneType(),
//                    ZoneRelationType.USER, userId, "zoneId", "zoneName", "zoneLevel");
//            res = zoneIds.stream().map(ZoneResource::from)
//                    .collect(Collectors.toList());
//        } else {
//            List<Integer> zoneIds = this.zoneService
//                    .findZoneIdsByRelation(ZoneRelationType.USER, userId);
//            res = zoneIds.stream().map(ZoneResource::from)
//                    .collect(Collectors.toList());
//        }
//        return new Resources<>(res,
//                linkTo(methodOn(UserManagerController.class).listZones(userId)).withSelfRel());
//    }
//
//    @PutMapping("/{user_id}/zones")
//    public EmptyResource updateAllZones(@PathVariable("user_id") String userId,
//                                        @RequestBody List<Integer> zoneIds) {
//        if (!this.zoneService
//                .bindByRelationId(ZoneRelationType.USER, userId, zoneIds)) {
//            throw new UnprocessableException();
//        }
//        return EmptyResource.INSTANCE;
//    }
//
//    @GetMapping("/{user_id}/mgmt/zones")
//    public Resources<ZoneResource> listMgmtZones(@PathVariable("user_id") String userId) {
//        List<ZoneResource> res;
//        boolean isAdmin = this.userService.admin(userId);
//
//        if (!isAdmin) {
//            res = Collections.emptyList();
//        } else if (this.extraTablesProperties.getZone().isEnabled()
//                && this.extraTablesProperties.getZone().getZoneType() != null) {
//            List<Zone> zoneIds = this.zoneService.findZonesByRelation(
//                    this.extraTablesProperties.getZone().getZoneType(),
//                    ZoneRelationType.USER_MGMT_ZONE, userId, "zoneId", "zoneName", "zoneLevel");
//            res = zoneIds.stream().map(ZoneResource::from)
//                    .collect(Collectors.toList());
//        } else {
//            List<Integer> zoneIds = this.zoneService
//                    .findZoneIdsByRelation(ZoneRelationType.USER_MGMT_ZONE, userId);
//            res = zoneIds.stream().map(ZoneResource::from)
//                    .collect(Collectors.toList());
//        }
//        // Use user zones if no mgmt zones.
//        if (isAdmin && res.size() == 0) {
//            res = new ArrayList<>(listZones(userId).getContent());
//        }
//        return new Resources<>(res,
//                linkTo(methodOn(UserManagerController.class).listZones(userId)).withSelfRel());
//    }
//
//    @PutMapping("/{user_id}/mgmt/zones")
//    public EmptyResource updateAllMgmtZones(@PathVariable("user_id") String userId,
//                                            @RequestBody List<Integer> zoneIds) {
//        List<Integer> userZoneIds = this.zoneService
//                .findZoneIdsByRelation(ZoneRelationType.USER, userId);
//        if (userZoneIds.size() == zoneIds.size() && userZoneIds.containsAll(zoneIds)
//                && this.zoneService.deleteByRelationId(
//                ZoneRelationType.USER_MGMT_ZONE, userId) == 0) {
//            throw new UnprocessableException();
//        } else if (!this.zoneService.bindByRelationId(
//                ZoneRelationType.USER_MGMT_ZONE, userId, zoneIds)) {
//            throw new UnprocessableException();
//        }
//        return EmptyResource.INSTANCE;
//    }
//
//    @GetMapping("/identify")
//    public UserResource identify(@RequestParam("id") String id,
//                                 @RequestParam(value = "by", required = false) String by) {
//        Assert.hasText(id, "Require id");
//
//        String[] fields = StringUtils.split(by, ", ");
//        if (fields != null && fields.length > 0) {
//            fields = Arrays.stream(fields).distinct()
//                    .map(CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE)::convert)
//                    .map(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL)::convert)
//                    .toArray(String[]::new);
//            Assert.isTrue(Arrays.stream(fields)
//                            .allMatch(s -> ArrayUtils.contains(IDENTIFY_BY_FIELDS, s)),
//                    "Invalid by, should be empty or any of [" +
//                            String.join(",", IDENTIFY_BY_FIELDS) + "]");
//        } else {
//            fields = IDENTIFY_BY_FIELDS;
//        }
//
//        Path<?>[] paths = Services.fieldsToPaths(QUser.user, fields);
//        for (Path<?> path : paths) {
//            List<User> users = this.userService
//                    .findAll(((StringPath) path).eq(id), IDENTIFY_RESULT_FIELDS);
//            if (users.size() == 1) {
//                User user = users.get(0);
//                return UserResource.from(user,
//                        linkTo(methodOn(UserManagerController.class).identify(id, by)).withSelfRel(),
//                        linkTo(methodOn(UserManagerController.class).get(user.getUserId()))
//                                .withRel("user"));
//            }
//        }
//
//        throw new ResourceNotFoundException();
//    }
//
//    @GetMapping("/by/ids")
//    public Resources<UserResource> byIds(@RequestParam("ids") String idsStr,
//                                         @RequestParam(value = "fields", required = false) String fieldsStr) {
//        Assert.hasText(idsStr, "Require ids");
//
//        String[] ids = StringUtils.split(idsStr, ", ");
//        Assert.isTrue(Arrays.stream(ids).allMatch(StringUtils::isNoneBlank),
//                "Invalid ids");
//
//        String[] fields = StringUtils.split(fieldsStr, ", ");
//        if (fields != null && fields.length > 0) {
//            fields = Arrays.stream(fields).distinct()
//                    .map(CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE)::convert)
//                    .map(CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL)::convert)
//                    .toArray(String[]::new);
//            Assert.isTrue(Arrays.stream(fields)
//                            .allMatch(p -> StringUtils.isNoneBlank(p) && ArrayUtils.indexOf(EXCLUDED_FIELDS, p) == -1),
//                    "Invalid fields");
//        } else {
//            fields = new String[0];
//        }
//        final String[] fields2 = fields;
//
//        List<List<String>> partitions = Lists
//                .partition(Arrays.stream(ids).distinct().collect(Collectors.toList()),
//                        Repositories.DEFAULT_PARTITION_SIZE);
//        // Find all users, not only undeleted users
//        List<User> users = partitions.stream()
//                .map(partition -> this.userService.findAllIncludeDeleted(QUser.user.userId.in(partition), fields2))
//                .flatMap(Collection::stream).collect(Collectors.toList());
//        List<UserResource> res = users.stream().map(UserResource::from)
//                .collect(Collectors.toList());
//
//        return new Resources<>(res,
//                linkTo(methodOn(UserManagerController.class).byIds(idsStr, fieldsStr))
//                        .withSelfRel());
//    }
}
