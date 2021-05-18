/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package build.max.common.exception;

/**
 * @author pengxiang.li
 * @date. 2020/7/1 4:51 下午
 */
public class OfRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -1253151488039971417L;

    public OfRuntimeException() {
        super();
    }

    public OfRuntimeException(String message) {
        super(message);
    }

    public OfRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfRuntimeException(Throwable cause) {
        super(cause);
    }
}
