package com.volgagas.personalassistant.presentation.worker_nomenclature_dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.models.model.worker.Barcode;
import com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.presenter.NomenclatureDFPresenter;
import com.volgagas.personalassistant.presentation.worker_nomenclature_dialog.presenter.NomenclatureDFView;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 11:04, 31/01/2019.
 */
public class NomenclatureDialogFragment extends MvpAppCompatDialogFragment implements NomenclatureDFView<Barcode> {

    private DisplayMetrics displayMetrics;

    private Button btnCompleted;

    @InjectPresenter
    NomenclatureDFPresenter presenter;

    @ProvidePresenter
    NomenclatureDFPresenter provideNomenclatureDFPresenter() {
        return new NomenclatureDFPresenter(getArguments().getString("BARCODE"));
    }

    public static NomenclatureDialogFragment newInstance(String barcodeResult) {
        Bundle args = new Bundle();
        args.putString("BARCODE", barcodeResult);

        NomenclatureDialogFragment fragment = new NomenclatureDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_nomenclature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnCompleted = view.findViewById(R.id.btn_completed);

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
    public void showBarcodeResult(Barcode barcode) {
        Timber.d("SHOW RESULT");
    }
}
