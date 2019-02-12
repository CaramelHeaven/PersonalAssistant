package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.network.QueryToUserResponse;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserAssignedTo;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserNetwork;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by CaramelHeaven on 15:31, 17/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class QueryToUserResponseToQueryToUser extends Mapper<QueryToUserResponse, List<QueryToUser>> {

    @Override
    public List<QueryToUser> map(QueryToUserResponse value) {
        List<QueryToUser> queryToUsers = new ArrayList<>();
        fillData(queryToUsers, value);

        return queryToUsers;
    }

    @Override
    protected void fillData(List<QueryToUser> queryToUsers, QueryToUserResponse queryToUserResponse) {
        for (QueryToUserNetwork query : queryToUserResponse.getValue()) {
            QueryToUser queryToUser = new QueryToUser();

            queryToUser.setTitle(query.getTitle());
            queryToUser.setComment(query.getComment());
            queryToUser.setPriority(query.getPriority());
            queryToUser.setCategory(query.getCategoryData().getTitle());

            formatDate(queryToUser, query.getDueDate());

            List<String> authors = new ArrayList<>();

            for (QueryToUserAssignedTo assignedTo : query.getAssignedTo()) {
                authors.add(assignedTo.getTitle());
            }

            queryToUser.setAssignedTo(authors);

            queryToUsers.add(queryToUser);
        }
    }

    /* format date and time
     * */
    private void formatDate(QueryToUser queryToUser, String baseData) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm", new Locale("RU"));
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+4"));

        try {
            Date date = dateFormat.parse(baseData);
            queryToUser.setDate(formatDate.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
