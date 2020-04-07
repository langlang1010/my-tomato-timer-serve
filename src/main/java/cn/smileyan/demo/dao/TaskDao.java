package cn.smileyan.demo.dao;

import cn.smileyan.demo.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDao extends CrudRepository<Task,Long> {
    /**
     * 根据id进行查询
     * @param id task编号
     * @return task
     */
    Task getById(Long id);

    /**
     * 根据用户id查询所有task
     * @param userId 用户id
     * @return 此用户所有task
     */
    @Query(value = "SELECT task FROM Task task, UserTask userTask " +
            "WHERE userTask.userId = :userId AND userTask.taskId = task.id")
    List<Task> findTaskByUserid(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(task.id) FROM Task task, UserTask userTask " +
            "WHERE userTask.userId = :userId AND userTask.taskId = task.id AND task.finished = 1")
    int countFinished(@Param("userId") Long userId);
}
