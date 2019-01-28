package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 12:17, 16/01/2019.
 */
public class NomenclatureResponseToNomenclature extends Mapper<NomenclatureResponse, List<Nomenclature>> {
    @Override
    public List<Nomenclature> map(NomenclatureResponse value) {
        List<Nomenclature> list = new ArrayList<>();
        fillData(list, value);

        return list;
    }

    @Override
    protected void fillData(List<Nomenclature> nomenclatures, NomenclatureResponse nomenclatureResponse) {

    }
}
