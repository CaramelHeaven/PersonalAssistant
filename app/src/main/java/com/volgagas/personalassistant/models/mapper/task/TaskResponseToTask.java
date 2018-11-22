package com.volgagas.personalassistant.models.mapper.task;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 17:44, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskResponseToTask extends Mapper<TaskResponse, List<Task>> {
    @Override
    public List<Task> map(TaskResponse value) {
        List<Task> tasks = new ArrayList<>();
        fillData(tasks, value);
        return tasks;
    }

    @Override
    protected void fillData(List<Task> tasks, TaskResponse taskResponse) {
        for (TaskNetwork container : taskResponse.getValue()) {
            Task task = new Task();
            task.setCategoryId(container.getProjCategoryId());
            task.setDescription(container.getDescription());
            task.setServiceOrderId(container.getServiceOrderId());
        }
    }
}
