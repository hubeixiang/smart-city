package com.sct.application.security.controller;

import com.sct.service.core.web.support.collection.ResultVOO;
import com.sct.service.core.web.support.simple.EmptyResourceResponse;
import com.sct.service.main.ScDictGroup;
import com.sct.service.main.ScDictServiceImpl;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Api(tags = "字典管理")
@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/dict", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/dict",})
@RestController
public class ScDictController extends BaseController {

    @Autowired
    private ScDictServiceImpl scDictService;

    @ApiOperation("所有字典值列举")
    @GetMapping("/all")
    public ResultVOO getAllDict() {
        Map<String, ScDictGroup> data = scDictService.getAllData();
        return ResultVOO.of(data);
    }

    @ApiOperation("刷新字典")
    @GetMapping("/refresh")
    public EmptyResourceResponse refresh() {
        scDictService.refresh();
        return EmptyResourceResponse.INSTANCE;
    }

}
