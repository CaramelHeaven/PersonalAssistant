package com.volgagas.personalassistant.presentation.start_login_card;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.utils.bus.RxBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by CaramelHeaven on 11:56, 01/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class StartLoginCardFragment extends Fragment {

    private CompositeDisposable disposable;

    private TextView tvTitle;

    public static StartLoginCardFragment newInstance() {

        Bundle args = new Bundle();

        StartLoginCardFragment fragment = new StartLoginCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_login_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTitle = view.findViewById(R.id.tv_title);

        disposable = new CompositeDisposable();

        disposable.add(RxBus.getInstance().getCommonChannel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    switch (result) {
                        case "TITLE_HIDE":
                            tvTitle.setVisibility(View.GONE);
                            break;
                        case "TITLE_SHOW":
                            tvTitle.setVisibility(View.VISIBLE);
                            break;
                    }
                }));
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBus.getInstance().passDataToCommonChannel("ENABLE_NFC");
    }

    @Override
    public void onDestroyView() {
        disposable.clear();
        tvTitle = null;
        super.onDestroyView();
    }
}
