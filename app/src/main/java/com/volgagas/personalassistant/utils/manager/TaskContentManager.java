package com.volgagas.personalassistant.utils.manager;

import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 12:18, 18/01/2019.
 * TaskContentManager it's observer for manipulate the current task [and] sub-tasks anywhere
 */
public class TaskContentManager {
    private static TaskContentManager INSTANCE;
    private Task task;
    private List<SubTask> subTasks; // selected current tasks

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

    public Task getTask() {
        return task;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskViewer> list) {
        subTasks = new ArrayList<>();

        fillData(subTasks, list);
    }

    private void fillData(List<SubTask> subTasks, List<SubTaskViewer> list) {
        for (SubTaskViewer temp : list) {
            SubTask subTask = new SubTask();

            subTask.setWorker(temp.getWorkerName());
            subTask.setIdActivity(temp.getActivityId());
            subTask.setDescription(temp.getDescription());

            subTasks.add(subTask);
        }
    }
}
