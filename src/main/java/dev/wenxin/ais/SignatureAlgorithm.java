package dev.wenxin.ais;

import dev.wenxin.ais.exception.SignatureException;

public enum SignatureAlgorithm {

    /**
     * 没有数字签名或MAC执行
     */
    NONE("none", null, null),

    /**
     * HMAC SAG-256
     */
    HS256("HS256", "HMAC", "HmacSHA256"),

    /**
     * HMAC SAG-384
     */
    HS384("HS384", "HMAC", "HmacSHA384"),

    /**
     * HMAC SAG-512
     */
    HS512("HS512", "HMAC", "HmacSHA512"),

    /**
     * RSASSA-PKCS-v1_5 SHA-256
     */
    RS256("RS256", "RSA", "SHA256withRSA"),

    /**
     * RSASSA-PKCS-v1_5 SHA-384
     */
    RS384("RS384", "RSA", "SHA384withRSA"),

    /**
     * RSASSA-PKCS-v1_5 SHA-512
     */
    RS512("RS512", "RSA", "SHA512withRSA");


    private final String value;
    private final String familyName;
    private final String jcaName;

    SignatureAlgorithm(String value, String familyName, String jcaName) {
        this.value = value;
        this.familyName = familyName;
        this.jcaName = jcaName;
    }

    public String getValue() {
        return value;
    }


    public String getFamilyName() {
        return familyName;
    }

    public String getJcaName() {
        return jcaName;
    }


    public boolean isHmac() {
        return getFamilyName().equals("HMAC");
    }

    public boolean isRsa() {
        return getFamilyName().equals("RSA");
    }

    public static SignatureAlgorithm forName(String value) throws SignatureException {
        for (SignatureAlgorithm alg : values()) {
            if (alg.getValue().equalsIgnoreCase(value)) {
                return alg;
            }
        }

        throw new SignatureException("不支持签名算法'" + value + "'");
    }
}
