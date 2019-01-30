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
import com.volgagas.personalassistant.presentation.worker_gpa.GpaActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclaturePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by CaramelHeaven on 17:25, 15/01/2019.
 */
public class NomenclatureFragment extends BaseFragment implements NomenclatureView {

    private Button btnConfirm;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ProgressBar progressBar;

    private NomenclatureAdapter adapter;

    @InjectPresenter
    NomenclaturePresenter presenter;

    public static NomenclatureFragment newInstance(Task task) {
        Bundle args = new Bundle();
        args.putParcelable("TASK", task);

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

        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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
                Intent intent = new Intent(getActivity(), GpaActivity.class);
                startActivity(intent);
            }
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

        adapter.setOnButtonPlusMinusClickListener((position, status, count) ->
                adapter.getItemByPosition(position).setCount(count));
    }

    @Override
    public void onDestroyView() {
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
    public void showBaseList(List<Nomenclature> values) {
        if (values.size() > 0) {
            adapter.updateAdapter(values);
        }
    }

    @Override
    public void addNomenclatureToBaseList(Nomenclature value) {

    }

    public void showErrorCard() {
        Toasty.error(getActivity(), "Приложена не ваша карта").show();
    }

    @Override
    public void initialBasePresenter() {
        setBasePresenter(presenter);
    }
}
