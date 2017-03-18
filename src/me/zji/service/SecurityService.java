package me.zji.service;

/**
 * 加密解密服务
 * Created by imyu on 2017/3/11.
 */
public interface SecurityService {
    /**
     * 加密文件
     * @param inPath 需要加密的文件路径
     * @param outPath 加密后的文件路径
     * @param secretKey 秘钥
     * @return
     */
    boolean encryptFile(String inPath, String outPath, String secretKey);

    /**
     * 解密文件
     * @param inPath 需要解密的文件路径
     * @param outPath 解密后的文件路径
     * @param secretKey 秘钥
     * @return
     */
    boolean decryptFile(String inPath, String outPath, String secretKey);

    /**
     * 对 64 位编码格式的字符串进行加密、解密
     * @param mode 加密或解密标志
     * @param data64 64 位编码格式的字符串
     * @param secretKey 密钥
     * @return 64 位编码格式的字符串
     * @throws Exception
     */
    String doFinal(int mode, String data64, String secretKey) throws Exception;
}
