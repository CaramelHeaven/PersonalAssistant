package com.volgagas.personalassistant.models.mapper.uniform_request;

import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;
import com.volgagas.personalassistant.models.network.QueryResponse;
import com.volgagas.personalassistant.models.network.QueryToUserResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 13:22, 09.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UniformRequestMapper {
    private final QueryResponseToUniformRequest queryResponseToUniformRequest;
    private final QueryToUserResponseToQueryToUser queryToUserResponseToQueryToUser;

    public UniformRequestMapper(QueryResponseToUniformRequest queryResponseToUniformRequest,
                                QueryToUserResponseToQueryToUser queryToUserResponseToQueryToUser) {
        this.queryResponseToUniformRequest = queryResponseToUniformRequest;
        this.queryToUserResponseToQueryToUser = queryToUserResponseToQueryToUser;
    }

    public List<UniformRequest> map(QueryResponse value) {
        return queryResponseToUniformRequest.map(value);
    }

    public List<QueryToUser> map(QueryToUserResponse value) {
        return queryToUserResponseToQueryToUser.map(value);
    }
}
