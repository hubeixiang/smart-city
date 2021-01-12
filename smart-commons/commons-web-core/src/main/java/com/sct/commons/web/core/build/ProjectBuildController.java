package com.sct.commons.web.core.build;

import com.sct.commons.web.core.response.HttpResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/build", produces = {"application/json; charset=utf-8"})
@RestController
public class ProjectBuildController {

    @Autowired(required = false)
    private ProjectBuildProperties buildProperties;

    @GetMapping("/info")
    public HttpEntity<?> build() {
        if (buildProperties == null) {
            return ResponseEntity.ok(HttpResultEntity.failure("build info can't Configuration!", null));
        } else {
            return ResponseEntity.ok(buildProperties);
        }
    }
}
