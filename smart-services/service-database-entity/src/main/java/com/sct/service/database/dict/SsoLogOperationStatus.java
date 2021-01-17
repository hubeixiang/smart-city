/*
 *
 */

package com.sct.service.database.dict;

import com.sct.commons.utils.dict.Description;
import com.sct.commons.utils.dict.IdEnum;
import com.sct.service.database.entity.ScSsoLog;

/**
 * User SSO log operation status definition for {@link ScSsoLog}.
 *
 * @author
 * @since 1.0.0
 */
public enum SsoLogOperationStatus implements IdEnum<SsoLogOperationStatus> {
    /**
     * User SSO Log Operation Status is success.
     */
    @Description("成功") SUCCESS(1),
    /**
     * User SSO Log Operation Status is failed.
     */
    @Description("失败") FAILED(2);

    private int id;

    SsoLogOperationStatus(int id) {
        this.id = id;
    }

    public static SsoLogOperationStatus getType(Integer id) {
        return IdEnum.getType(SsoLogOperationStatus.class, id);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
