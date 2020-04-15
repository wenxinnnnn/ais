package dev.wenxin.ais;

/**
 * 应用签名实体
 *
 * @author wenxin
 * @date 2018/7/6
 */
public class Ais {

    /**
     * 头部
     */
    private Header header;
    /**
     * 签名体
     */
    private Signature signature;

    public Ais(Header header, Signature signature) {
        this.header = header;
        this.signature = signature;
    }

    public Header getHeader() {
        return header;
    }

    public Signature getSignature() {
        return signature;
    }
}
