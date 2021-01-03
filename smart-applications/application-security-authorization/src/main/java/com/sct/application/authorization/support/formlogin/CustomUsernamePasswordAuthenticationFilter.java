/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sct.application.authorization.support.formlogin;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.service.core.web.captcha.CaptchaCodeInfo;
import com.sct.service.core.web.captcha.CaptchaCodeUtil;
import com.sct.service.core.web.captcha.cache.CaptchaCacheEntity;
import com.sct.service.core.web.captcha.cache.CaptchaInfo;
import com.sct.service.core.web.rsa.RsaKeyGenerateUtil;
import com.sct.service.core.web.rsa.cache.RasKeyCacheInfo;
import com.sct.service.core.web.rsa.cache.RsaKeyCacheEntity;
import com.sct.service.database.security.service.DefaultUsernameResolver;
import com.sct.service.database.security.service.UsernameResolverEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Processes an authentication form submission. Called
 * {@code AuthenticationProcessingFilter} prior to Spring Security 3.0.
 * <p>
 * Login forms must present two parameters to this filter: a username and password. The
 * default parameter names to use are contained in the static fields
 * {@link #SPRING_SECURITY_FORM_USERNAME_KEY} and
 * {@link #SPRING_SECURITY_FORM_PASSWORD_KEY}. The parameter names can also be changed by
 * setting the {@code usernameParameter} and {@code passwordParameter} properties.
 * <p>
 * This filter by default responds to the URL {@code /login}.
 *
 * @author Ben Alex
 * @author Colin Sampaleanu
 * @author Luke Taylor
 * @since 3.0
 */
public class CustomUsernamePasswordAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    public static final String SPRING_SECURITY_FORM_PASSWORD_PUBLIC_KEY = "publickey";
    public static final String SPRING_SECURITY_FORM_CAPTCHA_CODE_KEY = "captchaCode";
    public static final String SPRING_SECURITY_FORM_USER_TYPE = "userType";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private String passwordPublicKeyParameter = SPRING_SECURITY_FORM_PASSWORD_PUBLIC_KEY;
    private String captchaCodeParameter = SPRING_SECURITY_FORM_CAPTCHA_CODE_KEY;
    private String userTypeParameter = SPRING_SECURITY_FORM_USER_TYPE;
    private boolean postOnly = true;
    private boolean captchaEnable = true;

    // ~ Constructors
    // ===================================================================================================

    public CustomUsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String userType = obtainUserType(request);
        String passwordPublicKey = obtainPasswordPublicKey(request);
        if (captchaEnable) {
            //如果验证码生效,请先验证图片验证码是否正确
            String catpchaCode = obtainCaptchaCode(request);
            if (org.springframework.util.StringUtils.isEmpty(catpchaCode))
                throw new AuthenticationServiceException(getMessage("WelcomeLoginController.verifyCode_notEmpty"));
            CaptchaCodeInfo captchaCodeInfo = new CaptchaCodeInfo();
            captchaCodeInfo.setKey(catpchaCode);
            CaptchaCacheEntity genCaptcha = CaptchaCodeUtil.takeOutFromCache(captchaCodeInfo);
            if (genCaptcha == null) {
                throw new AuthenticationServiceException(getMessage("WelcomeLoginController.verifyCode_ERROR"));
            }
            CaptchaInfo captchaInfo = genCaptcha.getCacheValue();
            if (captchaInfo == null || org.springframework.util.StringUtils.isEmpty(captchaInfo.getCode()) || !captchaInfo.getCode().toLowerCase().equals(catpchaCode.toLowerCase())) {
                throw new AuthenticationServiceException(getMessage("WelcomeLoginController.verifyCode_ERROR"));
            }
            if (genCaptcha.isExpired()) {
                throw new AuthenticationServiceException(getMessage("WelcomeLoginController.verifyCode_timeout_ERROR"));
            }
            CaptchaCodeUtil.remove(captchaCodeInfo);
        }

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        if (passwordPublicKey == null) {
            passwordPublicKey = "";
        }

        if (StringUtils.isNotEmpty(passwordPublicKey)) {
            //如果传入的密码公钥不为空,则需要解密
            String originPassword = password;
            RasKeyCacheInfo rasKeyCacheInfo = new RasKeyCacheInfo();
            rasKeyCacheInfo.setPublicKey(passwordPublicKey);
            RsaKeyCacheEntity rsaKeyCacheEntity = RsaKeyGenerateUtil.takeOutFromCache(rasKeyCacheInfo);
            if (rsaKeyCacheEntity != null && rsaKeyCacheEntity.getCacheValue() != null) {
                password = RsaKeyGenerateUtil.decryptDataOnJava(originPassword, rsaKeyCacheEntity.getCacheValue().getPrivateKey());
            }
            RsaKeyGenerateUtil.remove(rasKeyCacheInfo);
        }

        username = username.trim();

        if (StringUtils.isNotEmpty(userType)) {
//            if (userType.equalsIgnoreCase("manager")) {
//                username = new DefaultUsernameResolver().assemble(username, UsernameResolverEnum.manager_mix_3);
//            } else {
            username = new DefaultUsernameResolver().assemble(username, UsernameResolverEnum.mix_3);
//            }
        } else {
            //为空就默认是普通用户
            username = new DefaultUsernameResolver().assemble(username, UsernameResolverEnum.mix_3);
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * Enables subclasses to override the composition of the password, such as by
     * including additional values and a separator.
     * <p>
     * This might be used for example if a postcode/zipcode was required in addition to
     * the password. A delimiter such as a pipe (|) should be used to separate the
     * password and extended value(s). The <code>AuthenticationDao</code> will need to
     * generate the expected password in a corresponding manner.
     * </p>
     *
     * @param request so that request attributes can be retrieved
     * @return the password that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    @Nullable
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    /**
     * Enables subclasses to override the composition of the username, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     * @return the username that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected String obtainPasswordPublicKey(HttpServletRequest request) {
        return request.getParameter(passwordPublicKeyParameter);
    }

    protected String obtainCaptchaCode(HttpServletRequest request) {
        return request.getParameter(captchaCodeParameter);
    }

    protected String obtainUserType(HttpServletRequest request) {
        return request.getParameter(userTypeParameter);
    }

    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     *                    set
     */
    protected void setDetails(HttpServletRequest request,
                              UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    /**
     * Sets the parameter name which will be used to obtain the username from the login
     * request.
     *
     * @param usernameParameter the parameter name. Defaults to "username".
     */
    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.usernameParameter = usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }

    /**
     * Sets the parameter name which will be used to obtain the password from the login
     * request..
     *
     * @param passwordParameter the parameter name. Defaults to "password".
     */
    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
        this.passwordParameter = passwordParameter;
    }

    public void setPasswordPublicKeyParameter(String passwordPublicKeyParameter) {
        this.passwordPublicKeyParameter = passwordPublicKeyParameter;
    }

    public void setCaptchaCodeParameter(String captchaCodeParameter) {
        this.captchaCodeParameter = captchaCodeParameter;
    }

    public void setCaptchaEnable(boolean captchaEnable) {
        this.captchaEnable = captchaEnable;
    }

    public void setUserTypeParameter(String userTypeParameter) {
        this.userTypeParameter = userTypeParameter;
    }

    public String getMessage(String code) {
        return I18nMessageUtil.getInstance().getMessage(code);
    }
}
