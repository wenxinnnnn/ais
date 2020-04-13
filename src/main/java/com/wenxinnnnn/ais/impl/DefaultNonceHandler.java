package com.wenxinnnnn.ais.impl;

import com.wenxinnnnn.ais.NonceHandler;

/**
 * 默认接口签名防重放处理器，不校验接口重放
 *
 * @author chenqing
 * @date 2018/7/9
 */
public class DefaultNonceHandler implements NonceHandler {
    @Override
    public boolean handle(String appId, String nonce) {
        return true;
    }
}
