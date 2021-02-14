package com.sct.service.database.security.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultUsernameResolver implements UsernameResolver {
    private final static String username_split = "::";

    @Override
    public ResolverResult resolver(String username) {
        ResolverResult resolverResult = new ResolverResult();
        if (isAssemble(username)) {
            String[] arrays = username.split(username_split);
            String type = arrays[0];
            String value = arrays[1];
            resolverResult.setResult(value);
            resolverResult.setUsernameResolverEnum(UsernameResolverEnum.valueOf(type));
        } else {
            resolverResult.setResult(username);
        }
        return resolverResult;
    }

    @Override
    public String assemble(String username, UsernameResolverEnum usernameResolverEnum) {
        ResolverResult resolverResult = resolver(username);
        if (resolverResult.getUsernameResolverEnum() != null) {
            //如果是已经组装过的结果,则将原结果替换为新的组装类型
            return internalAssemble(resolverResult.getResult(), usernameResolverEnum);
        } else {
            //没有组装过的,则按照新的方式进行组装
            return internalAssemble(username, usernameResolverEnum);
        }
    }

    private String internalAssemble(String username, UsernameResolverEnum usernameResolverEnum) {
        if (usernameResolverEnum == null) {
            return username;
        } else {
            return String.format("%s%s%s", usernameResolverEnum.toString(), username_split, username);
        }
    }

    private boolean isAssemble(String username) {
        String[] arrays = username.split(username_split);
        if (arrays != null && (isNormalAssemble(arrays.length))) {
            return true;
        }
        return false;
    }

    private boolean isNormalAssemble(int length) {
        return length == 2;
    }
}
