/**
 * Copyright (c) 2020 Copyright Originforest All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/7/1 16:51
 * Desc:
 */
package build.max.common.exception;

import lombok.Getter;

/**
 * @author pengxiang.li
 * @date. 2020/7/1 4:51 下午
 */
public class OfErrSubcodeException extends RuntimeException {
    private static final long serialVersionUID = -1259159488139971417L;

    /**
     * 错误代码.
     */
    @Getter
    private String errSubCode;

    public OfErrSubcodeException() {
        super();
    }
    public OfErrSubcodeException(String errSubCode) {
        super(errSubCode);
        this.errSubCode = errSubCode;
    }
    public OfErrSubcodeException(String errSubCode, Throwable cause) {
        super(errSubCode, cause);
        this.errSubCode = errSubCode;
    }
    public OfErrSubcodeException(Throwable cause) {
        super(cause);
    }
}
