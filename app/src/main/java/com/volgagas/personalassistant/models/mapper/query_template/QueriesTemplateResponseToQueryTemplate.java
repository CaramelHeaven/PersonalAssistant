package com.volgagas.personalassistant.models.mapper.query_template;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.models.network.QueriesTemplateResponse;
import com.volgagas.personalassistant.models.network.all_queries_template.QueriesNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 11:31, 17/12/2018.
 */
public class QueriesTemplateResponseToQueryTemplate extends Mapper<QueriesTemplateResponse, List<QueryTemplate>> {

    @Override
    public List<QueryTemplate> map(QueriesTemplateResponse value) {
        List<QueryTemplate> queryTemplates = new ArrayList<>();
        fillData(queryTemplates, value);

        return queryTemplates;
    }

    @Override
    protected void fillData(List<QueryTemplate> queryTemplates, QueriesTemplateResponse queriesTemplateResponse) {
        for (QueriesNetwork query : queriesTemplateResponse.getQueriesNetworkList()) {
            QueryTemplate queryTemplate = new QueryTemplate(query.getId(), query.getTitle());

            queryTemplates.add(queryTemplate);
        }
    }


}
