package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.kiosk.TaskKioskResponseToTaskTemplate;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:43, 27/12/2018.
 */
public class TaskMapper {
    private final TaskResponseToTask taskResponseToTask;
    private final TaskResponseToTaskHistory taskResponseToTaskHistory;
    private final TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate;
    private final SubTaskResponseToSubTask subTaskResponseToSubTask;
    private final NomenclatureResponseToNomenclature nomenclatureResponseToNomenclature;

    public TaskMapper(TaskResponseToTask taskResponseToTask, TaskResponseToTaskHistory taskResponseToTaskHistory,
                      TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate,
                      SubTaskResponseToSubTask subTaskResponseToSubTask,
                      NomenclatureResponseToNomenclature nomenclatureResponseToNomenclature) {
        this.taskResponseToTask = taskResponseToTask;
        this.taskResponseToTaskHistory = taskResponseToTaskHistory;
        this.taskKioskResponseToTaskTemplate = taskKioskResponseToTaskTemplate;
        this.subTaskResponseToSubTask = subTaskResponseToSubTask;
        this.nomenclatureResponseToNomenclature = nomenclatureResponseToNomenclature;
    }

    public List<TaskHistory> mapHistoryTasks(TaskResponse response) {
        return taskResponseToTaskHistory.map(response);
    }

    public List<Task> mapTasks(TaskResponse response) {
        return taskResponseToTask.map(response);
    }

    public List<TaskTemplate> map(TaskKioskResponse response) {
        return taskKioskResponseToTaskTemplate.map(response);
    }

    public List<SubTaskViewer> map(SubTaskResponse response) {
        return subTaskResponseToSubTask.map(response);
    }

    public Nomenclature map(NomenclatureResponse response) {
        return nomenclatureResponseToNomenclature.map(response);
    }
}
