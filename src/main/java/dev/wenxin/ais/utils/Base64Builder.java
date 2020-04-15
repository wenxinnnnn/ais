package dev.wenxin.ais.utils;

import com.alibaba.fastjson.JSON;
import dev.wenxin.ais.exception.SerializeException;

import java.util.Base64;

/**
 * @author wenxin
 * @date 2018/7/6
 */
public class Base64Builder {

    public static final String BASE64_CHARSET_NAME = "UTF-8";

    public static String encode(Object o, String errMsg) {
        String headerJson = JSON.toJSONString(o);
        return encode(headerJson, errMsg);
    }

    public static String encode(String json, String errMsg) {
        final Base64.Encoder encoder = Base64.getEncoder();
        try {
            final byte[] textByte = json.getBytes(BASE64_CHARSET_NAME);
            final String encodedText = encoder.encodeToString(textByte);
            return encodedText;
        } catch (Exception e) {
            throw new SerializeException(errMsg);
        }
    }

    public static <T> T decode(String string, Class<T> clazz, String errMsg) {
        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] decodeByte = decoder.decode(string);
            return JSON.parseObject(decodeByte, clazz);
        } catch (Exception e) {
            throw new SerializeException(errMsg);
        }
    }

    public static String decode(String string, String errMsg) {
        final Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] decodeByte = decoder.decode(string);
            return new String(decodeByte, BASE64_CHARSET_NAME);
        } catch (Exception e) {
            throw new SerializeException(errMsg);
        }
    }


}
