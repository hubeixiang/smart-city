package com.sct.service.database.security.service;

import com.sct.commons.i18n.I18nMessageUtil;
import com.sct.service.database.entity.ScManager;
import com.sct.service.database.entity.ScUser;
import com.sct.service.database.mapper.ScManagerMapper;
import com.sct.service.database.mapper.ScUserMapper;
import com.sct.service.users.data.entity.AuthorityUser;
import com.sct.service.users.security.SecurityUser;
import com.sct.service.users.security.UserEntity;
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
    private ScUserMapper scUserMapper;
    @Autowired
    private ScManagerMapper scManagerMapper;

    @Autowired
    private UsernameResolver usernameResolver;

    private boolean enableAuthorities = true;

    /**
     * username 在进行查询时用户登录主键
     * 按照不同的查询方式拼接username来区分从数据库中查询的检索主键信息
     * loginid:username 按照登录名查找
     * cellphone:username 按照手机号进行查找
     * userid:username 按照用户表的自增主键进行查找, 默认查找方式
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
        UserEntity userEntity = null;
        if (usernameResolverEnum == null) {
            //检索2张表的主键
            ScManager scManager = scManagerMapper.selectByPrimaryKey(findKey);
            if (scManager == null) {
                ScUser scUser = scUserMapper.selectByPrimaryKey(findKey);
                userEntity = scUser;
            } else {
                userEntity = scManager;
            }
        } else {
            switch (usernameResolverEnum) {
                case userPk:
                    userEntity = scUserMapper.selectByPrimaryKey(findKey);
                    break;
                case loginId:
                    userEntity = scUserMapper.selectByLoginId(findKey);
                    break;
                case cellPhone:
                    userEntity = scUserMapper.selectByMobile(findKey);
                    break;
                case email:
                    userEntity = scUserMapper.selectByEmail(findKey);
                    break;
                case mix_3:
                    userEntity = scUserMapper.selectByMixed(findKey);
                    break;
                case wxId:
                    userEntity = scUserMapper.selectBywxid(findKey);
                    break;
                case manager_userPk:
                    userEntity = scManagerMapper.selectByPrimaryKey(findKey);
                    break;
                case manager_loginId:
                    userEntity = scManagerMapper.selectByLoginId(findKey);
                    break;
                case manager_cellPhone:
                    userEntity = scManagerMapper.selectByMobile(findKey);
                    break;
                case manager_email:
                    userEntity = scManagerMapper.selectByEmail(findKey);
                    break;
                case manager_mix_3:
                    userEntity = scManagerMapper.selectByMixed(findKey);
                    break;
                case manager_wxId:
                    userEntity = scManagerMapper.selectBywxid(findKey);
                    break;
                default:
                    String msg = I18nMessageUtil.getInstance().getMessage("AuthorityUserDetailsService.NOT_FOUND_UsernameResolverEnum", usernameResolverEnum);
                    logger.error(msg);
                    throw new RuntimeException(msg);
            }
        }
        if (userEntity == null) {
            logger.debug(String.format("Query returned no results for user .type=%s,findKey=%s ", usernameResolverEnum, findKey));
            String msg = I18nMessageUtil.getInstance().getMessage("AuthorityUserDetailsService.NOT_FOUND_USER_FROM_DB", usernameResolverEnum == null ? "" : usernameResolverEnum);
            throw new UsernameNotFoundException(msg);
        }

        AuthorityUser authorityUser = createAuthorityUser(userEntity);
        Set<GrantedAuthority> dbAuthsSet = new HashSet<>();
        if (this.enableAuthorities) {
            dbAuthsSet.addAll(loadUserAuthorities(authorityUser, userEntity));
        }

        List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);
        return createUserDetails(authorityUser, dbAuths);
    }

    protected AuthorityUser createAuthorityUser(UserEntity userEntity) {
        if (userEntity instanceof ScUser) {
            //一般注册用户
            return createAuthorityUserByScUser((ScUser) userEntity);
        } else {
            //管理用户
            return createAuthorityUserByScManagerUser((ScManager) userEntity);
        }
    }

    private AuthorityUser createAuthorityUserByScUser(ScUser scUser) {
        AuthorityUser authorityUser = new AuthorityUser();
        authorityUser.setPkId(scUser.getId());
        authorityUser.setLoginId(scUser.getUserId());
        authorityUser.setUserEmail(scUser.getEmail());
        authorityUser.setUserMobile(scUser.getMobile());
        authorityUser.setWxId(scUser.getWxId());

        authorityUser.setUserName(scUser.getUserName());
        authorityUser.setPassword(scUser.getPassword());
        return authorityUser;
    }

    private AuthorityUser createAuthorityUserByScManagerUser(ScManager scManager) {
        AuthorityUser authorityUser = new AuthorityUser();
        authorityUser.setPkId(scManager.getId());
        authorityUser.setLoginId(scManager.getUserId());
        authorityUser.setUserEmail(scManager.getEmail());
        authorityUser.setUserMobile(scManager.getMobile());
        authorityUser.setWxId(scManager.getWxId());

        authorityUser.setUserName(scManager.getUserName());
        authorityUser.setPassword(scManager.getPassword());
        return authorityUser;
    }

    protected List<GrantedAuthority> loadUserAuthorities(AuthorityUser authorityUser, UserEntity userEntity) {
//        List<SecUserRole> secUserRoles = getSecUserRoleMapper().selectByUserId(securityUser.getUserId());
        return Collections.emptyList();
    }

    protected UserDetails createUserDetails(AuthorityUser authorityUser, List<GrantedAuthority> combinedAuthorities) {
        String pkId = authorityUser.getPkId();
        String loginId = authorityUser.getLoginId();
        String returnUsername = authorityUser.getUserName();
        String password = authorityUser.getPassword();
        if (StringUtils.isEmpty(password)) {
            password = "NONE_PASSWORD";
        }
        return new SecurityUser(pkId, loginId, returnUsername, password,
                authorityUser.isEnabled(), authorityUser.isAccountNonExpired(),
                authorityUser.isCredentialsNonExpired(), authorityUser.isAccountNonLocked(), combinedAuthorities,
                authorityUser);
    }
}
