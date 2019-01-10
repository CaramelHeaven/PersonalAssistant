package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.models.network.TaskResponse;
import com.volgagas.personalassistant.models.network.task.TaskNetwork;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by CaramelHeaven on 17:19, 27/12/2018.
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
        //removing completed tasks
        List<TaskNetwork> test = new ArrayList<>();
        for (TaskNetwork taskNetwork : taskResponse.getValue()) {
            if (!taskNetwork.getAcClosed().equals("Yes")) {
                test.add(taskNetwork);
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

        //Changed logic [copy from worker]. 21.12.2018
        for (Map.Entry<Task, List<SubTask>> entry : updated.entrySet()) {
            Task task = entry.getKey();

            //set minimal time
            String[] massive = getMinimalTimeFromSubTasks(entry.getValue());

            task.setStartDate(massive[0]);
            task.setStartTime(massive[1]);

            task.setSubTasks(entry.getValue());

            compeletedList.add(task);
        }

        tasks.addAll(compeletedList);
    }

    private SubTask addSubTask(SubTask subTask, TaskNetwork network) {
        subTask.setDescription(network.getActivityDescription());
        subTask.setEndDate(network.getEndDate());
        subTask.setStartServerTime(network.getStartDate());
        subTask.setIdSubTask(String.valueOf(network.getIdSubTask()));
        subTask.setStatus(network.getStatus());
        subTask.setWorker(network.getWorker());
        subTask.setIdActivity(network.getActivityId());

        return subTask;
    }

    /* First subTask contains minimal date and time after fillDateStartInSubTasks method.
     *  Just get it and set each Task this is date and time.
     * */
    private String[] getMinimalTimeFromSubTasks(List<SubTask> subTasks) {
        fillDateStartInSubTasks(subTasks);

        Collections.sort(subTasks);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String currentDate = dateFormat.format(Calendar.getInstance().getTime());

        String dateString = subTasks.get(0).getStartServerTime().substring(0, 10);

        String[] mas = new String[2];

        /* if date equal current date - just put "Today", another return minimal time.
         * */
        if (currentDate.contains(dateString)) {
            mas[0] = "Сегодня";
        } else {
            mas[0] = subTasks.get(0).getStartTime();
        }
        mas[1] = subTasks.get(0).getStartTime();

        return mas;
    }

    /* format date and time in each subTask
     * */
    private void fillDateStartInSubTasks(List<SubTask> subTasks) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy", new Locale("RU"));
        DateFormat formatTime = new SimpleDateFormat("HH:mm", new Locale("RU"));

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+4"));

        for (SubTask subTask : subTasks) {
            try {
                Date date = dateFormat.parse(subTask.getStartServerTime());

                //fill missing field formatting DATE
                subTask.setStartDate(formatDate.format(date));
                //formatting TIME
                subTask.setStartTime(formatTime.format(date));

                subTask.setDateStart(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}