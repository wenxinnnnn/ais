package dev.wenxin.ais;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author wenxin
 * @date 2018/7/12
 */
public class DefinedNonceRulesTest {

    final String secret = "123456";
    final String appId = "wenxin";
    long now = System.currentTimeMillis();
    final String nonce = "1111";

    @Test
    public void testDefinedNonceHandler1() {
        Header header = new Header(SignatureAlgorithm.HS256);
        Signature signature = new Signature(appId, now, nonce);
        String ais = AisEntry.builder()
                .setHeader(header)
                .setSignature(signature)
                .setSecretStore(appId -> secret)
                .compact("GET /user/username/111?a=asfja&b=222");

        try {
            AisEntry.parser(new DefinedNonceHandler1())
                    .setSecretStore(appId -> "123456")
                    .parse(ais, "GET /user/username/111?a=asfja&b=222");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("重复请求接口!"));
        }

    }

    class DefinedNonceHandler1 implements NonceHandler {

        @Override
        public boolean handle(String appId, String nonce) {
            //拒绝
            return false;
        }
    }

    @Test
    public void testDefinedNonceHandler2() {
//        Header header = new Header(SignatureAlgorithm.HS256);
//        Signature signature = new Signature(appId, now, "11122");
//        String ais = AisEntry.builder()
//                .setHeader(header)
//                .setSignature(signature)
//                .setSecretStore(appId -> secret)
//                .compact("GET /user/username/111?a=asfja&b=222");
//
//        try {
//            Ais parse = AisEntry.parser(new DefinedNonceHandler2())
//                    .setSecretStore(appId -> "123456")
//                    .parse(ais, "GET /user/username/111?a=asfja&b=222");
//
//            assertNotEquals(JSON.toJSONString(parse),"");
//        } catch (Exception e) {
//            assertTrue(e.getMessage().equals("重复请求接口!"));
//        }

    }

    class DefinedNonceHandler2 implements NonceHandler {

        @Override
        public boolean handle(String appId, String nonce) {
            boolean b = DefinedNonceRulesTest.this.appId.equals(appId) && DefinedNonceRulesTest.this.nonce.equals(nonce);
            return b;
        }
    }

}
