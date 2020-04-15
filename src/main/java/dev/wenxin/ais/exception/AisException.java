package dev.wenxin.ais.exception;

/**
 * AIS通用异常
 *
 * @author wenxin
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
