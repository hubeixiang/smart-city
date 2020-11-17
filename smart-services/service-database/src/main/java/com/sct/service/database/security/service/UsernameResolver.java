package com.sct.service.database.security.service;

public interface UsernameResolver {
    public ResolverResult resolver(String username);

    public String assemble(String username, UsernameResolverEnum usernameResolverEnum);

    class ResolverResult {
        private UsernameResolverEnum usernameResolverEnum;
        private String result;

        public UsernameResolverEnum getUsernameResolverEnum() {
            return usernameResolverEnum;
        }

        public void setUsernameResolverEnum(UsernameResolverEnum usernameResolverEnum) {
            this.usernameResolverEnum = usernameResolverEnum;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
