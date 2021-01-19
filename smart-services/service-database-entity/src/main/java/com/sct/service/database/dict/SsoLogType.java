/*
 *
 */

package com.sct.service.database.dict;

import com.sct.commons.utils.dict.Description;
import com.sct.commons.utils.dict.IdEnum;
import com.sct.service.database.entity.ScSsoLog;

/**
 * User SSO log type definition for {@link ScSsoLog}.
 *
 * @author
 * @since 1.0.0
 */
public enum SsoLogType implements IdEnum<SsoLogType> {
    /**
     * User SSO Log Type is login.
     */
    @Description("登录") LOGIN(1, "登录"),
    /**
     * User SSO Log Type is logout.
     */
    @Description("登出") LOGOUT(2, "登出"),
    /**
     * User SSO Log Type is expired.
     */
    @Description("过期") EXPIRED(3, "过期"),
    /**
     * User SSO Log Type is force logout.
     */
    @Description("强制退出") FORCE_LOGOUT(4, "强制退出"),
    /**
     * User SSO Log Type is force logout.
     */
    @Description("生成票据") CREATE_TICKET(5, "生成票据"),
    /**
     * User SSO Log Type is refresh ticket.
     */
    @Description("更新票据") REFRESH_TICKET(6, "更新票据"),
    /**
     * User SSO Log Type is destroy ticket.
     */
    @Description("销毁票据") DESTROY_TICKET(7, "销毁票据"),
    /**
     * User SSO Log Type is validate ticket.
     */
    @Description("校验票据") VALIDATE_TICKET(8, "校验票据"),
    /**
     * Oauth2.0 的AuthCode认证时获取code操作
     */
    @Description("获取认证code") AUTHORIZATION_CODE(9, "获取认证code");

    private int id;
    private String description;

    SsoLogType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static SsoLogType getType(Integer id) {
        return IdEnum.getType(SsoLogType.class, id);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
