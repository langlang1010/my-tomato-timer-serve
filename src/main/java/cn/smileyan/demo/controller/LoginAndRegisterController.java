package cn.smileyan.demo.controller;

import cn.smileyan.demo.entity.User;
import cn.smileyan.demo.service.UserService;
import cn.smileyan.demo.util.RestResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;


/**
 * @author Smileyan
 */
@RestController
@CrossOrigin
public class LoginAndRegisterController {
    @Autowired
    private UserService userService;
    @Value("${TOKEN_TIMEOUT_MS}")
    private Long TOKEN_TIMEOUT_MS;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "用户注册",notes = "需要提交用户名，密码和Email")
    @PostMapping("/register")
    private RestResult register(String username, String password, String email) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setUsername(username);
        user.setId(0L);
        return userService.save(user);
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录，需要提交用户名与密码")
    private RestResult login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return  userService.login(username, password);
    }
}
