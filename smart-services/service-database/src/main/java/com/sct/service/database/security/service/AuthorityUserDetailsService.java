package com.sct.service.database.security.service;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.service.database.mapper.SecUserMapper;
import com.sct.service.users.data.entity.AuthorityUser;
import com.sct.service.users.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 查询数据库中用户登录时的相关信息
 */
@Service("authorityUserDetailsService")
public class AuthorityUserDetailsService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(AuthorityUserDetailsService.class);

    @Autowired
    private SecUserMapper secUserMapper;

    @Autowired
    private UsernameResolver usernameResolver;

    private boolean enableAuthorities = true;

    /**
     * username 在进行查询时用户登录主键
     * 按照不同的查询方式拼接username来区分从数据库中查询的检索主键信息
     * loginid:username 按照登录名查找
     * cellphone:username 按照手机号进行查找
     * userid:username 按照用户表的自增主键进行查找
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsernameResolver.ResolverResult resolverResult = usernameResolver.resolver(username);
        String findKey = resolverResult.getResult();
        UsernameResolverEnum usernameResolverEnum = resolverResult.getUsernameResolverEnum();
        if (usernameResolverEnum == null) {
            usernameResolverEnum = UsernameResolverEnum.loginid;
        }
        AuthorityUser authorityUser = null;
        switch (usernameResolverEnum) {
            case loginid:
                authorityUser = secUserMapper.selectByLoginId(findKey);
                break;
            case cellphone:
                authorityUser = secUserMapper.selectByUserMobile(findKey);
                break;
            case userid:
                authorityUser = secUserMapper.selectByUserId(findKey);
                break;
            default:
                String msg = I18nMessageUtil.getInstance().getMessage("AuthorityUserDetailsService.NOT_FOUND_UsernameResolverEnum", usernameResolverEnum.toString());
                logger.error(msg);
                throw new RuntimeException(msg);
        }

        if (authorityUser == null) {
            logger.debug(String.format("Query returned no results for user .type=%s,findKey=%s ", usernameResolverEnum, findKey));
            String msg = I18nMessageUtil.getInstance().getMessage("AuthorityUserDetailsService.NOT_FIOUND_USER_FROM_DB", usernameResolverEnum.toString());
            throw new UsernameNotFoundException(msg);
        }

        Set<GrantedAuthority> dbAuthsSet = new HashSet<>();

        if (this.enableAuthorities) {
            dbAuthsSet.addAll(loadUserAuthorities(authorityUser));
        }

        List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);

        return createUserDetails(authorityUser, dbAuths);
    }

    protected List<GrantedAuthority> loadUserAuthorities(AuthorityUser authorityUser) {
//        List<SecUserRole> secUserRoles = getSecUserRoleMapper().selectByUserId(securityUser.getUserId());
        return Collections.emptyList();
    }

    protected UserDetails createUserDetails(AuthorityUser authorityUser, List<GrantedAuthority> combinedAuthorities) {
        String userId = authorityUser.getUserId();
        String loginId = authorityUser.getLoginId();
        String returnUsername = authorityUser.getUserName();
        String password = authorityUser.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = "NONE_PASSWORD";
        }
        return new SecurityUser(userId, loginId, returnUsername, password,
                authorityUser.isEnabled(), authorityUser.isAccountNonExpired(),
                authorityUser.isCredentialsNonExpired(), authorityUser.isAccountNonLocked(), combinedAuthorities,
                authorityUser);
    }

}
