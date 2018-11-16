package com.volgagas.personalassistant.presentation.contracts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractPresenter;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractView;

public class ContractFragment extends BaseFragment implements ContractView {

    @InjectPresenter
    ContractPresenter presenter;

    public static ContractFragment newInstance() {

        Bundle args = new Bundle();

        ContractFragment fragment = new ContractFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contracts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
