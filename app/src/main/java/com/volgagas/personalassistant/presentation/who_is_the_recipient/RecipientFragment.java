package com.volgagas.personalassistant.presentation.who_is_the_recipient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.User;
import com.volgagas.personalassistant.presentation.base.BaseFragment;
import com.volgagas.personalassistant.presentation.who_is_the_recipient.adapters.RecipientAdapter;
import com.volgagas.personalassistant.presentation.who_is_the_recipient.presenter.RecipientPresenter;
import com.volgagas.personalassistant.presentation.who_is_the_recipient.presenter.RecipientView;
import com.volgagas.personalassistant.utils.channels.pass_data.RequestData;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 16:57, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class RecipientFragment extends BaseFragment implements RecipientView {

    private RecipientAdapter adapter;
    private List<User> filterModels;
    private RequestData data;

    private RecyclerView rvAllWorkers;
    private EditText etSearch;
    private ProgressBar progressBar;
    private Button btnSend;

    @InjectPresenter
    RecipientPresenter presenter;

    public static RecipientFragment newInstance() {

        Bundle args = new Bundle();

        RecipientFragment fragment = new RecipientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvAllWorkers = view.findViewById(R.id.recyclerView);
        etSearch = view.findViewById(R.id.et_search);
        progressBar = view.findViewById(R.id.progress_bar);
        btnSend = view.findViewById(R.id.btn_send);

        rvAllWorkers.setHasFixedSize(true);
        rvAllWorkers.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        filterModels = new ArrayList<>();
        adapter = new RecipientAdapter(new ArrayList<>());

        rvAllWorkers.setAdapter(adapter);

        adapter.setMyOnItemClickListener(position -> {
            Timber.d("all");
            adapter.updateAddedUsers(adapter.getItemByPosition(position));
        });

        btnSend.setOnClickListener(v -> {
            if (adapter.getAddedUsers().size() > 0) {
                presenter.sendToServer(data, adapter.getAddedUsers());
            } else {
                Toast.makeText(getActivity(), "Нет добавленных пользователей", Toast.LENGTH_SHORT).show();
            }
        });

        provideEditText();
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d("START");
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("RESUME");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showUsers(List<User> values) {
        if (values.size() != 0) {
            filterModels.clear();
            filterModels.addAll(values);
            adapter.updateAdapter(values);
        }
    }

    @Override
    public void sendUserData(RequestData data) {
        this.data = new RequestData();

        this.data.setDescription(data.getDescription());
        this.data.setTitle(data.getTitle());
        this.data.setEndDate(data.getEndDate());
        this.data.setCategory(data.getCategory());
        this.data.setImportant(data.isImportant());

        Timber.d("check data: " + data.toString());
    }

    @Override
    public void finish() {
        Toast.makeText(getActivity(), "Заявка создана", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
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
