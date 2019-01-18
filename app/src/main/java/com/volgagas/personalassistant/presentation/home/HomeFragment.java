package com.volgagas.personalassistant.presentation.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.common.HomeModel;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.home.presenter.HomePresenter;
import com.volgagas.personalassistant.presentation.home.presenter.HomeView;
import com.volgagas.personalassistant.presentation.kiosk.KioskActivity;
import com.volgagas.personalassistant.presentation.order_purchase.OrderPurchaseActivity;
import com.volgagas.personalassistant.presentation.query_create.QueryCreateActivity;
import com.volgagas.personalassistant.presentation.worker.WorkerActivity;
import com.volgagas.personalassistant.utils.callbacks.myOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class HomeFragment extends BaseFragment implements HomeView {

    private RecyclerView recyclerView;

    private HomeAdapter adapter;
    private List<HomeModel> homeModelList;

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
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        homeModelList = new ArrayList<>();
        provideBaseNavigation();

        adapter = new HomeAdapter(homeModelList);
        recyclerView.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), KioskActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), QueryCreateActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), WorkerActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), OrderPurchaseActivity.class));
                    break;
            }
        });
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

    private void provideBaseNavigation() {
        HomeModel homeModel = new HomeModel("Создать работу", getContext().getDrawable(R.drawable.ic_writing));
        HomeModel homeModel1 = new HomeModel("Создать заявку", getContext().getDrawable(R.drawable.ic_pen));
        HomeModel homeModel2 = new HomeModel("Список работ", getContext().getDrawable(R.drawable.ic_note));
        HomeModel homeModel3 = new HomeModel("Заказ", getContext().getDrawable(R.drawable.ic_box));

        homeModelList.add(homeModel);
        homeModelList.add(homeModel1);
        homeModelList.add(homeModel2);
        homeModelList.add(homeModel3);
    }
}
