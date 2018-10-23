package com.volgagas.personalassistant.presentation.projects.query_create.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class QueryCreatePresenter extends MvpPresenter<QueryCreateView> {

    private List<String> dataCategory;

    public QueryCreatePresenter() {
        dataCategory = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public List<String> provideDataCategory() {
        if (dataCategory.size() == 0) {
            fillDataCategory();
        }
        return dataCategory;
    }

    private void fillDataCategory() {
        dataCategory.add("Заявка в ИТ");
        dataCategory.add("Заявка в АХО");
        dataCategory.add("Заявка в АХО УКПГ");
        dataCategory.add("Заявка в ОТЛ");
        dataCategory.add("Заявка в HR");
    }
}
