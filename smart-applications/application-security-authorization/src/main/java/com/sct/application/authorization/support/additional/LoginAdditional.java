package com.sct.application.authorization.support.additional;

public interface LoginAdditional<T> {
    /**
     * 获取登录方式
     *
     * @return
     */
    public LoginType getLoginType();

    public void setLoginType(LoginType loginType);

    /**
     * 获取登录时缓存的相关数据
     *
     * @return
     */
    public T getLoginData();

    public void setLoginData(T data);
}
