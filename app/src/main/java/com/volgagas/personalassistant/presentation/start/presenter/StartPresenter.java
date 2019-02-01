package com.volgagas.personalassistant.presentation.start.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.model.UserDynamics;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;
import com.volgagas.personalassistant.utils.channels.CommonChannel;
import com.volgagas.personalassistant.utils.channels.check_auth.ThreePermissions;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

@InjectViewState
public class StartPresenter extends BasePresenter<StartView> {

    private MainRepository repository;
    private String dataCodekey = "";

    @SuppressLint("CheckResult")
    public StartPresenter() {
        super();
        repository = MainRemoteRepository.getInstance();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //subscriber to get permissions state for start MainActivity
        disposable.add(CommonChannel.getInstance().getPermissionsSubject()
                .subscribeOn(Schedulers.io())
                .filter(ThreePermissions::allValuesIsTrue)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().goToMainMenu()));

        //subscriber to get enable NFC reader
        disposable.add(RxBus.getInstance().getCommonChannel()
                .filter(result -> result.equals("ENABLE_NFC"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().enableNFC()));
    }

    //TODO rewrite this shit
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
                .flatMap((Function<User, SingleSource<UserDynamics>>) user -> {
                    //if successful user data
                    if (user.getName() != null && !user.getName().equals("")) {
                        CacheUser.getUser().setBaseFields(user);
                        return repository.getPersonalUserNumber(user.getName());
                    } else {
                        return Single.just(new UserDynamics());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResponse, this::handlerErrorsFromBadRequests));
    }

    private void successfulResponse(UserDynamics userDynamics) {
        ThreePermissions permissions = ThreePermissions.getInstance();
        if (userDynamics.getPersonalNumber() == null || userDynamics.getPersonalNumber().equals("")) {
            permissions.setServer(false);
            CommonChannel.sendPermissions(permissions);

            //Show user if we get card data with empty fields
            getViewState().showErrorToEnter();
        } else {
            CacheUser.getUser().setPersonalDynamics365Number(userDynamics.getPersonalNumber());

            permissions.setServer(true);
            CommonChannel.sendPermissions(permissions);
        }
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

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }

    @Override
    protected void tokenUpdatedCallLoadDataAgain() {

    }

    @Override
    protected void loadData() {

    }

    public void setDataCodekey(String dataCodekey) {
        this.dataCodekey = dataCodekey;
    }

    public String getDataCodekey() {
        return dataCodekey;
    }
}
