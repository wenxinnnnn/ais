package com.wenxinnnnn.ais.exception;

/**
 * AIS通用异常
 *
 * @author chenqing
 * @date 2018/7/9
 */
public class AisException extends RuntimeException {

    public AisException(String message) {
        super(message);
    }

    public AisException(String message, Throwable cause) {
        super(message, cause);
    }
}
