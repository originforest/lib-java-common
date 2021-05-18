/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package build.max.common.exception;

/**
 * Method not found exception
 *
 * @author pengxiang.li
 * @date. 2020/7/1 4:51 下午
 */
public class OfNotFoundException extends OfRuntimeException {
    private static final long serialVersionUID = 4773921674738374416L;

    public OfNotFoundException() {
        super();
    }

    public OfNotFoundException(String message) {
        super(message);
    }

    public OfNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OfNotFoundException(Throwable cause) {
        super(cause);
    }
}
