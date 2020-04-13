package com.wenxinnnnn.ais.exception;

/**
 * AIS结构异常
 *
 * @author chenqing
 * @date 2018/7/9
 */
public class StructureException extends AisException {

    public StructureException(String message) {
        super(message);
    }

    public StructureException(String message, Throwable cause) {
        super(message, cause);
    }
}
