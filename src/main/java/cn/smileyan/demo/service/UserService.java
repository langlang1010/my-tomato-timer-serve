package cn.smileyan.demo.service;

import cn.smileyan.demo.entity.User;
import cn.smileyan.demo.util.RestResult;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {
    /**
     * 添加某个用户
     * @param user  待注册的用户
     * @return 用户id
     */
    RestResult save(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 登录用户
     */
    RestResult login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

}
