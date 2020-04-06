package cn.smileyan.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Smileyan
 */
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="task_name")
    private String taskName;
    @Column(name="is_finished")
    private boolean finished;
    @Column(name="create_time")
    private Date startTime;
    @Column(name="finish_time")
    private Date finishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
