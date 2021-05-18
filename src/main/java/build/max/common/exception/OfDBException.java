/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 17:30
 * Desc:
 */
package build.max.common.exception;

/**
 * Database Exception
 *
 * @author pengxiang.li
 * @date. 2020/7/1 5:30 下午
 */
public class OfDBException extends OfException {
	private static final long serialVersionUID = 1749850513565294722L;

	public OfDBException() { super(); }
    public OfDBException(String message) {
        super(message);
    }
    public OfDBException(String message, Throwable cause) {
        super(message, cause);
    }
    public OfDBException(Throwable cause) {
        super(cause);
    }
}
