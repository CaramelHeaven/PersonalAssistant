package com.volgagas.personalassistant.presentation.messenger;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.Message;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerPresenter;
import com.volgagas.personalassistant.presentation.messenger.presenter.MessengerView;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MessengerActivity extends BaseActivity implements MessengerView {

    @InjectPresenter
    MessengerPresenter presenter;

    private RecyclerView recyclerView;
    private EditText etMessage;
    private Button btnSend;

    private MessangerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        recyclerView = findViewById(R.id.recyclerView);
        etMessage = findViewById(R.id.et_send_message);
        btnSend = findViewById(R.id.btn_send);

        provideRecyclerAndAdapter();

        btnSend.setOnClickListener(v -> {
            Timber.d("data");
            Message message = new Message(CacheUser.getUser().getModifiedNormalName(), etMessage.getText().toString());
            adapter.addMessage(message);
            etMessage.setText("");
            recyclerView.postDelayed(() -> recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1), 100);
        });

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
    }

    @Override
    protected void sendDataToServer(String data) {
        //nothing
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private void provideRecyclerAndAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new MessangerAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                recyclerView.postDelayed(() -> recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount() - 1), 100);
            }
        });
    }
}
