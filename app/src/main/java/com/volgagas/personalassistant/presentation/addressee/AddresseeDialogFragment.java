package com.volgagas.personalassistant.presentation.addressee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.addressee.presenter.AddresseeView;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class AddresseeDialogFragment extends MvpAppCompatDialogFragment implements AddresseeView {

    public static AddresseeDialogFragment newInstance() {

        Bundle args = new Bundle();

        AddresseeDialogFragment fragment = new AddresseeDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_addressee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
