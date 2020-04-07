package cn.smileyan.demo.service.impl;

import cn.smileyan.demo.dao.TaskDao;
import cn.smileyan.demo.dao.UserTaskDao;
import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.entity.User;
import cn.smileyan.demo.entity.UserTask;
import cn.smileyan.demo.service.TaskService;
import cn.smileyan.demo.util.RestResult;
import cn.smileyan.demo.util.RestResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * @author Smileyan
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private UserTaskDao userTaskDao;
    @Autowired
    private TaskDao taskDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task save(Task task, User user) {
        // 1. 添加 task
        Task save = taskDao.save(task);

        // 2. 保存 user_task
        UserTask userTask = new UserTask();
        userTask.setId(0L);
        userTask.setTaskId(save.getId());
        userTask.setUserId(user.getId());
        userTaskDao.save(userTask);
        return task;
    }

    @Override
    public RestResult finish(Long taskId, Long userId) {
        RestResult restResult = new RestResult();
        // 1. 检查task是否存在
        Task taskById = taskDao.getById(taskId);
        if(taskById == null) {
            restResult.setMsg("此任务不存在");
            restResult.setData(RestResultCodeEnum.NONE_RESULT);
            return restResult;
        }

        // 2. 检查user和task是否匹配
        List<UserTask> matched = userTaskDao.findByUserIdAndAndTaskId(userId, taskId);
        if (matched != null && matched.size() == 0) {
            restResult.setMsg("该任务不属于此用户");
            restResult.setData(RestResultCodeEnum.NONE_RESULT);
            return restResult;
        }

        // 3. 修改task的is_finished和finish time
        taskById.setFinished(true);
        taskById.setFinishTime(Calendar.getInstance().getTime());

        // 4. 更新
        Task updated = taskDao.save(taskById);
        restResult.setMsg("完成任务");
        restResult.setData(updated);
        return restResult;
    }

    @Override
    public List<Task> findTaskByUserId(Long userId) {
        return taskDao.findTaskByUserid(userId);
    }

    @Override
    public Integer countFinished(Long userId) {
        return taskDao.countFinished(userId);
    }
}
