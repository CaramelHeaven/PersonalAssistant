package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_choose_action.ChooseActionActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclaturePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.NomenclatureBarcodeActivity;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class NomenclatureFragment extends BaseFragment implements NomenclatureView {

    private Button btnConfirm, btnToQRCode;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    private NomenclatureAdapter adapter;
    private String action;

    @InjectPresenter
    NomenclaturePresenter presenter;

    public static NomenclatureFragment newInstance(Task task, String action) {
        Bundle args = new Bundle();
        args.putParcelable("TASK", task);
        args.putString("ACTION", action);

        NomenclatureFragment fragment = new NomenclatureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nomenclature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        toolbar = view.findViewById(R.id.toolbar);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        btnToQRCode = view.findViewById(R.id.btn_to_qr_code);

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        action = getArguments().getString("ACTION");

        //enable NFC with delay 10 milliseconds
        new Handler().postDelayed(() -> {
            if (getActivity() != null) {
                ((ChooseActionActivity) getActivity()).setPermissionToEnableNfc(true);
                ((ChooseActionActivity) getActivity()).handlerNFC();
            }
        }, 10);

        btnConfirm.setOnClickListener(v -> {
            if (progressBar.getVisibility() == View.VISIBLE) {
                Toast.makeText(getActivity(), "Список еще грузится", Toast.LENGTH_SHORT).show();
            } else if (adapter.getNomenclatureList().size() == 0) {
                Toast.makeText(getActivity(), "Список элементов пуст", Toast.LENGTH_SHORT).show();
            } else {
                if (adapter.isNomenclaturesCountEqualsNull()) {
                    Toast.makeText(getActivity(), "Нельзя", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.clearNotChangedList(); // clear helper list, because we exit from this screen
                    presenter.createNomenclatures(adapter.getNomenclatureList());
//                    if (action.equals(Constants.ADD_MORE_NOMENCLATURES)) {
//                        //save data to cache before to send it to server
//                        CachePot.getInstance().putBarcodeCacheList(new ArrayList<>(adapter.getNomenclatureList()));
//
//                        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(SendNomenclaturesToServerWorker.class)
//                                .build();
//
//                        WorkManager.getInstance().enqueue(work);
//
//                        Toast.makeText(getActivity(), "SENDING", Toast.LENGTH_SHORT).show();
//                        //getActivity().finish();
//                    } else if (action.equals(Constants.USUAL)) {
//                        Intent intent = new Intent(getActivity(), GpaActivity.class);
//                        CachePot.getInstance().putBarcodeCacheList(new ArrayList<>(adapter.getNomenclatureList()));
//
//                        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(SendNomenclaturesToServerWorker.class)
//                                .setConstraints(new Constraints.Builder()
//                                        .setRequiredNetworkType(NetworkType.CONNECTED)
//                                        .build())
//                                .setInputData(new Data.Builder()
//                                        .putString("SERVICE_ORDER_ID", presenter.getTask().getIdTask())
//                                        .build())
//                                .build();
//
//                        WorkManager.getInstance().enqueue(work);
//
//                        Toast.makeText(getActivity(), "SENDING", Toast.LENGTH_SHORT).show();
//                        //startActivity(intent);
//                    }
                }
            }
        });

        btnToQRCode.setOnClickListener(v -> {
            presenter.clearNotChangedList();
            presenter.setNotChangedNomenclature(adapter.getNomenclatureList());
            startActivity(new Intent(getActivity(), NomenclatureBarcodeActivity.class));
        });

        provideRecyclerAndAdapter();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new NomenclatureAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.setOnButtonPlusMinusClickListener(new OnButtonPlusMinusClickListener() {
            @Override
            public void onHandleCount(int position, int status, int count) {
                adapter.getItemByPosition(position).setCount(count);
            }

            @Override
            public void onHandleEditText(int pos, int count) {
                adapter.getItemByPosition(pos).setCount(count);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        btnConfirm = null;
        btnToQRCode = null;
        toolbar = null;
        progressBar = null;
        recyclerView = null;
        super.onDestroyView();
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
    public void catastrophicError(Throwable throwable) {
        Toast.makeText(getActivity(), "Необработанная ошибка: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBaseList(List<Nomenclature> values) {
        if (values.size() > 0) {
            adapter.updateAdapter(values);
        }
    }

    @Override
    public void addNomenclatureToBaseList(Nomenclature value) {
        adapter.addItem(value);
    }

    @Override
    public void errorNomenclature() {
        Toast.makeText(getActivity(), "Номенклатура не найдена", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addedBarcodeNomenclaturesToBaseList(List<Nomenclature> values) {
        adapter.clear();
        adapter.addItems(values);
    }

    private void startedBackgroundService() {
//        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(SendTaskStartedWorker.class)
//                .build();

        //WorkManager.getInstance().enqueue(work);
    }

    public void showErrorCard() {
        Toasty.error(getActivity(), "Приложена не ваша карта").show();
    }
}
