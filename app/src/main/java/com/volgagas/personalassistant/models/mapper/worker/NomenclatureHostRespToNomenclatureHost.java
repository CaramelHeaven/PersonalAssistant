package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.NomenclatureHostResponse;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:01, 28/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class NomenclatureHostRespToNomenclatureHost extends Mapper<NomenclatureHostResponse,
        Nomenclature> {

    @Override
    public Nomenclature map(NomenclatureHostResponse value) {
        //make sure that get quantity is a number
        return new Nomenclature(value.getName(), Integer.parseInt(value.getQuantity()), value.getUnit());
    }

    @Override
    protected void fillData(Nomenclature nomenclatureHost, NomenclatureHostResponse response) {
        //nothing
    }
}
