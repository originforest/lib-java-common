/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package com.originforest.common.exception;

/**
 * Json parse and stringify Exception
 *
 * @author pengxiang.li
 * @date 2020/7/1 4:51 下午
 */
public class OfJsonException extends OfException {
    private static final long serialVersionUID = -5996617354987319003L;

    public OfJsonException() {
        super();
    }

    public OfJsonException(String message) {
        super(message);
    }

    public OfJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfJsonException(Throwable cause) {
        super(cause);
    }
}
