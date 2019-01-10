package com.volgagas.personalassistant.mapper;

import com.volgagas.personalassistant.models.mapper.query_template.QueriesTemplateResponseToQueryTemplate;
import com.volgagas.personalassistant.models.mapper.query_template.QueryTemplateMapper;
import com.volgagas.personalassistant.models.network.QueriesTemplateResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by CaramelHeaven on 09:18, 10/01/2019.
 */
public class QueryTemplateMapperTest {
    private ArrayList mockList;

    private QueryTemplateMapper queryTemplateMapper;
    private QueriesTemplateResponseToQueryTemplate queriesTemplateResponseToQueryTemplate;
    private QueriesTemplateResponse queriesTemplateResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //mock creation
        mockList = mock(ArrayList.class);
        queryTemplateMapper = mock(QueryTemplateMapper.class);
        queriesTemplateResponseToQueryTemplate = mock(QueriesTemplateResponseToQueryTemplate.class);
        queriesTemplateResponse = mock(QueriesTemplateResponse.class);

        fillResponseData();
    }

    @Test
    public void kek() {
        //using mock object
        mockList.add("one");
        mockList.clear();

        //verification
        verify(mockList).add("one");
        verify(mockList).clear();
    }

    private void fillResponseData() {

    }
}
