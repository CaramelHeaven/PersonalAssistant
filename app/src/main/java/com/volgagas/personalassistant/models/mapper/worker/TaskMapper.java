package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.kiosk.TaskKioskResponseToTaskTemplate;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:43, 27/12/2018.
 */
public class TaskMapper {
    private final TaskResponseToTask taskResponseToTask;
    private final TaskResponseToTaskHistory taskResponseToTaskHistory;
    private final TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate;

    public TaskMapper(TaskResponseToTask taskResponseToTask, TaskResponseToTaskHistory taskResponseToTaskHistory,
                      TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate) {
        this.taskResponseToTask = taskResponseToTask;
        this.taskResponseToTaskHistory = taskResponseToTaskHistory;
        this.taskKioskResponseToTaskTemplate = taskKioskResponseToTaskTemplate;
    }

    public List<TaskHistory> mapHistoryTasks(TaskResponse response) {
        return taskResponseToTaskHistory.map(response);
    }

    public List<Task> mapTasks(TaskResponse response) {
        return taskResponseToTask.map(response);
    }

    public List<TaskTemplate> mapTemplates(TaskKioskResponse response) {
        return taskKioskResponseToTaskTemplate.map(response);
    }
}
