/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package com.originforest.common.exception;

/**
 * @author pengxiang.li
 * @date 2020/7/1 4:51 下午
 */
public class OfException extends Exception {
    private static final long serialVersionUID = -1259159488039971417L;

    public OfException() {
        super();
    }

    public OfException(String message) {
        super(message);
    }

    public OfException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfException(Throwable cause) {
        super(cause);
    }
}
