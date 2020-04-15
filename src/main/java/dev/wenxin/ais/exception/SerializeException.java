package dev.wenxin.ais.exception;

/**
 * AIS数据序列化失败异常
 *
 * @author chenqing
 * @date 2018/7/9
 */
public class SerializeException extends AisException {

    public SerializeException(String message) {
        super(message);
    }

    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
}
