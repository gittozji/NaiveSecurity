package me.zji;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class Main {

    public static void main(String[] args) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("DES");
        Cipher decryptCipher = Cipher.getInstance("DES");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec("1234abcd".getBytes())));
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeyFactory.generateSecret(new DESKeySpec("1234abcd".getBytes())));
        String src = "1234";
        byte[] enBytes = encryptCipher.doFinal(src.getBytes("UTF-8"));
        String ss = new String(enBytes,"UTF-8");
        System.out.println(new String(enBytes));
        byte[] deBytes = decryptCipher.doFinal(enBytes);
        System.out.println(new String(deBytes));
//        byte[] test = decryptCipher.doFinal(ss.getBytes());
//        System.out.println(new String(test));
        System.out.println(enBytes.length + "      " + ss.getBytes("UTF-8").length);
        byte[] dd = {97,98,99,100};
        String sdsf = new String(dd);
        System.out.println(dd.length);
        System.out.println(sdsf.getBytes().length);
    }
}
