package cn.smileyan.demo.service;

import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.entity.User;
import cn.smileyan.demo.util.RestResult;

import java.util.List;

public interface TaskService {
    /**
     * 添加任务
     * @param task 任务
     * @param user 用户
     * @return 任务
     */
    Task save(Task task, User user);

    /**
     * 完成任务，首先需要检查一下用户和任务是否匹配
     * @param task 任务
     * @param user 用户
     * @return 完成情况
     */
    RestResult finish(Task task, User user);

    /**
     * 根据用户查询
     * @param userId 用户
     * @return 该用户的所有task
     */
    List<Task> findTaskByUserId(Long userId);
}
