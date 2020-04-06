package cn.smileyan.demo;

import cn.smileyan.demo.dao.TaskDao;
import cn.smileyan.demo.entity.Task;
import cn.smileyan.demo.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskDao taskDao;
    @Test
    public void getByUserid() {
        List<Task> taskByUserid = taskDao.findTaskByUserid(2L);
        taskByUserid.forEach(e->{
            System.out.println(e.getId());
        });
    }
}
