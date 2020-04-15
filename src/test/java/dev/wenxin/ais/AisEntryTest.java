package dev.wenxin.ais;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author chenqing
 * @date 2018/7/10
 */
public class AisEntryTest {

    final String appId = "9e2f9509-1913-41b9-ad8b-4f16d0081ea4";
    final String secret = "135dfd83-838e-4898-8f8b-f4ba4480de51";
    long now = System.currentTimeMillis();
    final String nonce = "1111";

    @Test
    public void testBuilderAndParse() {

        String data = "GET /user-center";
        Header header = new Header(SignatureAlgorithm.HS256);
        Signature signature = new Signature(appId, now, nonce);
        String ais = AisEntry.builder()
                .setHeader(header)
                .setSignature(signature)
                .setSecretStore(appId -> secret)
                .compact(data);

        System.out.println(ais);


        Ais aisObject = AisEntry.parser()
                .setSecretStore(appId -> secret)
                .parse(ais, data);


        assertEquals(JSON.toJSONString(aisObject.getHeader()), JSON.toJSONString(header));
        assertEquals(JSON.toJSONString(aisObject.getSignature()), JSON.toJSONString(signature));


    }

    @Test
    public void testBuilderHS384AndParse() {

        String data = "POST /first/wenxin a=111&b=222";
        Header header = new Header(SignatureAlgorithm.HS384);
        Signature signature = new Signature(appId, now, nonce);
        String ais = AisEntry.builder()
                .setHeader(header)
                .setSignature(signature)
                .setSecretStore(appId -> secret)
                .compact(data);

        System.out.println(ais);

        Ais aisObject = AisEntry.parser()
                .setSecretStore(appId -> secret)
                .parse(ais, data);

        System.out.println(JSON.toJSONString(aisObject.getHeader()));
        System.out.println(JSON.toJSONString(aisObject.getSignature()));

        assertEquals(aisObject.getHeader().getAlg(), header.getAlg());
    }

    @Test
    public void testBuilderHS512AndParse() {

        Header header = new Header(SignatureAlgorithm.HS512);
        Signature signature = new Signature(appId, now, nonce);
        String ais = AisEntry.builder()
                .setHeader(header)
                .setSignature(signature)
                .setSecretStore(appId -> secret)
                .compact("GET /user/username/111?a=asfja&b=222");

        System.out.println(ais);

        Ais aisObject = AisEntry.parser()
                .setSecretStore(appId -> secret)
                .parse(ais, "GET /user/username/111?a=asfja&b=222");

        System.out.println(JSON.toJSONString(aisObject.getHeader()));
        System.out.println(JSON.toJSONString(aisObject.getSignature()));

        assertEquals(aisObject.getHeader().getAlg(), header.getAlg());
    }

    @Test
    public void testTime() {

        now = System.currentTimeMillis() - (181 * 1000);
        Header header = new Header(SignatureAlgorithm.HS512);
        Signature signature = new Signature(appId, now, nonce);
        String ais = AisEntry.builder()
                .setHeader(header)
                .setSignature(signature)
                .setSecretStore(appId -> secret)
                .compact("GET /user/username/111?a=asfja&b=222");

        try {
            AisEntry.parser()
                    .setSecretStore(appId -> secret)
                    .parse(ais, "GET /user/username/111?a=asfja&b=222");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals("签名超时!"));
        }

    }

}
