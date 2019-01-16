package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Nomenclature;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclaturePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature.presenter.NomenclatureView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_scan.NomenclatureScanFragment;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:25, 15/01/2019.
 */
public class NomenclatureFragment extends BaseFragment implements NomenclatureView {

    private Button btnFindNomenclature;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private NomenclatureAdapter adapter;

    @InjectPresenter
    NomenclaturePresenter presenter;

    public static NomenclatureFragment newInstance() {

        Bundle args = new Bundle();

        NomenclatureFragment fragment = new NomenclatureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_nomenclature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        toolbar = view.findViewById(R.id.toolbar);
        btnFindNomenclature = view.findViewById(R.id.btn_find_nomenclature);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnFindNomenclature.setOnClickListener(v -> {
            Timber.d("kek");
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, NomenclatureScanFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        });

        provideRecyclerAndAdapter();
    }

    private void provideRecyclerAndAdapter() {

    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        btnFindNomenclature = null;
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
