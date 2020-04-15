package dev.wenxin.ais;

/**
 * 用于读取AIS字符串的解析器
 *
 * @author wenxin
 * @date 2018/7/6
 */
public interface AisParser<T> {

    /**
     * 签名校验
     * @param ais
     * @param data
     * @return
     */
    Ais parse(String ais, T data);

    /**
     * 设置密匙源
     * @param secretStore
     * @return
     */
    AisParser<T> setSecretStore(SecretStore secretStore);
}
