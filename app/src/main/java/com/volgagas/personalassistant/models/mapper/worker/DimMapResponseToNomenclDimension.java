package com.volgagas.personalassistant.models.mapper.worker;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.worker.NomenclatureDimension;
import com.volgagas.personalassistant.models.network.DimensionMappingResponse;

/**
 * Created by CaramelHeaven on 16:41, 18/03/2019.
 */
public class DimMapResponseToNomenclDimension extends Mapper<DimensionMappingResponse, NomenclatureDimension> {
    @Override
    public NomenclatureDimension map(DimensionMappingResponse value) {
        NomenclatureDimension nomenclature = new NomenclatureDimension();
        fillData(nomenclature, value);

        return nomenclature;
    }

    @Override
    protected void fillData(NomenclatureDimension nomenclature, DimensionMappingResponse dimensionMappingResponse) {
        if (dimensionMappingResponse.getValue().size() > 0) {
            nomenclature.setInventDimId(dimensionMappingResponse.getValue().get(0).getInventDimId());
        }
    }
}
