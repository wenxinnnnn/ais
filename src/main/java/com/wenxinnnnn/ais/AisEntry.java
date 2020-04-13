package com.wenxinnnnn.ais;

import com.wenxinnnnn.ais.impl.DefaultAisBuilder;
import com.wenxinnnnn.ais.impl.DefaultAisParser;

/**
 * 应用接口签名(application interface signature)入口类，整合功能，方便使用
 *
 * @author chenqing
 * @date 2018/7/6
 */
public class AisEntry {

    /**
     * 构建默认AIS解析器，不判断接口重放问题
     *
     * @return
     */
    public static AisParser<String> parser() {
        return new DefaultAisParser();
    }

    /**
     * 构建默认AIS解析器，自定义防重放处理器
     *
     * @param nonceHandler
     * @return
     */
    public static AisParser<String> parser(NonceHandler nonceHandler) {
        return new DefaultAisParser(nonceHandler);
    }

    /**
     * 构建AIS构造器
     *
     * @return
     */
    public static AisBuilder<String> builder() {
        return new DefaultAisBuilder();
    }

}
