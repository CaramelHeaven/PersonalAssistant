package com.volgagas.personalassistant.presentation.base;

/**
 * Created by CaramelHeaven on 12:58, 30/01/2019.
 */
public interface Presentable {
    /**
     * Initial presenter from each child presenter for control subscribe/unsubscribe
     * listener updated token
     */
    void initialBasePresenter();
}
