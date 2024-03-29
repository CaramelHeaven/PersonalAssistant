package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.kiosk.TaskKioskResponseToTaskTemplate;
import com.volgagas.personalassistant.models.model.SubTaskViewer;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.kiosk.TaskTemplate;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.model.worker.NomenclatureDimension;
import com.volgagas.personalassistant.models.model.worker.TaskHistory;
import com.volgagas.personalassistant.models.network.BarcodeResponse;
import com.volgagas.personalassistant.models.network.DimensionMappingResponse;
import com.volgagas.personalassistant.models.network.NomenclatureHostResponse;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;
import com.volgagas.personalassistant.models.network.SubTaskResponse;
import com.volgagas.personalassistant.models.network.TaskKioskResponse;
import com.volgagas.personalassistant.models.network.TaskResponse;

import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:43, 27/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class TaskMapper {
    private final TaskResponseToTask taskResponseToTask;
    private final TaskResponseToTaskHistory taskResponseToTaskHistory;
    private final TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate;
    private final SubTaskResponseToSubTask subTaskResponseToSubTask;
    private final NomenclatureResponseToNomenclature nomenclatureResponseToNomenclature;
    private final NomenclatureHostRespToNomenclatureHost nomenclatureHostRespToNomenclatureHost;
    private final BarcodeResponseToBarcode barcodeResponseToBarcode;
    private final DimMapResponseToNomenclDimension dimMapResponseToNomenclDimension;

    public TaskMapper(TaskResponseToTask taskResponseToTask, TaskResponseToTaskHistory taskResponseToTaskHistory,
                      TaskKioskResponseToTaskTemplate taskKioskResponseToTaskTemplate,
                      SubTaskResponseToSubTask subTaskResponseToSubTask,
                      NomenclatureResponseToNomenclature nomenclatureResponseToNomenclature,
                      NomenclatureHostRespToNomenclatureHost nomenclatureHostRespToNomenclatureHost,
                      BarcodeResponseToBarcode barcodeResponseToBarcode,
                      DimMapResponseToNomenclDimension dimMapResponseToNomenclDimension) {
        this.taskResponseToTask = taskResponseToTask;
        this.taskResponseToTaskHistory = taskResponseToTaskHistory;
        this.taskKioskResponseToTaskTemplate = taskKioskResponseToTaskTemplate;
        this.subTaskResponseToSubTask = subTaskResponseToSubTask;
        this.nomenclatureResponseToNomenclature = nomenclatureResponseToNomenclature;
        this.nomenclatureHostRespToNomenclatureHost = nomenclatureHostRespToNomenclatureHost;
        this.barcodeResponseToBarcode = barcodeResponseToBarcode;
        this.dimMapResponseToNomenclDimension = dimMapResponseToNomenclDimension;
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

    public List<Nomenclature> map(NomenclatureResponse response) {
        return nomenclatureResponseToNomenclature.map(response);
    }

    public Nomenclature map(NomenclatureHostResponse response) {
        return nomenclatureHostRespToNomenclatureHost.map(response);
    }

    public Barcode map(BarcodeResponse response) {
        return barcodeResponseToBarcode.map(response);
    }

    public NomenclatureDimension map(DimensionMappingResponse response) {
        return dimMapResponseToNomenclDimension.map(response);
    }
}
