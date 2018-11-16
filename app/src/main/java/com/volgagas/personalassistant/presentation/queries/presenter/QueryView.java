package com.volgagas.personalassistant.presentation.queries.presenter;

import com.volgagas.personalassistant.presentation.base.BaseView;

import java.util.List;

public interface QueryView<T> extends BaseView {
    void showItems(List<T> items);
}
