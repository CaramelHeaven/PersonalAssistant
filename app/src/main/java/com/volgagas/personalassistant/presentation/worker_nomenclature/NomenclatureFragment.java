package com.volgagas.personalassistant.presentation.worker_nomenclature;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.volgagas.personalassistant.presentation.base.BaseFragment;

/**
 * Created by CaramelHeaven on 17:25, 15/01/2019.
 */
public class NomenclatureFragment extends BaseFragment {

    private Button btnFindNomenclature;

    public static NomenclatureFragment newInstance() {

        Bundle args = new Bundle();

        NomenclatureFragment fragment = new NomenclatureFragment();
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
