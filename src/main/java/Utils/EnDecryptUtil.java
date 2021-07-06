package Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

/**
 * EnDecryptUtil.
 * 加密解密工具类
 *
 * @author
 * 目前包含的加解密算法：AES加密/解密，BASE64加密/解密，MD5 32位大小写/16位大小写加密
 */
public class EnDecryptUtil {

    /**
     * EncryptAes.
     *
     * @param source 被加密的明文
     * @param key    加密密钥
     * @param iv     初始化向量，如果为空则使用ECB模式，否则采用CBC模式，且长度必须为8
     * @return 加密后密文的字符串形式
     */
    public static byte[] encryptAes(byte[] source, byte[] key, byte[] iv) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(key));
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(source);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * DecryptAes.
     *
     * @param source 密文
     * @param key    加密密钥
     * @param iv     初始化向量，如果为空则使用ECB模式，否则采用CBC模式，且长度必须为8
     * @return 解密的字符串形式
     */
    public static byte[] decryptAes(byte[] source, byte[] key, byte[] iv) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(key));//key长可设为128，192，256位，这里只能设为128
            SecretKey secretKey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(source);
            return result;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Parses the byte 2 hex str.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String parseByte2HexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length);
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * Parses the hex str 2 byte.
     *
     * @param hex the hex
     * @return the byte[]
     */
    public static byte[] parseHexStr2Byte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * To byte.
     *
     * @param c the c
     * @return the byte
     */
    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * BASE64 加密
     *
     * @param str
     * @return
     */
    public static String encryptBase64(String str) {
        byte[] strByte = null;
        String result = null;
        try {
            strByte = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] encodeBase64 = Base64.encodeBase64(strByte);
        if (encodeBase64 != null) {
            result = new String(encodeBase64);
        }
        return result;
    }
@Test
public  void tt(){
    System.out.println(parseStrToMd5L32("25d55ad283aa400af464c76d713c07ad"));//12345678
    System.out.println(parseStrToMd5L32("141c4e962fc9b5079dbfb10136e611ac"));//aa1234567
    System.out.println(parseStrToMd5L32("85bf5831e593431e882887e077400b7f"));//aa111111
    System.out.println(parseStrToMd5L32("13010000030"));//13010000000
    System.out.println(parseStrToMd5L32("ids=114794443862835200&key=2bb9966c702e49048defbd0074c77888"));//aa111111
    System.out.println(System.currentTimeMillis());
}


    @Test
    public  void tt1(){
        for (Integer i=10;i<30;i++){
            String phone="130000000" + i;
            //System.out.println(phone);
            System.out.println(parseStrToMd5L32(phone.toString()));
        }
//1300000001
//13000000001
    }



    /**
     * BASE64 解密
     *
     * @param str
     * @return
     */
    public static String decryptBase64(String str) {
        byte[] strByte = null;
        String result = null;
        try {
            strByte = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] encodeBase64 = Base64.decodeBase64(strByte);
        if (encodeBase64 != null) {
            result = new String(encodeBase64);
        }
        return result;
    }

    /**
     * 32位小写MD5加密
     *
     * @param str
     * @return
     * @Description: 32位小写MD5
     */
    public static String parseStrToMd5L32(String str) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(str.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * 32位大写MD5加密
     *
     * @param str
     * @return
     * @Description: 32位大写MD5
     */
    public static String parseStrToMd5U32(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase();
        }
        return reStr;
    }

    /**
     * 16位小写MD5加密.
     *
     * @param str
     * @return
     * @Description: 16位小写MD5
     */
    public static String parseStrToMd5U16(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.toUpperCase().substring(8, 24);
        }
        return reStr;
    }

    /**
     * 16位大写MD5.
     *
     * @param str
     * @return
     * @Description: 16位大写MD5
     */
    public static String parseStrToMd5L16(String str) {
        String reStr = parseStrToMd5L32(str);
        if (reStr != null) {
            reStr = reStr.substring(8, 24);
        }
        return reStr;
    }


}
