package com.volgagas.personalassistant.presentation.projects.query_create.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.volgagas.personalassistant.utils.channels.pass_data.PassData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class QueryCreatePresenter extends MvpPresenter<QueryCreateView>  {

    private List<String> dataCategory;
    private PassData passData;
    private CompositeDisposable disposable;

    public QueryCreatePresenter() {
        dataCategory = new ArrayList<>();
        disposable = new CompositeDisposable();
        providePassData();
    }

    private void providePassData() {
        disposable.add(passData.getInstance().getSubject()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().showNextPage()));
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

    }

}
