package com.volgagas.personalassistant.presentation.projects.queries;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.UniformRequest;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryPresenter;
import com.volgagas.personalassistant.presentation.projects.queries.presenter.QueryView;
import com.volgagas.personalassistant.presentation.projects.query_create.QueryCreateActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

public class QueryFragment extends BaseFragment implements QueryView<UniformRequest> {

    private FloatingActionButton fabCreate;
    private RecyclerView rvTasksToUser, rvTasksFromUser;
    private ExpandableLinearLayout expandableFromUser, expandableToUser;
    private CardView cvExpandFromUser, cvExpandToUser;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<String> listBaseAdapter;

    private QueryBaseAdapter adapterBase;

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
        recyclerView = view.findViewById(R.id.recyclerView);

        // progressBar = view.findViewById(R.id.progress_bar);
        listBaseAdapter = new ArrayList<>(Arrays.asList("Заявки к вам", "Заявки от вас"));
        adapterBase = new QueryBaseAdapter(new ArrayList<>());

        adapterBase.setMyOnItemClickListener(position -> {
            Timber.d("CLICK " + position);
            Timber.d("CLICK " + position);
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterBase);

//        expandableFromUser.setInRecyclerView(true);
        // expandableToUser.setInRecyclerView(true);

      /*  cvExpandToUser.setOnClickListener(v -> {
            Timber.d("CV EXPAND USER");
            // expandableToUser.moveChild(0);
            expandableToUser.toggle();
        });

        cvExpandFromUser.setOnClickListener(v -> {
            Timber.d("CV EXPAND USER");
            // expandableFromUser.setExpanded(true);
            // expandableFromUser.toggle();
        });*/

        //provideListeners();
        // fillDataToAdapter();
    }

    private void provideListeners() {
        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QueryCreateActivity.class));
            }
        });

        /*nsvContainer.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (nestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            Timber.d("SCROLL");
            if (scrollY > oldScrollY) {
                Timber.d("fab HIDE");
                fabCreate.hide();
            } else {
                Timber.d("fab SHOW");
                fabCreate.show();
            }
        });*/
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fabCreate = null;
    }

    @Override
    public void showProgress() {
//        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //  progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems(List<UniformRequest> items) {
        if (items.size() != 0) {
            adapterBase.updateAdapter(listBaseAdapter, items);
        }
    }
}
