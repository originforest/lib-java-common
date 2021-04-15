package com.originforest.common.constant;

/**
 * 用户相关常量
 *
 * @author rjf
 * @since 2021/3/20
 */
public final class UserConstant {

    /**
     * 用户ID标识
     */
    public static final String HEADER_KEY_USER_ID = "of-uid";

    /**
     * 租户ID标识
     */
    public static final String HEADER_KEY_TENANT_ID = "of-tid";

    /**
     * 用户类型
     */
    public static final String HEADER_KEY_USER_TYPE = "of-ut";

    /**
     * 用户角色集合
     */
    public static final String HEADER_KEY_USER_ROLES = "of-roles";

    /**
     * JWT签名
     */
    public static final String HEADER_KEY_JWT_SIGNATURE = "jwt-signature";

    /**
     * Redis Jwt失效记录
     */
    public static final String REDIS_KEY_JWT_EXPIRES = "jwt:expires";

    /**
     * 登出
     */
    public static final String URL_SUFFIX_LOGOUT = "/logout";

}
