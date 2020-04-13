package com.wenxinnnnn.ais;

import com.wenxinnnnn.ais.exception.SignatureException;

/**
 * 签名接口
 *
 * @author chenqing
 * @date 2018/7/6
 */
public interface Signer {

    /**
     * 对数据进行签名
     *
     * @param data
     * @return
     * @throws SignatureException
     */
    String sign(String data) throws SignatureException;
}