package dev.wenxin.ais;

import java.util.UUID;

/**
 * AIS的第二部分，签名体
 *
 * @author wenxin
 * @date 2018/7/6
 */
public class Signature {

    /**
     * 请求应用id
     */
    private String appId;

    /**
     * 请求时间戳
     */
    private long timestamp;

    /**
     * 随机数
     */
    private String nonce;

    /**
     * 构建签名体，默认当前时间戳，默认uuid做随机值
     *
     * @param appId
     */
    public Signature(String appId) {
        this.appId = appId;
        this.timestamp = System.currentTimeMillis();
        this.nonce = UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 构建签名体
     *
     * @param appId
     * @param timestamp
     * @param nonce
     */
    public Signature(String appId, long timestamp, String nonce) {
        this.appId = appId;
        this.timestamp = timestamp;
        this.nonce = nonce;
    }

    public Signature() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
