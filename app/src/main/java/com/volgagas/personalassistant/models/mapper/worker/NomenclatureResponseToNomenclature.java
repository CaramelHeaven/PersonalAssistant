package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.models.network.NomenclatureResponse;
import com.volgagas.personalassistant.models.network.nomenclature.NomenclatureNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:17, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
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
        for (NomenclatureNetwork network : nomenclatureResponse.getValue()) {
            Nomenclature data = new Nomenclature(network.getItemId(), network.getQty(),
                    network.getUnit());
            data.setProjectCategoryId(network.getProjCategoryId());

            nomenclatures.add(data);
        }
    }
}
