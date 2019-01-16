package com.volgagas.personalassistant.presentation.worker_result;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.worker_camera.CameraActivity;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultPresenter;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultView;
import com.volgagas.personalassistant.utils.callbacks.OnResultItemClick;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class ResultActivity extends BaseActivity implements ResultView {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;
    private Button btnStartCompleted;

    private ResultAdapter adapter;

    @InjectPresenter
    ResultPresenter presenter;

    @ProvidePresenter
    ResultPresenter provideResultPresenter() {
        return new ResultPresenter(((Task) getIntent().getParcelableExtra("TASK")).getSubTasks());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.recyclerView);
        btnStartCompleted = findViewById(R.id.btn_start_completed);

        setPermissionToEnableNfc(false);

        provideRecyclerAndAdapter(presenter.getAllSubTasks());

        btnStartCompleted.setOnClickListener(v -> {
            if (presenter.getChosenSubTasks().size() != 0) {
                showAlertDialog();
            } else {
                Toasty.error(ResultActivity.this, "Задачи не добавлены").show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void sendDataToServer(String data) {
        Timber.d("get data from NFC and send it");
        presenter.sendData();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String filePath = data.getStringExtra("FILE");
            int position = data.getIntExtra("POSITION", 0);

            adapter.updatePicture(position, filePath);
        }
    }

    private void provideRecyclerAndAdapter(List<SubTask> subTasks) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ResultAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.updateAdapter(subTasks);
        runLayoutAnimation();

        adapter.setOnResultItemClick(new OnResultItemClick() {
            @Override
            public void onClick(int position, View view, boolean status) {
                if (status) {
                    presenter.addChosenSubTask(adapter.getItemByPos(position));
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                } else {
                    presenter.removeChosenSubTask(adapter.getItemByPos(position));
                    view.setBackgroundColor(getResources().getColor(R.color.colorBackgroundStatusNeutral));
                }
            }

            @Override
            public void makePhotoClick(int position) {
                Timber.d("click make photo");
                provideMakePhotoClick(position);
            }

            @Override
            public void makeClearPicture(int position) {
                adapter.clearPicture(position);
            }
        });
    }

    private void runLayoutAnimation() {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void provideMakePhotoClick(int position) {
        Intent intent = new Intent(ResultActivity.this, CameraActivity.class);

        intent.putExtra("LIMIT", 1);
        intent.putExtra("POSITION_DATA", position);

        startActivityForResult(intent, 1);
    }

    public void showAlertDialog() {
        //permission from abstract class to enable NFC
        setPermissionToEnableNfc(true);
        handlerNFC();

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setTitle("Сканирование")
                .setMessage("Приложите карту для подтверждения задач")
                .setCancelable(true)
                .setOnCancelListener(dialogInterface -> {
                    setPermissionToEnableNfc(false);
                    handlerNFC();
                });
        alertDialog = builder.create();

        alertDialog.show();
    }

    @Override
    public void showSendStatus() {
        alertDialog.hide();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Отправляем задачи");

        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void completed() {
        progressDialog.hide();
        Toasty.success(this, "Задания успешно завершены").show();
    }
}
