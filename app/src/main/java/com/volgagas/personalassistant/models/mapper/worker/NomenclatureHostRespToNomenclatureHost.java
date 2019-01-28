package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.NomenclatureHostResponse;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:01, 28/01/2019.
 */
public class NomenclatureHostRespToNomenclatureHost extends Mapper<NomenclatureHostResponse,
        Nomenclature> {

    @Override
    public Nomenclature map(NomenclatureHostResponse value) {
        Nomenclature nomenclatureHost = new Nomenclature();
        fillData(nomenclatureHost, value);

        return nomenclatureHost;
    }

    @Override
    protected void fillData(Nomenclature nomenclatureHost, NomenclatureHostResponse response) {
        Timber.d("fill datq");
    }
}
