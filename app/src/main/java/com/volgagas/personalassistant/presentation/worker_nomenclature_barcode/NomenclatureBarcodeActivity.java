package com.volgagas.personalassistant.presentation.worker_nomenclature_barcode;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

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
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodePresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature_barcode.presenter.NomenclatureBarcodeView;
import com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.NomenclatureDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import timber.log.Timber;

public class NomenclatureBarcodeActivity extends MvpAppCompatActivity implements NomenclatureBarcodeView {
    private DecoratedBarcodeView barcodeView;
    private RecyclerView recyclerView;
    private Button btnCompleted;

    private BeepManager beepManager;
    private BarcodeAdapter adapter;
    private String lastText; //prevent duplicate scan barcode

    @InjectPresenter
    NomenclatureBarcodePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomenclature_barcode);
        recyclerView = findViewById(R.id.recyclerView);
        barcodeView = findViewById(R.id.barcode_scanner);

        initBarcode();
        initListWithAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
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

    private void initListWithAdapter() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapter = new BarcodeAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
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
            //added image
//            ImageView imageView = findViewById(R.id.barcodePreview);
//            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };

    @Override
    public void updateBarcodeFromNetwork(Barcode barcode) {
        //UPDATED
        adapter.addValue(barcode, adapter.getBarcodeList().size() - 1);
    }
}
