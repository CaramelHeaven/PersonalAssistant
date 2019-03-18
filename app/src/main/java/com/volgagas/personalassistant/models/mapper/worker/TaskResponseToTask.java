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

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:19, 27/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskResponseToTask extends Mapper<TaskResponse, List<Task>> {

    @Override
    public List<Task> map(TaskResponse value) {
        List<Task> tasks = new ArrayList<>();
        fillData(tasks, value);

        Collections.sort(tasks);

        return tasks;
    }

    @Override
    protected void fillData(List<Task> tasks, TaskResponse taskResponse) {
        //removing completed tasks
        List<TaskNetwork> test = new ArrayList<>();
        for (TaskNetwork taskNetwork : taskResponse.getValue()) {
            //if gpa not set - not add
            if (!taskNetwork.getAcClosed().equals("Yes") && !taskNetwork.getGpa().equals("")) {
                test.add(taskNetwork);
            }
        }

        Map<Task, List<SubTask>> map = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            Task task = new Task();

            //set field this
            task.setDescription(test.get(i).getServiceOrderDescription());
            task.setIdTask(test.get(i).getServiceOrderId());
            task.setGpa(test.get(i).getGpa());
            task.setPreferredTime(test.get(i).getPreferredTime());
            task.setStatus(test.get(i).getStatus());
            task.setProjCategoryId(test.get(i).getProjCategoryId());
            task.setServiceTaskId(test.get(i).getSoServiceTaskId());
            task.setSoProjId(test.get(i).getSoProjectId());

            if (!map.containsKey(task))
                map.put(task, new ArrayList<>());
        }

        Map<Task, List<SubTask>> updated = new LinkedHashMap<>();

        for (int i = 0; i < test.size(); i++) {
            Task task = new Task();

            //set field this
            task.setDescription(test.get(i).getServiceOrderDescription());
            task.setIdTask(test.get(i).getServiceOrderId());
            task.setGpa(test.get(i).getGpa());
            task.setPreferredTime(test.get(i).getPreferredTime());
            task.setStatus(test.get(i).getStatus());
            task.setProjCategoryId(test.get(i).getProjCategoryId());
            task.setServiceTaskId(test.get(i).getSoServiceTaskId());
            task.setSoProjId(test.get(i).getSoProjectId());

            if (map.containsKey(task)) {
                List<SubTask> subTasks = map.get(task);
                subTasks.add(addSubTask(new SubTask(), test.get(i)));

                updated.put(task, subTasks);
            }
        }

        List<Task> completedList = new ArrayList<>();

        //Changed logic [copy from worker]. 21.12.2018
        for (Map.Entry<Task, List<SubTask>> entry : updated.entrySet()) {
            Task task = entry.getKey();

            //set minimal time
            Object[] values = getMinimalTimeFromSubTasks(entry.getValue());

            task.setStartDate((String) values[0]);
            task.setStartTime((String) values[1]);
            task.setServerDateTime((Date) values[2]);

            formatDayOfWeekAndDayOfMonth(task);

            task.setSubTasks(entry.getValue());

            completedList.add(task);
        }

        tasks.addAll(completedList);
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

    /**
     * First subTask contains minimal date and time after fillDateStartInSubTasks method.
     * Just get it and set each Task this is date and time.
     *
     * @param subTasks - current list of sub-tasks
     * @return object which contains 3 special data-elements.
     */
    private Object[] getMinimalTimeFromSubTasks(List<SubTask> subTasks) {
        Object[] massiveData = new Object[3];

        fillDateStartInSubTasks(subTasks);

        Collections.sort(subTasks);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String currentDate = dateFormat.format(Calendar.getInstance().getTime());
        String dateString = subTasks.get(0).getStartServerTime().substring(0, 10);

        // if date equal current date - just put "Today", another return minimal time.
        if (currentDate.contains(dateString)) {
            massiveData[0] = "Сегодня";
        } else {
            massiveData[0] = subTasks.get(0).getStartDate();
        }

        massiveData[1] = subTasks.get(0).getStartTime();


        // Add to massive last value which we will be add to task and after all will use collection sort
        try {
            Date date = dateFormat.parse(subTasks.get(0).getStartServerTime());
            massiveData[2] = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return massiveData;
    }

    /**
     * Format date and time in each subTask
     *
     * @param subTasks - list of current subtasks. inside it we change time
     */
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

    /**
     * Format server time to day in week and day in month for reflect this data to UI inside
     * WorkerTodayNewFragment
     *
     * @param task - current task
     */
    private void formatDayOfWeekAndDayOfMonth(Task task) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat dayInWeek = new SimpleDateFormat("E", new Locale("RU"));
        DateFormat dayInMonth = new SimpleDateFormat("d", new Locale("RU"));

        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+4"));

        Date date = task.getServerDateTime();

        task.setDayOfWeek(dayInWeek.format(date));
        task.setDayOfMonth(dayInMonth.format(date));
    }
}
