package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.models.network.QueryResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:22, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UniformRequestMapper {
    private final QueryResponseToUniformRequest queryResponseToUniformRequest;

    public UniformRequestMapper(QueryResponseToUniformRequest queryResponseToUniformRequest) {
        this.queryResponseToUniformRequest = queryResponseToUniformRequest;
    }

    public List<UniformRequest> map(QueryResponse value) {
        return queryResponseToUniformRequest.map(value);
    }
}
