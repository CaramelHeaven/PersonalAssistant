package com.volgagas.personalassistant.models.model.common;

import com.volgagas.personalassistant.models.model.Contract;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:06, 22/02/2019.
 * QueryToUser, QueryFromUser and contracts(from d365) united
 */
public class QueriesCommon {
    private List<QueryToUser> queryToUserList;
    private List<UniformRequest> queryFromUserList;
    private List<Contract> contractList;

    @Override
    public String toString() {
        return "QueriesCommon{" +
                "queryToUserList=" + queryToUserList +
                ", queryFromUserList=" + queryFromUserList +
                ", contractList=" + contractList +
                '}';
    }

    public List<QueryToUser> getQueryToUserList() {
        return queryToUserList;
    }

    public void setQueryToUserList(List<QueryToUser> queryToUserList) {
        this.queryToUserList = queryToUserList;
    }

    public List<UniformRequest> getQueryFromUserList() {
        return queryFromUserList;
    }

    public void setQueryFromUserList(List<UniformRequest> queryFromUserList) {
        this.queryFromUserList = queryFromUserList;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }
}
