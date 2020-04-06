package cn.smileyan.demo;

import cn.smileyan.demo.dao.TaskDao;
import cn.smileyan.demo.dao.UserTaskDao;
import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.entity.UserTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskDaoTest {
    @Autowired
    private TaskDao taskDao;

    @Test
    public void save() {
        for (int i=0; i<10; i++) {
            Task task = new Task();
            task.setId(0L);
            task.setStartTime(Calendar.getInstance().getTime());
            task.setTaskName("Task");
            task.setFinished(false);
            taskDao.save(task);
        }
    }

    @Autowired
    private UserTaskDao userTaskDao;
    @Test
    public void saveUserTask() {
        for (long i=7; i<17; i++) {
            UserTask userTask = new UserTask();
            userTask.setUserId(4L);
            userTask.setTaskId(i);
            userTask.setId(0L);
            userTaskDao.save(userTask);
        }
    }

    @Test
    public void findByUserid() {
        List<Task> taskByUserid = taskDao.findTaskByUserid(4L);
        System.out.println("size = "+taskByUserid.size());
    }
}
