package dev.wenxin.ais;

import dev.wenxin.ais.utils.Assert;

import static dev.wenxin.ais.utils.Assert.notNull;

/**
 * 签名算法工厂类，通过算法，构建签名工具
 *
 * @author wenxin
 * @date 2018/7/6
 */
public class SignerFactory {

    public static Signer createSigner(SignatureAlgorithm alg, String secret) {

        Assert.notNull(alg, "SignatureAlgorithm cannot be null.");
        Assert.notNull(secret, "secret cannot be null.");

        switch (alg) {
            case HS256:
            case HS384:
            case HS512:
                return new MacSigner(alg, secret);
            default:
                throw new IllegalArgumentException("尚未支持该算法，alg：" + alg.name() + "。");

        }

    }
}
