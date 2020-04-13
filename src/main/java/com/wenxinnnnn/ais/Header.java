package com.wenxinnnnn.ais;

/**
 * AIS的第一部分，头部信息
 *
 * @author chenqing
 * @date 2018/7/6
 */
public class Header {

    /**
     * 算法
     */
    private SignatureAlgorithm alg;

    public Header() {
    }

    public Header(SignatureAlgorithm alg) {
        this.alg = alg;
    }

    public Header(String alg) {
        this.alg = SignatureAlgorithm.forName(alg);
    }

    public SignatureAlgorithm getAlg() {
        return alg;
    }

    public void setAlg(SignatureAlgorithm alg) {
        this.alg = alg;
    }
}
