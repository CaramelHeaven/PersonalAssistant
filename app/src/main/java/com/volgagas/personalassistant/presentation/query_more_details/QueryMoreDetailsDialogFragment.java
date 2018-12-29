package com.volgagas.personalassistant.presentation.query_more_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.queries.QueryBase;
import com.volgagas.personalassistant.models.model.queries.QueryToUser;
import com.volgagas.personalassistant.models.model.queries.UniformRequest;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 13:56, 29/12/2018.
 */
public class QueryMoreDetailsDialogFragment extends MvpAppCompatDialogFragment {

    private TextView tvTitle;

    private DisplayMetrics displayMetrics;

    public static QueryMoreDetailsDialogFragment newInstance(QueryBase model) {

        Bundle args = new Bundle();
        args.putParcelable("MODEL", model);

        QueryMoreDetailsDialogFragment fragment = new QueryMoreDetailsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_query_more_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTitle = view.findViewById(R.id.tv_title);

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        QueryBase model = getArguments().getParcelable("MODEL");

        if (model instanceof UniformRequest) {
            Timber.d("ir: " + model.toString());
        } else if (model instanceof QueryToUser) {
            Timber.d("qtu: " + model.toString());
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 360);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
