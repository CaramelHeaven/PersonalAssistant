package com.volgagas.personalassistant.presentation.messenger;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.Message;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerPresenter;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import timber.log.Timber;

public class MessengerActivity extends BaseActivity implements MessengerView {

    private RecyclerView recyclerView;
    private EditText etMessage;
    private ImageButton btnSend;
    private ProgressBar progressBar;

    private MessengerAdapter adapter;

    @InjectPresenter
    MessengerPresenter presenter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        recyclerView = findViewById(R.id.recyclerView);
        etMessage = findViewById(R.id.et_send_message);
        btnSend = findViewById(R.id.btn_send);
        progressBar = findViewById(R.id.progressBar);

        setPermissionToEnableNfc(false);

        provideRecyclerAndAdapter();

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));
        messages.add(new Message("Adrrew", "t"));

        adapter.updateAdapter(messages);
        recyclerView.scrollToPosition(adapter.getMessageList().size() - 1);

        postMessage();
    }

    @Override
    protected void sendDataToServer(String data) {
        //nothing
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MessengerAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                recyclerView.postDelayed(() ->
                        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1), 100);
            }
        });
    }


    /* post user message
     * */
    private void postMessage() {
        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().replaceAll("^\\s+", "");
            Message message = new Message(CacheUser.getUser().getModifiedNormalName(), msg);
            message.setCompletedSendToServer(false);

           // formatDisplay(message);

            presenter.postMessage(message)
                    .subscribe(result -> {
                       // observeResults(result, adapter.getItemCount());
                        Timber.d("result in messenger: " + result.toString());
                    });
        });
    }
//
//    private void formatDisplay(Message message) {
//        adapter.addMessage(message);
//        etMessage.setText("");
//
//        recyclerView.postDelayed(() -> recyclerView
//                .smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1), 100);
//    }
//
//    private void observeResults(UUID id, int lastPosition) {
//        WorkManager.getInstance().getWorkInfoByIdLiveData(id).observe(MessengerActivity.this, workInfo -> {
//            if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
//                adapter.updateMessage(lastPosition);
//            }
//        });
//    }
}
