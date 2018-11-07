package com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter.RecipientPresenter;
import com.volgagas.personalassistant.presentation.projects.query_create.who_is_the_recipient.presenter.RecipientView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 15:25, 02.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientDialogFragment extends MvpAppCompatDialogFragment implements RecipientView {

    private DisplayMetrics displayMetrics;
    private RecipientAdapter adapter;
    private List<User> filterModels;

    private RecyclerView recyclerView;
    private EditText etSearch;

    @InjectPresenter
    RecipientPresenter presenter;

    public static RecipientDialogFragment newInstance() {

        Bundle args = new Bundle();

        RecipientDialogFragment fragment = new RecipientDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_recipient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);
        etSearch = view.findViewById(R.id.et_search);

    //    recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        filterModels = new ArrayList<>();
        adapter = new RecipientAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        provideEditText();

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
        super.onDestroyView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showUsers(List<User> values) {
        if (values.size() != 0) {
            adapter.updateAdapter(values);
            filterModels.clear();
            filterModels.addAll(values);
        }
    }

    private void provideEditText() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                List<User> filterUsers = new ArrayList<>();

                String c = "Тищенко Алексей Иванович";
                String k = "Алексей";
                if (c.toLowerCase().contains(k.toLowerCase())) {
                    Timber.d("contains");
                }

                if (k.toLowerCase().contains(c.toLowerCase())) {
                    Timber.d("k: contains");
                }

                if (!charSequence.toString().isEmpty()) {
                    for (User user : filterModels) {
                        if (user.getName().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filterUsers.add(user);
                        }
                    }
                    adapter.updateAdapter(filterUsers);
                } else {
                    adapter.updateAdapter(filterModels);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //nothing
            }
        });
    }
}
