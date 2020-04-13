package com.wenxinnnnn.ais.impl;

import com.wenxinnnnn.ais.NonceHandler;

/**
 * 默认AIS解析器，支持String数据签名
 *
 * @author chenqing
 * @date 2018/7/6
 */
public class DefaultAisParser extends AbstractAisParser<String> {

    public DefaultAisParser() {
    }

    public DefaultAisParser(NonceHandler nonceHandler) {
        super(nonceHandler);
    }

    public DefaultAisParser(long effective, NonceHandler nonceHandler) {
        super(effective, nonceHandler);
    }

    @Override
    public String translate(String object) {
        return object == null ? "" : object;
    }

}
