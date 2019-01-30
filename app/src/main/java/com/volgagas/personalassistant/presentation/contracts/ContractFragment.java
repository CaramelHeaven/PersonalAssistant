package com.volgagas.personalassistant.presentation.contracts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractPresenter;
import com.volgagas.personalassistant.presentation.contracts.presenter.ContractView;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;

import timber.log.Timber;

public class ContractFragment extends BaseFragment implements ContractView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private ContractAdapter adapter;

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
        recyclerView = view.findViewById(R.id.recyclerView);

        provideRecyclerAndAdapter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new ContractAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            Timber.d("clicK: " + position);
            Timber.d("clicK: " + position);
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void initialBasePresenter() {
        //nothing
    }
}
