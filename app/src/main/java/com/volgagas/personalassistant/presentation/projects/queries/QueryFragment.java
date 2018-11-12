package com.volgagas.personalassistant.presentation.projects.queries;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryPresenter;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryView;
import com.volgagas.personalassistant.presentation.projects.query_create.QueryCreateActivity;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class QueryFragment extends BaseFragment implements QueryView<UniformRequest> {

    private FloatingActionButton fabCreate;
    private RecyclerView rvTasksToUser, rvTasksFromUser;
    private NestedScrollView nsvContainer;

    private QueryToUserAdapter adapterToUser;
    private QueryFromUserAdapter adapterFromUser;

    @InjectPresenter
    QueryPresenter presenter;

    public static QueryFragment newInstance() {

        Bundle args = new Bundle();

        QueryFragment fragment = new QueryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_query, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fabCreate = view.findViewById(R.id.fab_create);
        rvTasksToUser = view.findViewById(R.id.rv_tasks_to_user);
        rvTasksFromUser = view.findViewById(R.id.rv_tasks_from_user);
        nsvContainer = view.findViewById(R.id.nsv_container);

        provideListeners();
        fillDataToAdapter();
    }

    private void fillDataToAdapter() {
        rvTasksToUser.setHasFixedSize(true);
        rvTasksFromUser.setHasFixedSize(true);

        rvTasksToUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvTasksFromUser.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        rvTasksToUser.setNestedScrollingEnabled(false);
        rvTasksFromUser.setNestedScrollingEnabled(false);

        adapterToUser = new QueryToUserAdapter(new ArrayList());
        adapterFromUser = new QueryFromUserAdapter(new ArrayList<>());

        rvTasksToUser.setAdapter(adapterToUser);
        rvTasksFromUser.setAdapter(adapterFromUser);
    }

    private void provideListeners() {
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QueryCreateActivity.class));
            }
        });

        nsvContainer.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Timber.d("SCROLL");
            if (scrollY > oldScrollY) {
                Timber.d("fab HIDE");
                fabCreate.hide();
            } else {
                Timber.d("fab SHOW");
                fabCreate.show();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fabCreate = null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showItems(List<UniformRequest> items) {
        if (items.size() != 0) {
            adapterToUser.updateAdapter(items);
        }
    }
}
