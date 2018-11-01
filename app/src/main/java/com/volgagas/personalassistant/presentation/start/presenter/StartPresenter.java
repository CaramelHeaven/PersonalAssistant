package com.volgagas.personalassistant.presentation.start.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.channels.StartChannel;
import com.volgagas.personalassistant.utils.channels.ThreePermissions;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class StartPresenter extends BasePresenter<StartView> {

    private MainRepository repository;
    private CompositeDisposable disposable;
    private String dataCodekey = "";

    @SuppressLint("CheckResult")
    public StartPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();

        disposable.add(StartChannel.getInstance().getPermissionSubject()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.allValuesIsTrue()) {
                        getViewState().goToMainMenu();
                    }
                }));
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    public void getUserData(String data) {
        getViewState().showProgress();
        disposable.add(repository.getCardInfo(data)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<User, SingleSource<User>>) user -> {
                    if (user.getCategory() != null) {
                        if (!user.getCategory().equals(Constants.EQUIPMENT)) {
                            return Single.just(user);
                        } else {
                            return Single.error(new IllegalArgumentException("Equipment"));
                        }
                    } else {
                        return Single.just(user);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResponse, this::handlerErrorsFromBadRequests));
    }

    private void successfulResponse(User user) {
        CacheUser.getUser().setBaseFields(user);

        ThreePermissions permissions = ThreePermissions.getInstance();
        permissions.setServer(true);
        StartChannel.sendData(permissions);
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {
        getViewState().hideProgress();
        if (throwable instanceof IllegalArgumentException) {
            getViewState().resultMatchedWithEquipment();
        } else {
            getViewState().commonError();
        }
    }

    public void setDataCodekey(String dataCodekey) {
        this.dataCodekey = dataCodekey;
    }

    public String getDataCodekey() {
        return dataCodekey;
    }
}
