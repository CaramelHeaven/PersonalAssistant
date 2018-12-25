package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.network.QueryResponse;
import com.volgagas.personalassistant.models.network.query.QueryNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:23, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryResponseToUniformRequest extends Mapper<QueryResponse, List<UniformRequest>> {

    @Override
    public List<UniformRequest> map(QueryResponse value) {
        List<UniformRequest> uniformRequests = new ArrayList<>();
        fillData(uniformRequests, value);
        return uniformRequests;
    }

    @Override
    protected void fillData(List<UniformRequest> uniformRequests, QueryResponse queryResponse) {
        Timber.d("queryResponse");
        for (QueryNetwork result : queryResponse.getQueryNetwork()) {
            UniformRequest uniformRequest = new UniformRequest();

            uniformRequest.setPriority(result.getPriority());
            uniformRequest.setDescription(result.getComment());
            uniformRequest.setEndedTime(result.getDueDate());
            uniformRequest.setTitle(result.getTitle());
            uniformRequest.setUserName(result.getAuthor().getTitle());

            uniformRequests.add(uniformRequest);
        }
    }
}
