/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package build.max.common.exception;

/**
 * Execute Command Exception
 *
 * @author pengxiang.li
 * @date. 2020/7/1 4:51 下午
 */
public class OfExecuteCommandException extends OfException {
    private static final long serialVersionUID = -5996617354987319003L;

    public OfExecuteCommandException() {
        super();
    }
    public OfExecuteCommandException(String message) {
        super(message);
    }
    public OfExecuteCommandException(String message, Throwable cause) {
        super(message, cause);
    }
    public OfExecuteCommandException(Throwable cause) {
        super(cause);
    }
}
