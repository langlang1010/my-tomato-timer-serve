package cn.smileyan.demo.service.impl;

import cn.smileyan.demo.dao.UserDao;
import cn.smileyan.demo.entity.User;
import cn.smileyan.demo.service.UserService;
import cn.smileyan.demo.util.EmailFormCheck;
import cn.smileyan.demo.util.Md5;
import cn.smileyan.demo.util.RestResult;
import cn.smileyan.demo.util.RestResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Smileyan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Value("${PASSWORD_MIN_LENGTH}")
    private Long PASSWORD_MIN_LENGTH;
    @Value("${TOKEN_TIMEOUT_MS}")
    private Long TOKEN_TIMEOUT_MS;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public RestResult save(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        RestResult restResult = new RestResult();
        // 1. 查询用户名是否存在
        List<User> sameUsername = userDao.findByUsername(user.getUsername());
        // 如果用户名已经存在
        if (sameUsername != null && sameUsername.size() > 0) {
            restResult.setCode(RestResultCodeEnum.REGISTER_USER_EXISTED.getSeq());
            restResult.setMsg("用户名已存在");
            restResult.setData(sameUsername);
            return restResult;
        }

        // 2. 检查密码是否为空
        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
           restResult.setMsg("密码不能为空");
           restResult.setCode(RestResultCodeEnum.REGISTER_NULL_PASSWORD.getSeq());
           return restResult;
        }

        // 3. 检查密码是否太短
        if (user.getPassword().trim().length() < PASSWORD_MIN_LENGTH) {
            restResult.setMsg("密码太短");
            restResult.setCode(RestResultCodeEnum.REGISTER_TOO_SHORT_PASSWORD.getSeq());
            return restResult;
        }

        // 4. 检查邮箱是否为空
        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
            restResult.setMsg("邮箱不能为空");
            restResult.setCode(RestResultCodeEnum.REGISTER_NULL_EMAIL.getSeq());
            return restResult;
        }

        // 5. 检查邮箱是否格式错误
        if (!EmailFormCheck.checkEmailForm(user.getEmail())) {
            restResult.setMsg("邮箱格式错误");
            restResult.setCode(RestResultCodeEnum.REGISTER_FAULT_EMAIL.getSeq());
            return restResult;
        }

        // 历经千辛万苦的感觉，进行加密
        user.setPassword(Md5.EncoderByMd5(user.getPassword()));
        User saved = userDao.save(user);
        restResult.setData(saved);
        return restResult;
    }

    @Override
    public RestResult login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        RestResult restResult = new RestResult();

        // 1.检查用户名是否为空
        if (username == null || username.trim().length() == 0) {
            restResult.setMsg("用户名不能为空");
            restResult.setCode(RestResultCodeEnum.LOGIN_NULL_USERNAME.getSeq());
            return restResult;
        }

        // 2.检查密码是否为空
        if (password == null || password.trim().length() == 0) {
            restResult.setMsg("密码不能为空");
            restResult.setCode(RestResultCodeEnum.LOGIN_NULL_PASSWORD.getSeq());
            return restResult;
        }

        // 3.检查用户名是否存在
        User user = userDao.getByUsername(username);
        if (user == null) {
           restResult.setMsg("用户名不存在");
           restResult.setCode(RestResultCodeEnum.LOGIN_USER_NOT_EXSIST.getSeq());
           return restResult;
        }

        // 4.检查密码是否一致
        boolean correctPassword = Md5.checkPassword(password, user.getPassword());
        if (!correctPassword) {
            restResult.setCode(RestResultCodeEnum.LOGIN_FAULT_PASSWORD.getSeq());
            restResult.setMsg("密码错误");
            return restResult;
        }

        // 5.登录成功，生成token，写入redis
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set(token,user.getId()+"",TOKEN_TIMEOUT_MS, TimeUnit.MINUTES);
        restResult.setMsg("登录成功");
        restResult.setCode(RestResultCodeEnum.SUCCESS.getSeq());
        restResult.setData(token);

        return restResult;
    }
}
