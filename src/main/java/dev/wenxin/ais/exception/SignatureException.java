package dev.wenxin.ais.exception;

/**
 * AIS签名异常
 *
 * @author chenqing
 * @date 2018/7/9
 */
public class SignatureException extends AisException {

    public SignatureException(String message) {
        super(message);
    }

    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
