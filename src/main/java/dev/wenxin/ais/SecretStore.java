package dev.wenxin.ais;

/**
 * 密匙仓库，实现接口实现密匙来源
 *
 * @author wenxin
 * @date 2018/7/19
 */
public interface SecretStore {

    /**
     * 通过appId获取appSecret
     * @param appId
     * @return appSecret
     */
    String getSecret(String appId);
}
