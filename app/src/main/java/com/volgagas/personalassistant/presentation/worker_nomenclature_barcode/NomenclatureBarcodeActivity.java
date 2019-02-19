package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.presentation.worker_camera.PermissionsDelegate;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodeView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.BarcodeListFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.animation.UtilsAnimationView;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by CaramelHeaven on 15:40, 06/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class NomenclatureBarcodeActivity extends MvpAppCompatActivity implements NomenclatureBarcodeView {

    private DecoratedBarcodeView barcodeView;
    private Button btnCompleted, btnBack;
    private ImageButton ibShowItems;
    private FrameLayout flContainerItems;
    private ProgressDialog progressDialog;

    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private boolean hasCameraPermission;

    private BeepManager beepManager;
    private String lastText; //prevent duplicate scan barcode
    private boolean isShowItems = false;
    private int height;

    @InjectPresenter
    NomenclatureBarcodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomenclature_barcode);
        ibShowItems = findViewById(R.id.ib_show_items);
        barcodeView = findViewById(R.id.barcode_scanner);
        btnBack = findViewById(R.id.btn_back);
        btnCompleted = findViewById(R.id.btn_completed);
        flContainerItems = findViewById(R.id.frameLayout);

        barcodeView.getStatusView().setGravity(Gravity.CENTER);

        hasCameraPermission = permissionsDelegate.hasCameraPermission();

        if (hasCameraPermission) {
            initBarcode();
        } else {
            permissionsDelegate.requestPermissions();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, BarcodeListFragment.newInstance())
                .commit();

        ibShowItems.setOnClickListener(v -> {
            isShowItems = !isShowItems;

            if (isShowItems) {
                ibShowItems.animate()
                        .rotation(-180)
                        .setDuration(300)
                        .start();

                if (UtilsAnimationView.getValueAnimator() != null && UtilsAnimationView.getValueAnimator()
                        .isStarted()) {
                    UtilsAnimationView.getValueAnimator().cancel();
                }

                barcodeView.pauseAndWait();
                UtilsAnimationView.getInstance()
                        .expandFromInitialPosToHalfOfPartScreen(height, flContainerItems, getApplicationContext());
            } else {
                ibShowItems.animate()
                        .rotation(0)
                        .setDuration(300)
                        .start();

                RxBus.getInstance().passDataToCommonChannel(Constants.COLLAPSED);

                if (UtilsAnimationView.getValueAnimator() != null && UtilsAnimationView.getValueAnimator()
                        .isStarted()) {
                    UtilsAnimationView.getValueAnimator().cancel();
                }

                UtilsAnimationView.getInstance()
                        .collapseFromHalfOfPartToInitialPos(42, flContainerItems, getApplicationContext());
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;

        btnCompleted.setOnClickListener(v ->
                RxBus.getInstance().passDataToCommonChannel(Constants.REQUEST_DATA_FROM_BARCODE_LIST));

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeBarcode();
    }

    @Override
    protected void onPause() {
        barcodeView.pause();
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            hasCameraPermission = true;

            initBarcode();
        }
    }


    private void initBarcode() {
        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39,
                BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.UPC_E);

        barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
        barcodeView.initializeFromIntent(getIntent());
        barcodeView.decodeContinuous(barcodeCallback);

        beepManager = new BeepManager(this);
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() == null || result.getText().equals(lastText)) {
                // Prevent duplicate scans
                return;
            }

            lastText = result.getText();

            barcodeView.setStatusText("Отсканирован товар с номером " + result.getText());

            beepManager.playBeepSoundAndVibrate();

            //TODO make get data from network
            presenter.loadBarcodeData(lastText);
//            Barcode barcode = new Barcode();
//            barcode.setBarcode(lastText);
//            barcode.setCount(5);
//            barcode.setBarcodeName("Трилон Б");

            Toast.makeText(NomenclatureBarcodeActivity.this, "Отсканировано", Toast.LENGTH_SHORT).show();


            new Handler().postDelayed(() -> {
                lastText = "";
            }, 1300);
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    public void resumeBarcode() {
        lastText = "";

        barcodeView.resume();
    }

    @Override
    public void getDataAndComlpeted() {
        if (CachePot.getInstance().getCacheBarcodeList().size() > 0) {
            RxBus.getInstance().passDataToCommonChannel(Constants.CLOSED_NOMENCLATURE_BARCODE_ACTIVITY);
            finish();
        } else {
            Toast.makeText(this, "Вы ничего не добавили", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void successfulGetBarcodeFromServer(Barcode barcode) {
        CachePot.getInstance().putBarcodeCache(barcode);
        RxBus.getInstance().passDataToCommonChannel(Constants.UPDATE_DATA_BARCODE);
    }

    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }

        progressDialog.setMessage("Получаем номенклатуру");
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.cancel();
    }
}
