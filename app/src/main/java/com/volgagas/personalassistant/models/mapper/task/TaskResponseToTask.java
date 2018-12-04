package com.volgagas.personalassistant.models.mapper.task;

import com.volgagas.personalassistant.PersonalAssistant;
import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.SubTask;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.sub_task.SubTaskNetwork;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 07:40, 04.12.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskResponseToTask extends Mapper<TaskResponse, List<Task>> {
    @Override
    public List<Task> map(TaskResponse value) {
        List<Task> tasks = new ArrayList<>();
        fillData(tasks, value);
        return tasks;
    }

    public List<SubTaskViewer> mapSubTasks(SubTaskResponse value) {
        List<SubTaskViewer> viewers = new ArrayList<>();
        fillSubTask(viewers, value);
        return viewers;
    }

    public List<Task> mapHistory(TaskResponse response) {
        List<Task> tasks = new ArrayList<>();
        fillTask(tasks, response, false);
        return tasks;
    }

    private void fillTask(List<Task> tasks, TaskResponse response, boolean isToday) {
        //removing completed tasks
        List<TaskNetwork> test = new ArrayList<>();
        if (isToday) {
            for (TaskNetwork taskNetwork : response.getValue()) {
                if (!taskNetwork.getAcClosed().equals("Yes")) {
                    test.add(taskNetwork);
                }
            }
        } else {
            //show completed tasks last days.. < today
            for (TaskNetwork taskNetwork : response.getValue()) {
                if (taskNetwork.getAcClosed().equals("Yes")) {
                    test.add(taskNetwork);
                }
            }
        }

        Map<Task, List<SubTask>> map = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            Task task = new Task();
            task.setDescription(test.get(i).getServiceOrderDescription());
            task.setIdTask(test.get(i).getServiceOrderId());
            task.setGpa(test.get(i).getGpa());
            task.setPreferredTime(test.get(i).getPreferredTime());
            task.setStatus(test.get(i).getStatus());
            if (!map.containsKey(task))
                map.put(task, new ArrayList<>());
        }

        Map<Task, List<SubTask>> updated = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            Task task = new Task();
            task.setDescription(test.get(i).getServiceOrderDescription());
            task.setIdTask(test.get(i).getServiceOrderId());
            task.setGpa(test.get(i).getGpa());
            task.setPreferredTime(test.get(i).getPreferredTime());
            task.setStatus(test.get(i).getStatus());
            if (map.containsKey(task)) {
                List<SubTask> subTasks = map.get(task);
                subTasks.add(addSubTask(new SubTask(), test.get(i)));
                updated.put(task, subTasks);
            }
        }

        List<Task> compeletedList = new ArrayList<>();

        for (Map.Entry<Task, List<SubTask>> entry : updated.entrySet()) {
            Task task = entry.getKey();
            task.setSubTasks(entry.getValue());
            compeletedList.add(task);
        }

        tasks.addAll(compeletedList);
        Timber.d("check mapping tasks: " + tasks.size());
    }

    private void fillSubTask(List<SubTaskViewer> viewers, SubTaskResponse value) {
        for (SubTaskNetwork network : value.getValue()) {
            SubTaskViewer subTaskViewer = new SubTaskViewer();
            subTaskViewer.setDescription(network.getaCDescription());
            subTaskViewer.setStartTime(network.getaCActivityStartDateTime());
            subTaskViewer.setWorkerName(network.getaCWorker());
            subTaskViewer.setState(network.getState());
            viewers.add(subTaskViewer);
        }
    }

    @Override
    protected void fillData(List<Task> tasks, TaskResponse taskResponse) {
        for (TaskNetwork container : taskResponse.getValue()) {
            Task task = new Task();
            //task.set(container.getProjCategoryId());
            task.setDescription(container.getServiceOrderDescription());
            task.setIdTask(container.getServiceOrderId());
        }
    }

    private SubTask addSubTask(SubTask subTask, TaskNetwork network) {
        subTask.setDescription(network.getActivityDescription());
        subTask.setEndDate(network.getEndDate());
        //TODO update time
        //subTask.setStartDate(PersonalAssistant.getSimpleDateFormat(network.getStartDate()));
        //subTask.setStartTime(PersonalAssistant.getSimpleTimeFormat(network.getStartDate()));
        subTask.setIdSubTask(String.valueOf(network.getIdSubTask()));
        subTask.setStatus(network.getStatus());
        subTask.setWorker(network.getWorker());
        subTask.setIdActivity(network.getActivityId());
        return subTask;
    }
}
