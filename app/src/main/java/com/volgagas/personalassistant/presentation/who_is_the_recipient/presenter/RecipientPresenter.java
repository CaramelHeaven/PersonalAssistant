package com.volgagas.personalassistant.presentation.who_is_the_recipient.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.volgagas.personalassistant.data.repository.MainRemoteRepository;
import com.volgagas.personalassistant.domain.MainRepository;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.models.network.user_id.UserId;
import com.volgagas.personalassistant.presentation.base.BasePresenter;
import com.volgagas.personalassistant.utils.channels.pass_data.PassDataChannel;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
@InjectViewState
public class RecipientPresenter extends BasePresenter<RecipientView> {

    private MainRepository repository;
    private PassDataChannel passDataChannel;
    private CompositeDisposable disposable;

    public RecipientPresenter() {
        repository = MainRemoteRepository.getInstance();
        disposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable.add(repository.getSearchedUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::successfulResult, this::handlerErrorsFromBadRequests));

        disposable.add(passDataChannel.getInstance().getSubject()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> getViewState().sendUserData(result)));
    }

    private void successfulResult(List<User> users) {
        getViewState().hideProgress();
        getViewState().showUsers(users);
    }

    @SuppressLint("CheckResult")
    public void sendToServer(RequestData data, List<User> userList) {
        Timber.d("SEND");
        getViewState().showProgress();
        Single.just(userList)
                .subscribeOn(Schedulers.io())
                .flattenAsObservable((Function<List<User>, Iterable<User>>) users -> userList)
                .flatMap(user -> repository.getUserIdByUserName(user.getModifiedNormalName()))
                .toList()
                .flatMap((Function<List<UserId>, SingleSource<JsonObject>>) userIds -> {
                    JsonObject dataToServer = new JsonObject();
                    JsonObject metadataListItem = new JsonObject();
                    JsonObject metadataInt = new JsonObject();
                    JsonObject whoIsRecipient = new JsonObject();
                    JsonArray jsonResultRecipients = new JsonArray(userIds.size());

                    metadataListItem.add("type", new JsonPrimitive("SP.Data.List7ListItem"));
                    metadataInt.add("type", new JsonPrimitive("Collection(Edm.Int32)"));

                    //users who recipient
                    for (UserId userId : userIds) {
                        jsonResultRecipients.add(userId.getId());
                    }

                    whoIsRecipient.add("__metadata", metadataInt);
                    whoIsRecipient.add("results", jsonResultRecipients);

                    dataToServer.add("__metadata", metadataListItem);
                    dataToServer.add("Title", new JsonPrimitive(data.getTitle()));
                    dataToServer.add("CategoryLookup0Id", new JsonPrimitive(data.getCategory()));
                    dataToServer.add("Comment", new JsonPrimitive(data.getDescription()));
                    dataToServer.add("DueDate", new JsonPrimitive(data.getEndDate()));
                    dataToServer.add("LastText", new JsonPrimitive(data.getDescription()));
                    dataToServer.add("AssignedToId", whoIsRecipient);

                    if (data.isImportant()) {
                        dataToServer.add("Priority", new JsonPrimitive("(1) Высокая"));
                    } else {
                        dataToServer.add("Priority", new JsonPrimitive("(2) Обычная"));
                    }
                    return Single.just(dataToServer);
                })
                .flatMap(jsonObject -> repository.createUniformQueryItem(jsonObject))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    getViewState().showProgress();
                    getViewState().finish();
                    Timber.d("get result: " + result.toString());
                }, t -> {
                    Timber.d("throwable: " + t.getMessage());
                    Timber.d("throwable: " + t.getCause());
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void handlerErrorsFromBadRequests(Throwable throwable) {

    }

    @Override
    protected void handlerErrorInSuccessfulResult(List<Response<Void>> result) {

    }
}