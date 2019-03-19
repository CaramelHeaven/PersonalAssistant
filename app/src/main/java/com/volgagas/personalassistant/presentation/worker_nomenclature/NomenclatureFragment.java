package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_choose_action.ChooseActionActivity;
import com.volgagas.personalassistant.presentation.worker_gpa.GpaActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclaturePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.NomenclatureBarcodeActivity;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.callbacks.OnButtonPlusMinusClickListener;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;
import com.volgagas.personalassistant.utils.item_touch.ItemTouchAdapterNomenclature;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class NomenclatureFragment extends BaseFragment implements NomenclatureView {

    private Button btnConfirm, btnToQRCode;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

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

//        btnConfirm.setEnabled(false);
//        btnToQRCode.setEnabled(false);

        btnConfirm.setOnClickListener(v -> {
            if (progressBar.getVisibility() == View.VISIBLE) {
                Toast.makeText(getActivity(), "Список еще грузится", Toast.LENGTH_SHORT).show();
            } else if (adapter.getNomenclatureList().size() == 0) {
                Toast.makeText(getActivity(), "Список элементов пуст", Toast.LENGTH_SHORT).show();
            } else {
                if (adapter.isNomenclaturesCountEqualsNull()) {
                    Toast.makeText(getActivity(), "В списке присутствует номенклатура с нулевым значением", Toast.LENGTH_SHORT).show();
                } else {
                    if (adapter.getNomenclatureList().size() > 0) {
                        Boolean checkedNewData = presenter.createNomenclatures(adapter.getNomenclatureList());

                        if (checkedNewData) {
                            progressDialog = new ProgressDialog(getActivity());
                            progressDialog.setMessage("Посылаем номенклатуры");
                            progressDialog.setCanceledOnTouchOutside(false);

                            progressDialog.show();
                            presenter.sendNomenclaturesToServer(); // create or update nomenclatures
                        } else {
                            Toast.makeText(getActivity(), "Вы ничего не добавили", Toast.LENGTH_SHORT).show();
                            acceptAndCloseView();
                        }
                    }
                }
            }
        });

        btnToQRCode.setOnClickListener(v -> {
            presenter.setLoadNomenclaturesFromServer(false);
            presenter.clearHelperList();
            presenter.setHelperNomenclatureList(adapter.getNomenclatureList());

            startActivity(new Intent(getActivity(), NomenclatureBarcodeActivity.class));
        });

        provideRecyclerAndAdapter();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter.isLoadNomenclaturesFromServer()) {
            presenter.presenterLoadData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("on resume: ");
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        adapter = new NomenclatureAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        ItemTouchAdapterNomenclature callback = new ItemTouchAdapterNomenclature(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

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

        adapter.setMyOnItemClickListener(position -> {
            adapter.removeItemByPosition(position);
            if (adapter.getItemCount() == 0) {
                //todo nothing
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        adapter.clear();
        adapter.notifyDataSetChanged();
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

    @Override
    public void acceptAndCloseView() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        presenter.setLoadNomenclaturesFromServer(true);

        if (action.equals(Constants.ADD_MORE_NOMENCLATURES)) {
            getActivity().finish();
        } else if (action.equals(Constants.USUAL)) {
            Intent intent = new Intent(getActivity(), GpaActivity.class);
            startActivity(intent);
        }
    }
}
