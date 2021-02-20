package com.sct.application.authorization.filter;

import com.sct.application.authorization.util.LoginPageConstants;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 长时间不操作的页面拦截过滤器
 * 1. 在登录页面长时间停留后,没有刷新登录页面，直接提交登录信息.
 * 此时因为，spring 里面 RequestCache 缓存的原始登录信息已经丢失了.会导致登录后不知道重定向的地址而异常
 */
public class LoginPageNotOperateForLongTimeFilter extends GenericFilterBean {
    private OrRequestMatcher requestMatcher;

    public LoginPageNotOperateForLongTimeFilter(List<RequestMatcher> requestMatchers) {
        this.requestMatcher = new OrRequestMatcher(requestMatchers);
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (support(request, response)) {
            doTimestampFilter(request, response);
        }

        chain.doFilter(request, response);
    }

    protected boolean support(HttpServletRequest request, HttpServletResponse response) {
        return requestMatcher.matches(request);
    }

    protected void doTimestampFilter(HttpServletRequest request, HttpServletResponse response) {
        String pageTimeStamp = request.getParameter(LoginPageConstants.login_page_open_timestamp_key);
        if (pageTimeStamp != null && pageTimeStamp.trim().length() > 0) {
            Long startTime = Long.parseLong(pageTimeStamp.trim());
            Long currentTime = System.currentTimeMillis();
            long wait = currentTime - startTime;
            if (wait >= 0 && wait <= 4 * 60 * 1000) {
                //页面在正常的时间范围类
                return;
            } else {
                throw new RuntimeException("长时间未操作请刷新页面,及时重新登录");
            }
        }
    }
}
