package com.volgagas.personalassistant.models.model.worker;

import com.volgagas.personalassistant.models.model.Task;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:18, 13/02/2019.
 * <p>
 * Class which contains list of tasks today and list of histories
 */
public class ContainerTaskAndHistory {
    private List<Task> taskList;
    private List<TaskHistory> taskHistories;

    public ContainerTaskAndHistory(List<Task> taskList, List<TaskHistory> taskHistories) {
        this.taskList = taskList;
        this.taskHistories = taskHistories;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    @Override
    public String toString() {
        return "ContainerTaskAndHistory{" +
                "taskList=" + taskList +
                ", taskHistories=" + taskHistories +
                '}';
    }
}
