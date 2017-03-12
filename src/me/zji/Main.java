package me.zji;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Main {

    public static void main(String[] args) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("DES");
        Cipher decryptCipher = Cipher.getInstance("DES");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec("12345678000".getBytes())));
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec("12345679".getBytes())));
        String src = "A324214地方1234";
        byte[] enBytes = encryptCipher.doFinal(src.getBytes());
        String ss = new String(enBytes);
        System.out.println("enBytes:" + new String(enBytes));
        byte[] deBytes = decryptCipher.doFinal(Base64.decode(Base64.encode(enBytes)));
        System.out.println("deBytes:" + new String(deBytes));
//        byte[] test = decryptCipher.doFinal(ss.getBytes());
//        System.out.println(new String(test));

        System.out.println(enBytes.length + "      " + ss.getBytes().length);
        byte[] dd = {97,98,99,100};
        String sdsf = new String(dd);
        System.out.println(dd.length);
        System.out.println(sdsf.getBytes().length);
    }
}
