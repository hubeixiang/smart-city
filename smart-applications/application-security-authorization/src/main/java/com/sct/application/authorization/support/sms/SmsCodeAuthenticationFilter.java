package com.sct.application.authorization.support.sms;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.service.core.web.smscode.SmsGenerateUtil;
import com.sct.service.core.web.smscode.cache.SmsCacheEntity;
import com.sct.service.core.web.smscode.cache.SmsInfo;
import com.sct.service.database.security.service.DefaultUsernameResolver;
import com.sct.service.database.security.service.UsernameResolverEnum;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String COREQI_FORM_MOBILE_KEY = "mobile";
    public static final String COREQI_FORM_USER_TYPE_KEY = "userType";
    private String mobileParameter = COREQI_FORM_MOBILE_KEY;    //请求中携带手机号的参数名称
    private String userTypeParameter = COREQI_FORM_USER_TYPE_KEY;    //请求中携带手机号的参数名称
    private boolean postOnly = true;    //指定当前过滤器是否只处理POST请求

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login-mobile", "POST")); //指定当前过滤器处理的请求
        //也可以在外部指定
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            String userType = this.obtainUserType(request);
            if (mobile == null) {
                mobile = "";
            }
            mobile = mobile.trim();
            validate(mobile, new ServletWebRequest(request));

            //增加查询类型
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(userType)) {
//                if (userType.equalsIgnoreCase("manager")) {
//                    mobile = new DefaultUsernameResolver().assemble(mobile, UsernameResolverEnum.manager_cellPhone);
//                } else {
                    mobile = new DefaultUsernameResolver().assemble(mobile, UsernameResolverEnum.cellPhone);
//                }
            } else {
                //为空就默认是普通用户
                mobile = new DefaultUsernameResolver().assemble(mobile, UsernameResolverEnum.cellPhone);
            }
            SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    /**
     * 获取手机号码
     *
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(this.mobileParameter);
    }

    protected String obtainUserType(HttpServletRequest request) {
        return request.getParameter(this.userTypeParameter);
    }

    /**
     * 把请求的详情，例如请求ip、SessionId等设置到验证请求中去
     *
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return this.mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setUserTypeParameter(String userTypeParameter) {
        Assert.hasText(mobileParameter, "userType parameter must not be empty or null");
        this.userTypeParameter = userTypeParameter;
    }

    private void validate(String mobile, ServletWebRequest request) {
        String codeInRequest = request.getParameter("smsVerifyCode");
        if (!StringUtils.hasText(codeInRequest)) {
            throw new AuthenticationServiceException(getMessage("SmsCodeFilter.sms_code_not_empty"));
        }

        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setPhone(mobile);
        smsInfo.setCode(codeInRequest);
        SmsCacheEntity smsCacheEntity = SmsGenerateUtil.takeOutFromCache(smsInfo);

        if (smsCacheEntity == null) {
            throw new AuthenticationServiceException(getMessage("SmsCodeFilter.sms_code_not_exists"));
        }
        if (smsCacheEntity.isExpired()) {
            throw new AuthenticationServiceException(getMessage("SmsCodeFilter.sms_code_expired"));
        }
        long currentTime = System.currentTimeMillis();
        SmsInfo newsmsInfo = smsCacheEntity.getCacheValue();
        long expireDate = newsmsInfo.getExpireDate();
        if (expireDate > 0 && expireDate < currentTime) {
            throw new AuthenticationServiceException(getMessage("SmsCodeFilter.sms_code_expired"));
        }
        if (!newsmsInfo.getCode().equalsIgnoreCase(codeInRequest)) {
            throw new AuthenticationServiceException(getMessage("SmsCodeFilter.sms_code_error"));
        }
        //登录成功后删除缓存的短信码
        SmsGenerateUtil.remove(newsmsInfo);
    }

    private String getMessage(String code) {
        return I18nMessageUtil.getInstance().getMessage(code);
    }
}
