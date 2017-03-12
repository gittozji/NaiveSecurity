package me.zji.utils;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

/**
 * Created by imyu on 2017/3/12.
 */
public class DDD {
    public static void main(String[] args) throws Exception {
        String souorcePath = "D:/Git/GitProjects/NaiveSecurity/xxxxxxxxout.pdf";
        String targetPath = "D:/Git/GitProjects/NaiveSecurity/xxxxxxxxen.pdf";
        InputStream inputStream = new FileInputStream(souorcePath);
        OutputStream outputStream = new FileOutputStream(targetPath);
        Cipher cipher = Cipher.getInstance("DES");
        SecureRandom secureRandom = new SecureRandom();
        DESKeySpec desKeySpec = new DESKeySpec("12345678543fgdsdfsdfedffdg".getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = secretKeyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.DECRYPT_MODE, securekey, secureRandom);
        CipherInputStream cipherOutputStream = new CipherInputStream(inputStream, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cipherOutputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, r);
        }
        cipherOutputStream.close();
        inputStream.close();
        outputStream.close();
    }
}
