/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sct.application.authorization.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.sct.service.oauth2.core.constants.Oauth2Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joe Grandja
 */
@RestController
public class JwkController {

    @Autowired
    private JWKSet jwkSet;

    @GetMapping(value = "/" + Oauth2Constants.Oauth2_Context_Path + "/keys", produces = "application/json; charset=UTF-8")
    public String keys() {
        return this.jwkSet.toString();
    }
}