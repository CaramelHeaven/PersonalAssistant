package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;

/**
 * Created by CaramelHeaven on 12:17, 16/01/2019.
 */
public class NomenclatureResponseToNomenclature extends Mapper<NomenclatureResponse, Nomenclature> {
    @Override
    public Nomenclature map(NomenclatureResponse value) {
        Nomenclature nomenclature = new Nomenclature();
        fillData(nomenclature, value);

        return nomenclature;
    }

    @Override
    protected void fillData(Nomenclature nomenclatures, NomenclatureResponse nomenclatureResponse) {

    }
}
