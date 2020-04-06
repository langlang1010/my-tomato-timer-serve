package cn.smileyan.demo.controller;

import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.service.TaskService;
import cn.smileyan.demo.util.RestResult;
import cn.smileyan.demo.util.RestResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserTaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @GetMapping("/{token}/tasks")
    private RestResult findTaskById(@PathVariable("token") String token) {
        // 1. 根据 token 查看用户是否登录
        String userStr = redisTemplate.opsForValue().get(token);
        RestResult restResult = new RestResult();
        if(userStr == null || userStr.trim().length() == 0) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }

        // 2. 获得该用户的所有task
        Long userId = Long.parseLong(userStr);

        List<Task> tasks = taskService.findTaskByUserId(userId);
        restResult.setData(tasks);
        return restResult;
    }
}
