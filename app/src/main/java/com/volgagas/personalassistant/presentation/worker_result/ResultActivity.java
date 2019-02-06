package com.volgagas.personalassistant.presentation.worker_result;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CacheUser;
import com.volgagas.personalassistant.models.model.Task;
import com.volgagas.personalassistant.models.model.worker.SubTask;
import com.volgagas.personalassistant.presentation.base.BaseActivity;
import com.volgagas.personalassistant.presentation.start_splash.StartSplashFragment;
import com.volgagas.personalassistant.presentation.worker.WorkerActivity;
import com.volgagas.personalassistant.presentation.worker_camera.CameraActivity;
import com.volgagas.personalassistant.presentation.worker_choose_action.ChooseActionActivity;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultPresenter;
import com.volgagas.personalassistant.presentation.worker_result.presenter.ResultView;
import com.volgagas.personalassistant.presentation.worker_result_dialog.ResultDialogFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.callbacks.OnResultItemClick;
import com.volgagas.personalassistant.utils.manager.TaskContentManager;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import timber.log.Timber;

public class ResultActivity extends BaseActivity implements ResultView {

    private RecyclerView recyclerView;
    ;
    private AlertDialog alCardScan;
    private ProgressDialog progressDialog;
    private Button btnStartCompleted, btnToNomenclatures;

    private ResultAdapter adapter;

    @InjectPresenter
    ResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView = findViewById(R.id.recyclerView);
        btnStartCompleted = findViewById(R.id.btn_start_completed);
        btnToNomenclatures = findViewById(R.id.btn_to_nomenclature);

        setPermissionToEnableNfc(false);

        provideRecyclerAndAdapter(TaskContentManager.getInstance().getSubTasks());

        btnStartCompleted.setOnClickListener(v -> {
            Timber.d("CHECK: " + presenter.getChosenSubTasks().size());
            Timber.d("prenseter: " + presenter.getAllSubTasks().size());
            if (presenter.getChosenSubTasks().size() == 0) {
                Toasty.error(ResultActivity.this, "Задачи не добавлены").show();
            } else if (presenter.getChosenSubTasks().size() == presenter.getAllSubTasks().size()) {

                showAlScanCard(presenter.getChosenSubTasks().size());
            } else if (presenter.getChosenSubTasks().size() != 0) {
                presenter.findNonSelectedSubTasks();
                Timber.d("chosen subtassk: " + presenter.getNonSelectedSubTasks().size());
                ResultDialogFragment fragment = ResultDialogFragment
                        .newInstance(new ArrayList<>(presenter.getNonSelectedSubTasks()));
                fragment.show(getSupportFragmentManager(), null);
            }
        });

        btnToNomenclatures.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ChooseActionActivity.class);
            intent.putExtra("ACTION", Constants.ADD_MORE_NOMENCLATURES);

            startActivity(intent);
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
        if (CacheUser.getUser().getCodekeyList().contains(data.substring(2, data.length()))) {
            presenter.sendData();
        } else {
            Toasty.error(this, "Приложена другая карточка").show();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new ResultAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        adapter.updateAdapter(subTasks);
        runLayoutAnimation();

        adapter.setOnResultItemClick(new OnResultItemClick() {
            @Override
            public void onClick(int position, boolean status) {
                if (status) {
                    presenter.addChosenSubTask(adapter.getItemByPos(position));
                } else {
                    presenter.removeChosenSubTask(adapter.getItemByPos(position));
                }

                adapter.updateStateSubTask(position, status);
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

    private void showAlScanCard(int size) {
        //permission from abstract class to enable NFC
        setPermissionToEnableNfc(true);
        handlerNFC();

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setMessage("Приложите карту исполнителя " + CacheUser.getUser().getName())
                .setCancelable(true)
                .setOnCancelListener(dialogInterface -> {
                    setPermissionToEnableNfc(false);
                    handlerNFC();
                });

        if (size > 1) {
            builder.setTitle("Подтверждение выполнение мероприятий");
        } else if (size == 1) {
            builder.setTitle("Подтверждение выполнение мероприятия");
        }

        alCardScan = builder.create();

        alCardScan.show();
    }

    @Override
    public void showSendStatus() {
        if (alCardScan != null) {
            alCardScan.cancel();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Отправляем задачи");

        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void completed() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        Toasty.success(this, "Задания успешно завершены").show();

        Intent intent = new Intent(ResultActivity.this, WorkerActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void callbackFromResultDialog(boolean bool) {
        presenter.setStoppingTasks(bool);

        showAlScanCard(presenter.getChosenSubTasks().size());
    }

    @Override
    public void timeout() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }

        Toast.makeText(this, "Проблемы с сетью. Попробуйте еще раз", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

}
