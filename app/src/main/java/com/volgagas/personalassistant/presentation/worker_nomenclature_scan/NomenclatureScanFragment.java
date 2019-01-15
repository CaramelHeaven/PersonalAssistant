package com.volgagas.personalassistant.presentation.worker_nomenclature_scan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;

public class NomenclatureScanFragment extends BaseFragment {

    public static NomenclatureScanFragment newInstance() {

        Bundle args = new Bundle();

        NomenclatureScanFragment fragment = new NomenclatureScanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
