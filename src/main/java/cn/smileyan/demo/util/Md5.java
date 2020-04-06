package cn.smileyan.demo.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Smileyan
 */
public class Md5 {
    /**
     * 加密
     * @param str 原始数据
     * @return 加密后的数据
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /**
     * 判断密码是否正确
     * @param newPassword 待检测的密码
     * @param oldPassword 原始密码
     * @return 是否匹配
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean checkPassword(String newPassword,String oldPassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        return EncoderByMd5(newPassword).equals(oldPassword);
    }
}
