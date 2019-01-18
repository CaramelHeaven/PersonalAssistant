package com.volgagas.personalassistant.utils.manager;

import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;

import java.util.List;

/**
 * Created by CaramelHeaven on 12:18, 18/01/2019.
 * TaskContentManager it's observer for manipulate the current task & subtasks anywhere
 */
public class TaskContentManager {
    private static TaskContentManager INSTANCE;
    private Task task;

    public static TaskContentManager getInstance() {
        if (INSTANCE == null) {
            synchronized (TaskContentManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TaskContentManager();
                }
            }
        }

        return INSTANCE;
    }

    public void setCurrentTask(Task task) {
        this.task = task;
    }

    public List<SubTask> getSubTasks() {
        return task.getSubTasks();
    }
}
