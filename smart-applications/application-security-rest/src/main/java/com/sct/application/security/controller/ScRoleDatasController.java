package com.sct.application.security.controller;

import com.sct.service.oauth2.core.constants.Oauth2Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = {"/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path_None_Auth + "/role/datas", "/" + Oauth2Constants.Oauth2_ResourceServer_Context_Path + "/role/datas",})
@RestController
public class ScRoleDatasController extends BaseController {
}
