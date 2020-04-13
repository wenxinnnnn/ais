package com.wenxinnnnn.ais.impl;

import com.wenxinnnnn.ais.*;
import com.wenxinnnnn.ais.exception.SignatureException;
import com.wenxinnnnn.ais.exception.StructureException;
import com.wenxinnnnn.ais.utils.Base64Builder;
import com.wenxinnnnn.ais.utils.Strings;

import static com.wenxinnnnn.ais.utils.Assert.hasText;

/**
 * AIS解析器抽象接口
 *
 * @author chenqing
 * @date 2018/7/6
 */
public abstract class AbstractAisParser<T> implements AisParser<T> {

    /**
     * 签名有效期（s）
     */
    protected long effective;

    /**
     * 防重放处理器
     */
    protected NonceHandler nonceHandler;

    /**
     * 密匙源
     */
    protected SecretStore secretStore;

    /**
     * 默认签名有效期3分钟，默认不判断接口重放问题
     */
    public AbstractAisParser() {
        this(new DefaultNonceHandler());
    }

    /**
     * 默认签名有效期3分钟
     *
     * @param nonceHandler
     */
    public AbstractAisParser(NonceHandler nonceHandler) {
        this(180, nonceHandler);
    }

    public AbstractAisParser(long effective, NonceHandler nonceHandler) {
        this.effective = effective;
        this.nonceHandler = nonceHandler;
    }

    @Override
    public AisParser<T> setSecretStore(SecretStore secretStore) {
        this.secretStore = secretStore;
        return this;
    }

    public long getEffective() {
        return effective;
    }

    public void setEffective(long effective) {
        this.effective = effective;
    }

    @Override
    public Ais parse(String ais, T data) {

        hasText(ais, "Ais String argument cannot be null or empty.");
        if (secretStore == null) {
            throw new IllegalStateException("secret源不能为null");
        }

        //拆分签名
        //1 headre;2 signature;3 digest
        String[] splitAis = new String[3];
        int delimiterCount = 0;
        StringBuilder sb = new StringBuilder(128);
        for (char c : ais.toCharArray()) {
            if (c == AisConstant.SEPARATOR_CHAR) {
                CharSequence tokenSeq = Strings.clean(sb);
                String token = tokenSeq != null ? tokenSeq.toString() : null;
                splitAis[delimiterCount] = token;
                delimiterCount++;
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (delimiterCount != 2) {
            String msg = "AIS字符串必须包含2个隔断符号“.”";
            throw new StructureException(msg);
        }
        splitAis[2] = sb.toString();

        Header header = Base64Builder.decode(splitAis[0], Header.class, "header信息base64解析错误！");
        Signature signature = Base64Builder.decode(splitAis[1], Signature.class, "signature信息解析错误！");
        String base64Data = Base64Builder.encode(this.translate(data), "data信息base64编码错误");

        String aisWithoutDegest = splitAis[0] + AisConstant.SEPARATOR_CHAR + splitAis[1] + AisConstant.SEPARATOR_CHAR;

        String secret = secretStore.getSecret(signature.getAppId());
        Signer signer = SignerFactory.createSigner(header.getAlg(), secret);
        String digest = signer.sign(aisWithoutDegest + base64Data);

        //签名异常
        if (!digest.equals(splitAis[2])) {
            throw new SignatureException("验签失败!");
        }
        //校验时间差
        if (System.currentTimeMillis() - signature.getTimestamp() > effective * 1000) {
            throw new SignatureException("签名超时!");
        }
        //校验随机数
        if (nonceHandler != null && !nonceHandler.handle(signature.getAppId(), signature.getNonce())) {
            throw new SignatureException("重复请求接口!");
        }

        return new Ais(header, signature);
    }

    /**
     * 自定义数据转换器
     *
     * @param object
     * @return
     */
    public abstract String translate(T object);
}
