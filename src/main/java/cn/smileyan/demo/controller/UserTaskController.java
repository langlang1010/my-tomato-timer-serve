package cn.smileyan.demo.controller;

import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.service.TaskService;
import cn.smileyan.demo.util.RestResult;
import cn.smileyan.demo.util.RestResultCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserTaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @ApiOperation(value = "获得用户的所有任务",notes = "需要提交用户名，密码和Email")
    @GetMapping("/tasks/{token}")
    private RestResult findTaskById(@PathVariable("token") String token) {
        // 1. 根据 token 查看用户是否登录
        RestResult restResult = new RestResult();
        Long userId = checkToken(token);
        if (userId == null) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }

        // 2. 获得该用户的所有task
        List<Task> tasks = taskService.findTaskByUserId(userId);
        restResult.setData(tasks);
        return restResult;
    }

    @ApiOperation(value = "完成某个任务",notes = "需要提交token和taskId")
    @PostMapping("/finish")
    private RestResult finishTask(String token, Long taskId) {
        // 1. 根据 token 查看用户是否登录
        RestResult restResult = new RestResult();
        Long userId = checkToken(token);
        if (userId == null) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }

        // 2. finish
        return taskService.finish(taskId, userId);
    }

    @ApiOperation(value = "获得已经完成的任务数目",notes = "需要提交token")
    @GetMapping("/count/{token}")
    private RestResult countFinished(@PathVariable("token") String token) {
        RestResult restResult = new RestResult();
        Long userId = checkToken(token);
        if (userId == null) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }

        // 获得已经完成的数目
        Integer count = taskService.countFinished(userId);
        restResult.setData(count);
        return restResult;
    }

    @ApiOperation(value = "添加某个任务",notes = "需要提交token和taskName")
    @PostMapping("/add")
    private RestResult saveTask(String taskName, String token) {
        RestResult restResult = new RestResult();
        Long userId = checkToken(token);
        if (userId == null) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }
        Task task = new Task();
        task.setId(0L);
        task.setStartTime(Calendar.getInstance().getTime());
        task.setTaskName(taskName);
        task.setFinished(false);
        Task save = taskService.save(task, userId);
        restResult.setData(save);
        return restResult;
    }

    @ApiOperation(value = "重新开始某个任务",notes = "需要提交token和taskId")
    @PostMapping("/restart")
    private RestResult unfinishTask(String token, Long taskId) {
        RestResult restResult = new RestResult();
        Long userId = checkToken(token);
        if (userId == null) {
            restResult.setMsg("授权码已经超时，请重新登录");
            restResult.setCode(RestResultCodeEnum.TIMEOUT_TOKEN.getSeq());
            return restResult;
        }
        return taskService.restart(taskId, userId);
    }

    /**
     * 检查 token 是否有效
     * @param token token
     * @return 用户id 或者 null
     */
    private Long checkToken(String token) {
        String userStr = redisTemplate.opsForValue().get(token);
        if(userStr == null || userStr.trim().length() == 0) {
            return null;
        }

        return Long.parseLong(userStr);
    }
}
