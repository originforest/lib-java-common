/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package com.originforest.common.exception;

/**
 * Msg Exception
 *
 * @author pengxiang.li
 * @date 2020/7/1 4:51 下午
 */
public class OfMsgException extends OfRuntimeException {
    private static final long serialVersionUID = -1481460277551765527L;

    public OfMsgException() {
        super();
    }

    public OfMsgException(String message) {
        super(message);
    }

    public OfMsgException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfMsgException(Throwable cause) {
        super(cause);
    }
}
