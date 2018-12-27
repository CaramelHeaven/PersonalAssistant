package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CaramelHeaven on 17:38, 27/12/2018.
 */
public class TaskResponseToTaskHistory extends Mapper<TaskResponse, List<TaskHistory>> {

    @Override
    public List<TaskHistory> map(TaskResponse value) {
        List<TaskHistory> taskHistories = new ArrayList<>();
        fillData(taskHistories, value);

        return taskHistories;
    }

    @Override
    protected void fillData(List<TaskHistory> taskHistories, TaskResponse taskResponse) {
//removing completed tasks
        List<TaskNetwork> test = new ArrayList<>();

        //show completed tasks last days.. < today
        for (TaskNetwork taskNetwork : taskResponse.getValue()) {
            if (taskNetwork.getAcClosed().equals("Yes")) {
                test.add(taskNetwork);
            }
        }

        Map<TaskHistory, List<SubTask>> map = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            TaskHistory task = new TaskHistory();
            task.setDescription(test.get(i).getServiceOrderDescription());
            task.setIdTask(test.get(i).getServiceOrderId());
            task.setGpa(test.get(i).getGpa());
            task.setPreferredTime(test.get(i).getPreferredTime());
            task.setStatus(test.get(i).getStatus());
            if (!map.containsKey(task))
                map.put(task, new ArrayList<>());
        }

        Map<TaskHistory, List<SubTask>> updated = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            TaskHistory task = new TaskHistory();
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

        List<TaskHistory> compeletedList = new ArrayList<>();

        for (Map.Entry<TaskHistory, List<SubTask>> entry : updated.entrySet()) {
            TaskHistory task = entry.getKey();
            task.setSubTasks(entry.getValue());
            compeletedList.add(task);
        }

        taskHistories.addAll(compeletedList);
    }

    private SubTask addSubTask(SubTask subTask, TaskNetwork network) {
        subTask.setDescription(network.getActivityDescription());
        subTask.setEndDate(network.getEndDate());
        subTask.setStartDate(network.getStartDate());
        subTask.setStartTime(network.getStartDate());
        subTask.setIdSubTask(String.valueOf(network.getIdSubTask()));
        subTask.setStatus(network.getStatus());
        subTask.setWorker(network.getWorker());
        subTask.setIdActivity(network.getActivityId());

        return subTask;
    }
}
