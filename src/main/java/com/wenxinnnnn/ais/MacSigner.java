package com.wenxinnnnn.ais;

import com.wenxinnnnn.ais.exception.SignatureException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.wenxinnnnn.ais.utils.Assert.hasText;
import static com.wenxinnnnn.ais.utils.Assert.isTrue;

/**
 * HMAC 加密算法实现类
 *
 * @author chenqing
 * @date 2018/7/6
 */
public class MacSigner implements Signer {

    private SignatureAlgorithm alg;
    private Key key;

    public MacSigner(SignatureAlgorithm alg, String secret) {
        hasText(secret, "secret cannot be null or empty.");
        isTrue(alg.isHmac(), "MacSigner仅适用于HMAC signatures.");
        this.alg = alg;
        this.key = new SecretKeySpec(Base64.getEncoder().encode(secret.getBytes()), alg.getJcaName());
    }

    @Override
    public String sign(String data) throws SignatureException {
        try {
            Mac mac = Mac.getInstance(alg.getJcaName());
            mac.init(key);
            byte[] bytes = mac.doFinal(data.getBytes(AisConstant.STRING_CHARSET_NAME));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (UnsupportedEncodingException e) {
            throw new SignatureException("signnature仅支持utf-8编码");
        } catch (NoSuchAlgorithmException e) {
            String msg = "Unable to obtain JCA MAC algorithm '" + alg.getJcaName() + "': " + e.getMessage();
            throw new SignatureException(msg, e);
        } catch (InvalidKeyException e) {
            String msg = "The specified signing key is not a valid " + alg.name() + " key: " + e.getMessage();
            throw new SignatureException(msg, e);
        }
    }

}
