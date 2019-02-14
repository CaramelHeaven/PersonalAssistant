package com.volgagas.personalassistant.presentation.worker_choose_action;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.worker_gpa.GpaActivity;
import com.volgagas.personalassistant.presentation.worker_nomenclature.NomenclatureFragment;
import com.volgagas.personalassistant.utils.Constants;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 12:40, 16/01/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * Main UI for User.
 */
public class ChooseActionFragment extends BaseFragment {

    private Button btnToGpa, btnToNomenclatureList;
    private Toolbar toolbar;
    private RelativeLayout rlSketch;
    private Animation animation;

    public static ChooseActionFragment newInstance(Task task) {
        Bundle args = new Bundle();
        args.putParcelable("TASK", task);

        ChooseActionFragment fragment = new ChooseActionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_choose_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnToNomenclatureList = view.findViewById(R.id.btn_to_nomenclature);
        btnToGpa = view.findViewById(R.id.btn_to_gpa);
        toolbar = view.findViewById(R.id.toolbar);
        rlSketch = view.findViewById(R.id.rl_sketch);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //animation
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_fall_down);
        rlSketch.startAnimation(animation);

        btnToGpa.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GpaActivity.class);
            intent.putExtra("TASK", (Task) getArguments().getParcelable("TASK"));

            startActivity(intent);
        });

        btnToNomenclatureList.setOnClickListener(v ->
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,
                                NomenclatureFragment.newInstance(getArguments().getParcelable("TASK"),
                                        Constants.USUAL))
                        .addToBackStack(null)
                        .commit());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!animation.hasStarted()) {
            animation.start();
        }
    }

    @Override
    public void onPause() {
        animation.cancel();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        toolbar = null;
        rlSketch = null;
        btnToGpa = null;
        btnToNomenclatureList = null;
        super.onDestroyView();
    }

}
