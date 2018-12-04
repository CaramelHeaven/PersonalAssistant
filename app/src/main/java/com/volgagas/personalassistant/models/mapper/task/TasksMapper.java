package com.volgagas.personalassistant.models.mapper.task;

import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 17:44, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TasksMapper {
    private final TaskResponseToTask taskResponseToTask;

    public TasksMapper(TaskResponseToTask taskResponseToTask) {
        this.taskResponseToTask = taskResponseToTask;
    }

    public List<Task> map(TaskResponse response) {
        return taskResponseToTask.map(response);
    }

    public List<SubTaskViewer> mapSubTask(SubTaskResponse response) {
        return taskResponseToTask.mapSubTasks(response);
    }

    public List<Task> mapHistory(TaskResponse response) {
        return taskResponseToTask.mapHistory(response);
    }
}
