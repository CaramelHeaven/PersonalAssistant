package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.sub_task.SubTaskNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:20, 11/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class SubTaskResponseToSubTask extends Mapper<SubTaskResponse, List<SubTaskViewer>> {
    @Override
    public List<SubTaskViewer> map(SubTaskResponse value) {
        List<SubTaskViewer> subTaskViewers = new ArrayList<>();
        fillData(subTaskViewers, value);

        return subTaskViewers;
    }

    @Override
    protected void fillData(List<SubTaskViewer> subTaskViewers, SubTaskResponse response) {
        for (SubTaskNetwork network : response.getValue()) {
            Timber.d("network: " + network.toString());
            SubTaskViewer subTaskViewer = new SubTaskViewer();

            subTaskViewer.setDescription(network.getaCDescription());
            subTaskViewer.setStartTime(network.getaCActivityStartDateTime());
            subTaskViewer.setWorkerName(network.getaCWorker());
            subTaskViewer.setState(network.getState());
            subTaskViewer.setActivityId(network.getaCActivityId());

            subTaskViewers.add(subTaskViewer);
        }
    }
}
