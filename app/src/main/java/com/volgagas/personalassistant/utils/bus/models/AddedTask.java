package com.volgagas.personalassistant.utils.bus.models;

import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;

/**
 * Created by CaramelHeaven on 12:03, 23.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class AddedTask {
    private TaskTemplate task;

    public AddedTask(TaskTemplate task) {
        this.task = task;
    }

    public TaskTemplate getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "AddedTask{" +
                "task=" + task +
                '}';
    }
}
