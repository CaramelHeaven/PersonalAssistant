package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode;

import android.os.Bundle;
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
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodeView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode_list.BarcodeListFragment;
import com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.NomenclatureDialogFragment;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.animation.UtilsAnimationView;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

public class NomenclatureBarcodeActivity extends MvpAppCompatActivity implements NomenclatureBarcodeView, Barcodable {

    private DecoratedBarcodeView barcodeView;
    private Button btnCompleted, btnBack;
    private ImageButton ibShowItems;
    private FrameLayout flContainerItems;
    private DisplayMetrics displayMetrics;


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

        initBarcode();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, BarcodeListFragment.newInstance())
                .commit();

        ibShowItems.setOnClickListener(v -> {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
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
                        .collapseFromHalfOfPartToInitialPos(50, flContainerItems, getApplicationContext());
            }
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;
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
            Timber.d("KEK: " + result.getBarcodeFormat());
            barcodeView.setStatusText("Отсканирован товар с номером " + result.getText());

            beepManager.playBeepSoundAndVibrate();

            NomenclatureDialogFragment fragment = NomenclatureDialogFragment
                    .newInstance(lastText);
            fragment.show(getSupportFragmentManager(), null);

            CachePot.getInstance().putCacheBitmap(lastText, result.getBitmap());

            barcodeView.pause();
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    public void passBarcode(Barcode barcode) {
        resumeBarcode();
        Timber.d("barcode pass: " + barcode.toString());

    }

    @Override
    public void resumeBarcode() {
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
        barcodeView.resume();
    }
}
