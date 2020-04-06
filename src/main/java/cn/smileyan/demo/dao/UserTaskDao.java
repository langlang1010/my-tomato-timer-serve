package cn.smileyan.demo.dao;

import cn.smileyan.demo.entity.UserTask;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTaskDao extends CrudRepository<UserTask,Long> {
    /**
     * 根据用户和任务查询是否匹配
     * @param userId 用户ID
     * @param taskId 任务ID
     * @return 满足条件的所有
     */
    List<UserTask> findByUserIdAndAndTaskId(Long userId, Long taskId);
}
