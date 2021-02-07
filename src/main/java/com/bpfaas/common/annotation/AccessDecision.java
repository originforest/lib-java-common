package com.bpfaas.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 访问策略
 *
 * @author rjf
 * @since 2021/2/2
 */
@Target({
        ElementType.TYPE,
        ElementType.METHOD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessDecision {

    /**
     * 是否可以任何人访问
     *
     * @return 默认不可以
     */
    boolean anyUser() default false;

}
