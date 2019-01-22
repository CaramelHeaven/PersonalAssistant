package com.volgagas.personalassistant.presentation.worker_nomenclature_scan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.base.BaseFragment;

public class NomenclatureScanFragment extends BaseFragment {

    private ImageView ivNfc;
    private Toolbar toolbar;

    public static NomenclatureScanFragment newInstance() {

        Bundle args = new Bundle();

        NomenclatureScanFragment fragment = new NomenclatureScanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nomenclature_scan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ivNfc = view.findViewById(R.id.iv_nfc);
        toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ivNfc.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_up_down));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
