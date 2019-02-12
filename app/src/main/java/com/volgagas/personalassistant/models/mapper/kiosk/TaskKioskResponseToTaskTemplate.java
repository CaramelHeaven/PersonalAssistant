package com.volgagas.personalassistant.models.mapper.kiosk;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;
import com.volgagas.personalassistant.models.network.task_kiosk.TaskKioskNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 18:00, 27/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskKioskResponseToTaskTemplate extends Mapper<TaskKioskResponse, List<TaskTemplate>> {

    @Override
    public List<TaskTemplate> map(TaskKioskResponse value) {
        List<TaskTemplate> taskTemplates = new ArrayList<>();
        fillData(taskTemplates, value);

        return taskTemplates;
    }

    @Override
    protected void fillData(List<TaskTemplate> taskTemplates, TaskKioskResponse taskResponse) {
        for (TaskKioskNetwork container : taskResponse.getValue()) {
            TaskTemplate task = new TaskTemplate();

            task.setActivityEndDateTime(container.getActivityEndDateTime());
            task.setActivityStartDateTime(container.getActivityStartDateTime());
            task.setActivityTypeId(container.getActivityTypeId());
            task.setCategoryId(container.getProjCategoryId());
            task.setCustAccount(container.getCustAccount());
            task.setServiceTaskId(container.getServiceTaskId());
            task.setDataAreaId(container.getDataAreaId());
            task.setDescription(container.getDescription());
            task.setProjectId(container.getProjId());
            task.setServiceDateTime(container.getServiceDateTime());
            task.setHcmResponsiblePersonnelNumber(container.getHcmWorkerResponsiblePersonnelNumber());
            task.setServiceObjectId(container.getServiceObjectId());
            task.setServiceOrderId(container.getServiceOrderId());
            task.setPreferredTechnicianPersonnelNumber(container.getHcmWorkerPreferredTechnicianPersonnelNumber());
            task.setStageId(container.getStageId());
            task.setAgreementId(container.getAgreementId());
            task.setCustCenter(container.getCostCenter());

            taskTemplates.add(task);
        }
    }
}
