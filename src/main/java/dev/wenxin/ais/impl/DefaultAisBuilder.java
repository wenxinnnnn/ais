package dev.wenxin.ais.impl;

/**
 * 默认AIS构造器，支持String数据签名
 *
 * @author wenxin
 * @date 2018/7/6
 */
public class DefaultAisBuilder extends AbstractAisBuilder<String> {

    @Override
    public String translate(String object) {
        return object == null ? "" : object;
    }

}
