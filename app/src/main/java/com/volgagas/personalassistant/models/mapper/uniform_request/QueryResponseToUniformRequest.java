package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.network.QueryResponse;
import com.volgagas.personalassistant.models.network.query.QueryNetwork;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
            uniformRequest.setTitle(result.getTitle());
            uniformRequest.setUserName(result.getAuthor().getTitle());

            formatDate(uniformRequest, result.getDueDate());

            uniformRequests.add(uniformRequest);
        }
    }

    /* format date and time
     * */
    private void formatDate(UniformRequest uniformRequest, String baseData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm", new Locale("RU"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+4"));

        try {
            Date date = dateFormat.parse(baseData);
            uniformRequest.setEndedTime(formatDate.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
