package com.volgagas.personalassistant.presentation.start_splash;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volgagas.personalassistant.R;

/**
 * Created by CaramelHeaven on 11:55, 01/02/2019.
 */
public class StartSplashFragment extends Fragment {

    public static StartSplashFragment newInstance() {

        Bundle args = new Bundle();

        StartSplashFragment fragment = new StartSplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start_splash, container, false);
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
