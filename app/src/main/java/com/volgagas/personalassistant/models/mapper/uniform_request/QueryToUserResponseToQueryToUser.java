package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.network.QueryToUserResponse;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserAssignedTo;
import com.volgagas.personalassistant.models.network.query_to_user.QueryToUserNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 15:31, 17/12/2018.
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
            queryToUser.setDate(query.getDueDate());
            queryToUser.setPriority(query.getPriority());

            List<String> authors = new ArrayList<>();

            for (QueryToUserAssignedTo assignedTo : query.getAssignedTo()) {
                authors.add(assignedTo.getTitle());
            }

            queryToUser.setAssignedTo(authors);

            queryToUsers.add(queryToUser);
        }
    }
}
