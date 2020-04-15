package dev.wenxin.ais;

/**
 * 防重放处理器
 *
 * @author chenqing
 * @date 2018/7/9
 */
public interface NonceHandler {

    /**
     * 处理器
     *
     * @param appId
     * @param nonce
     * @return
     */
    boolean handle(String appId, String nonce);
}
