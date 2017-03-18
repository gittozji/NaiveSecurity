package me.zji.service.impl;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import me.zji.service.SecurityService;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.MessageDigest;

/**
 * 加密解密服务
 * Created by imyu on 2017/3/11.
 */
public class SecurityServiceImpl implements SecurityService {
    public static void main(String[] args) throws Exception {
        {
            String souorcePath = "D:/Git/GitProjects/NaiveSecurity/origin.txt";
            String targetPath = "D:/Git/GitProjects/NaiveSecurity/en.txt";
            SecurityService securityService = new SecurityServiceImpl();
            securityService.encryptFile(souorcePath, targetPath, "12345678");
        }

        {
            String souorcePath = "D:/Git/GitProjects/NaiveSecurity/en.txt";
            String targetPath = "D:/Git/GitProjects/NaiveSecurity/de.txt";
            SecurityService securityService = new SecurityServiceImpl();
            securityService.decryptFile(souorcePath, targetPath, "12345678");
        }



    }
    /**
     * 加密文件
     *
     * @param inPath    需要加密的文件路径
     * @param outPath   加密后的文件路径
     * @param secretKey 秘钥
     * @return
     */
    @Override
    public boolean encryptFile(String inPath,String outPath, String secretKey) {
        try {
            // 加密 key2 转置
            byte[] key1 = getSecretKey(secretKey);
            byte[] key2 = getSecretKey(secretKey);
            int i = 0, j = key2.length - 1;
            while (i < j) {
                byte temp = key2[i];
                key2[i] = key2[j];
                key2[j] = temp;
                i ++;
                j --;
            }
            doFinal(Cipher.ENCRYPT_MODE, inPath, outPath + ".temp", key1);  // 一次加密
            doFinal(Cipher.ENCRYPT_MODE, outPath + ".temp", outPath, key2); // 二次加密
            return true;
        } catch (Exception e) {
            File temp = new File(outPath + ".temp");
            temp.delete();
            temp = new File(outPath);
            temp.delete();
        } finally {
            File temp = new File(outPath + ".temp");
            temp.delete();
        }
        return false;
    }

    /**
     * 解密文件
     *
     * @param inPath    需要解密的文件路径
     * @param outPath   解密后的文件路径
     * @param secretKey 秘钥
     * @return
     */
    @Override
    public boolean decryptFile(String inPath, String outPath, String secretKey) {
        try {
            // 解密 key1 转置
            byte[] key1 = getSecretKey(secretKey);
            byte[] key2 = getSecretKey(secretKey);
            int i = 0, j = key1.length - 1;
            while (i < j) {
                byte temp = key1[i];
                key1[i] = key1[j];
                key1[j] = temp;
                i ++;
                j --;
            }
            doFinal(Cipher.DECRYPT_MODE, inPath, outPath + ".temp", key1);  // 一次解密
            doFinal(Cipher.DECRYPT_MODE, outPath + ".temp", outPath, key2); // 二次解密
            return true;
        } catch (Exception e) {
            File temp = new File(outPath + ".temp");
            temp.delete();
            temp = new File(outPath);
            temp.delete();
        } finally {
            File temp = new File(outPath + ".temp");
            temp.delete();
        }
        return false;
    }

    /**
     * 对文件进行加密、解密
     * 出处：http://blog.csdn.net/anjon520/article/details/17145313
     * @param mode 加密或解密标志
     * @param inPath 需要解密或解密的文件路径
     * @param outPath 加密或解密后的文件路径
     * @param secretKey 密钥
     * @throws Exception
     */
    private void doFinal(int mode, String inPath, String outPath, byte[] secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        SecretKey finalKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(secretKey));
        cipher.init(mode, finalKey);

        InputStream inputStream = new FileInputStream(inPath);
        OutputStream outputStream = new FileOutputStream(outPath);
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

    /**
     * 对 64 位编码格式的字符串进行加密、解密
     * @param mode 加密或解密标志
     * @param data64 64 位编码格式的字符串
     * @param secretKey 密钥
     * @return 64 位编码格式的字符串
     * @throws Exception
     */
    public String doFinal(int mode, String data64, String secretKey) {
        try {
            byte[] key = getSecretKey(secretKey);
            Cipher cipher = Cipher.getInstance("DES");
            SecretKey finalKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key));
            cipher.init(mode, finalKey);

            byte[] result = cipher.doFinal(Base64.decode(data64));
            return Base64.encode(result);
        } catch (Exception e) {
            return "加密内部算法出错！！！";
        }

    }

    /**
     * 通过密码获取 md5 16字节密钥
     * @param secretKey
     * @return
     * @throws Exception
     */
    private byte[] getSecretKey(String secretKey) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest(secretKey.getBytes());
        return bytes;
    }
}
