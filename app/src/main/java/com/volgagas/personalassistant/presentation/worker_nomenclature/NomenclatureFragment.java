package com.volgagas.personalassistant.presentation.worker_nomenclature;

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

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_choose_action.ChooseActionActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclaturePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:25, 15/01/2019.
 */
public class NomenclatureFragment extends BaseFragment implements NomenclatureView {

    private Button btnConfirm;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

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
        toolbar = view.findViewById(R.id.toolbar);
        btnConfirm = view.findViewById(R.id.btn_confirm);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //((BaseActivity) getActivity()).handlerNFC();

        //enable NFC with delay 1 second
        new Handler().postDelayed(() -> {
            ((ChooseActionActivity) getActivity()).setPermissionToEnableNfc(true);
            ((ChooseActionActivity) getActivity()).handlerNFC();
        }, 1000);

        btnConfirm.setOnClickListener(v -> {
            //Intent intent = new Intent(getActivity(), GpaActivity.class);
            //intent.putExtra("TASK", (Task) getArguments().getParcelable("TASK"));

            //startActivity(intent);

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

        adapter = new NomenclatureAdapter(presenter.loadData());
        recyclerView.setAdapter(adapter);

        adapter.setOnButtonPlusMinusClickListener((position, status, count) -> {
            Timber.d("kek: " + count);
            adapter.getItemByPosition(position).setCount(String.valueOf(count));
        });
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
