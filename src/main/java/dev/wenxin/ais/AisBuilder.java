package dev.wenxin.ais;

/**
 * 用于构建AIS的生成器
 *
 * @author wenxin
 * @date 2018/7/6
 */
public interface AisBuilder<T> {

    /**
     * 设置头部
     * @param header
     * @return
     */
    AisBuilder<T> setHeader(Header header);

    /**
     * 设置签名体
     * @param signature
     * @return
     */
    AisBuilder<T> setSignature(Signature signature);

    /**
     * 设置密匙库
     * @param secretStore
     * @return
     */
    AisBuilder<T> setSecretStore(SecretStore secretStore);

    /**
     * 签名构建
     * @param data
     * @return
     */
    String compact(T data);
}

