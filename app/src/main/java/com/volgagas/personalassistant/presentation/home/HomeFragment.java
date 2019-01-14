package com.volgagas.personalassistant.presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.home.presenter.HomePresenter;
import com.volgagas.personalassistant.presentation.home.presenter.HomeView;
import com.volgagas.personalassistant.presentation.kiosk.KioskActivity;
import com.volgagas.personalassistant.presentation.order_purchase.OrderPurchaseActivity;
import com.volgagas.personalassistant.presentation.query_create.QueryCreateActivity;
import com.volgagas.personalassistant.presentation.worker.WorkerActivity;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends BaseFragment implements HomeView {

    private CardView cvCreateTask, cvKiosk, cvWorker, cvBuyOrder;

    @ProvidePresenter
    HomePresenter provideHomePresenter() {
        return new HomePresenter();
    }

    @InjectPresenter
    HomePresenter presenter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        cvCreateTask = view.findViewById(R.id.cv_container1);
        cvKiosk = view.findViewById(R.id.cv_container);
        cvWorker = view.findViewById(R.id.cv_container2);
        cvBuyOrder = view.findViewById(R.id.cv_container3);

        provideClickListeners();

//        recyclerView = view.findViewById(R.id.recyclerView);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//
//        adapter = new HomeAdapter(new ArrayList<>(Arrays.asList("Создать заявку", "Пожелание", "Kfkfkf", "lflflf")));
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideClickListeners() {
        cvCreateTask.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), QueryCreateActivity.class));
        });

        cvKiosk.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), KioskActivity.class));
        });

        cvWorker.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), WorkerActivity.class));
        });

        cvBuyOrder.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), OrderPurchaseActivity.class));
        });
    }
}
