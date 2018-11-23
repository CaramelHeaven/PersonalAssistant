package com.volgagas.personalassistant.utils.bus.models;

import com.volgagas.personalassistant.models.model.Task;

/**
 * Created by CaramelHeaven on 12:03, 23.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class AddedTask {
    private Task task;

    public AddedTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "AddedTask{" +
                "task=" + task +
                '}';
    }
}
