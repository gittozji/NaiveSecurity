package me.zji.utils;

import sun.misc.BASE64Encoder;
import sun.security.krb5.internal.crypto.Des;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Des 加密解密
 * Created by imyu on 2017/3/11.
 */
public class DesUtil {

    private final static String ENCODING= "UTF-8";

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            String a = new String(encrypt("jlsakf的临时发卡机", "345353245"),ENCODING);
            String b = new String(decrypt(a, "345353245"),ENCODING);
            System.out.println(b);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static byte[] encrypt(String data, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        Cipher Cipher1 = Cipher.getInstance("DES");
        Cipher Cipher2 = Cipher.getInstance("DES");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] passordFull = getKey(password);
        byte[] passwordHead = new byte[8];
        byte[] passwordTail = new byte[8];
        System.arraycopy(passordFull, 0, passwordHead, 0, 8);
        System.arraycopy(passordFull, 8, passwordTail, 0, 8);

        Cipher1.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec(passwordHead)));
        Cipher2.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec(passwordTail)));

        // 先前在后
        byte[] enBytes = Cipher1.doFinal(data.getBytes(ENCODING));
        enBytes = Cipher2.doFinal(enBytes);

        return enBytes;
    }

    public static byte[] decrypt(String data, String password) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        Cipher Cipher1 = Cipher.getInstance("DES");
        Cipher Cipher2 = Cipher.getInstance("DES");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] passordFull = getKey(password);
        byte[] passwordHead = new byte[8];
        byte[] passwordTail = new byte[8];
        System.arraycopy(passordFull, 0, passwordHead, 0, 8);
        System.arraycopy(passordFull, 8, passwordTail, 0, 8);

        Cipher1.init(Cipher.DECRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec(passwordHead)));
        Cipher2.init(Cipher.DECRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec(passwordTail)));

        // 先后再前
        byte[] deBytes = Cipher2.doFinal(data.getBytes(ENCODING));
        deBytes = Cipher1.doFinal(deBytes);

        return deBytes;
    }

    private static byte[] getKey(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        byte[] bytes = md5.digest(password.getBytes("UTF-8"));
        return bytes;
    }
}
