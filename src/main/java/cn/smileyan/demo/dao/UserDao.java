package cn.smileyan.demo.dao;

import cn.smileyan.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Long> {
    /**
     * 根据用户名查找
     * @param username 用户名
     * @return 所有此用户名的用户
     */
    List<User> findByUsername(String username);

    /**
     * 登录
     * @param username 用户名
     * @return 登录用户 / null
     */
    User getByUsername(String username);
}
