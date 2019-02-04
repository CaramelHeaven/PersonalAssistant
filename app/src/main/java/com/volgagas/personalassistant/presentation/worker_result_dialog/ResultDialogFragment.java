package com.volgagas.personalassistant.presentation.worker_result_dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by CaramelHeaven on 12:50, 31/01/2019.
 */
public class ResultDialogFragment extends MvpAppCompatDialogFragment {

    private ResultDFAdapter adapter;
    private DisplayMetrics displayMetrics;

    private RecyclerView recyclerView;
    private Button btnCompleted, btnCancel;

    public static ResultDialogFragment newInstance(ArrayList<SubTask> subTasks) {
        Bundle args = new Bundle();
        args.putParcelableArrayList("ARRAY", subTasks);

        ResultDialogFragment fragment = new ResultDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnCompleted = view.findViewById(R.id.btn_completed);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        Set<SubTask> removeDuplicated = new LinkedHashSet<>(getArguments().getParcelableArrayList("ARRAY"));

        adapter = new ResultDFAdapter(new ArrayList<>(removeDuplicated));
        recyclerView.setAdapter(adapter);

        btnCompleted.setOnClickListener(v -> {
            RxBus.getInstance().passResultCallback(true);
            dismiss();
        });

        btnCancel.setOnClickListener(v -> {
            RxBus.getInstance().passResultCallback(false);
            dismiss();
        });

        displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        window.setLayout(displayMetrics.widthPixels - 50, displayMetrics.heightPixels / 2 + 280);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroyView() {
        recyclerView = null;
        btnCancel = null;
        btnCompleted = null;
        super.onDestroyView();
    }
}
