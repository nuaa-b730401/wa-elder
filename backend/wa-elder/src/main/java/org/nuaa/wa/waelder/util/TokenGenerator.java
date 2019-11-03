package org.nuaa.wa.waelder.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.nuaa.wa.waelder.util.constant.ConfigConstant;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

/**
 * @Name: TokenGenerator
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-26 10:57
 * @Version: 1.0
 */
public class TokenGenerator {
    private String pubKey = "";
    private String priKey = "";
    private Random random;
    private static Logger logger = LoggerFactory.getLogger(LogLevel.SERVICE);

    private TokenGenerator() {
        try {
            genKeyPair();
            random = new Random();
        } catch (NoSuchAlgorithmException e) {
            logger.error("gen key pair fail", e);
        }
    }

    private static final class Holder {
        public static final TokenGenerator INSTANCE = new TokenGenerator();
    }

    public static TokenGenerator getInstance() {
        return Holder.INSTANCE;
    }



    public static void main(String[] args) throws Exception {
//        //生成公钥和私钥
//        genKeyPair();
//        //加密字符串
//        String message = "tomax." + System.currentTimeMillis();
//        String messageEn = encrypt(message);
//        System.out.println(message + "\t加密后的字符串为:" + messageEn);
//        String messageDe = decrypt(messageEn);
//        System.out.println("还原后的字符串为:" + messageDe);

        for (int i = 0; i <= 100; i++) {
            System.out.println(TokenGenerator.getInstance().generateSmsCode());
        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    private void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        pubKey = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        priKey = new String(Base64.encodeBase64((privateKey.getEncoded())));
    }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public String encrypt(String str) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(pubKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public String decrypt(String str) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(priKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    public String generateSmsCode() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            builder.append(random.nextInt(10));
        }

        return builder.toString();
    }
}
