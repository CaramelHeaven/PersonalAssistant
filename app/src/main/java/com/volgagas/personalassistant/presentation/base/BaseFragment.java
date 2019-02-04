package com.volgagas.personalassistant.presentation.base;

import com.arellomobile.mvp.MvpAppCompatFragment;

public abstract class BaseFragment extends MvpAppCompatFragment implements Presentable {
    protected BasePresenter basePresenter;

    @Override
    public void onStart() {
        super.onStart();
        initialBasePresenter();
        if (basePresenter != null) {
            basePresenter.setListenerUpdatedToken();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        if (basePresenter != null) {
            basePresenter.clearListenerIfScreenNotVisible();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        basePresenter = null;
        super.onDestroy();
    }

    public void setBasePresenter(BasePresenter basePresenter) {
        this.basePresenter = basePresenter;
    }
}
