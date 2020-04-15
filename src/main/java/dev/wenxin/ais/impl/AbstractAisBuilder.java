package dev.wenxin.ais.impl;

import dev.wenxin.ais.*;
import dev.wenxin.ais.utils.Assert;
import dev.wenxin.ais.utils.Base64Builder;

/**
 * @author wenxin
 * @date 2018/7/12
 */
public abstract class AbstractAisBuilder<T> implements AisBuilder<T> {

    protected Header header;
    protected Signature signature;
    protected SecretStore secretStore;

    @Override
    public AisBuilder<T> setHeader(Header header) {
        Assert.notNull(header, "header参数不能为null");
        this.header = header;
        return this;
    }

    @Override
    public AisBuilder<T> setSignature(Signature signature) {
        Assert.notNull(signature, "signature参数不能为null");
        this.signature = signature;
        return this;
    }

    @Override
    public AisBuilder<T> setSecretStore(SecretStore secretStore) {
        this.secretStore = secretStore;
        return this;
    }

    @Override
    public String compact(T object) {

        if (signature == null) {
            throw new IllegalStateException("signature参数不能为null");
        }
        if (header == null) {
            header = new Header(SignatureAlgorithm.HS256);
        }
        if (secretStore == null) {
            throw new IllegalStateException("secret源不能为null");
        }

        String base64Header = Base64Builder.encode(header, "header信息base64编码错误");
        String base64Signature = Base64Builder.encode(signature, "signature信息base64编码错误");
        String base64Data = Base64Builder.encode(this.translate(object), "data信息base64编码错误");

        String aisWithoutDegest = base64Header + AisConstant.SEPARATOR_CHAR + base64Signature + AisConstant.SEPARATOR_CHAR;

        String secret = secretStore.getSecret(signature.getAppId());
        Signer signer = SignerFactory.createSigner(header.getAlg(), secret);
        String digest = signer.sign(aisWithoutDegest + base64Data);

        return aisWithoutDegest + digest;
    }

    /**
     * 自定义数据转换器
     *
     * @param object
     * @return
     */
    public abstract String translate(T object);


}
