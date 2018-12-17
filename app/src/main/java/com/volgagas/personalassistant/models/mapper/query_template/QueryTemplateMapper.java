package com.volgagas.personalassistant.models.mapper.query_template;

import com.volgagas.personalassistant.models.model.queries.QueryTemplate;
import com.volgagas.personalassistant.models.network.QueriesTemplateResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 11:31, 17/12/2018.
 */
public class QueryTemplateMapper {
    private final QueriesTemplateResponseToQueryTemplate queriesTemplateResponseToQueryTemplate;

    public QueryTemplateMapper(QueriesTemplateResponseToQueryTemplate queriesTemplateResponseToQueryTemplate) {
        this.queriesTemplateResponseToQueryTemplate = queriesTemplateResponseToQueryTemplate;
    }

    public List<QueryTemplate> map(QueriesTemplateResponse response) {
        return queriesTemplateResponseToQueryTemplate.map(response);
    }
}
