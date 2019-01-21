package com.volgagas.personalassistant.presentation.worker_camera;

import android.content.Intent;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.utils.UtilsDateTimeProvider;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.error.CameraErrorListener;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.parameter.ScaleType;
import io.fotoapparat.preview.Frame;
import io.fotoapparat.preview.FrameProcessor;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;
import timber.log.Timber;

import static io.fotoapparat.result.transformer.ResolutionTransformersKt.scaled;
import static io.fotoapparat.selector.AspectRatioSelectorsKt.standardRatio;
import static io.fotoapparat.selector.FlashSelectorsKt.autoFlash;
import static io.fotoapparat.selector.FlashSelectorsKt.autoRedEye;
import static io.fotoapparat.selector.FlashSelectorsKt.off;
import static io.fotoapparat.selector.FlashSelectorsKt.torch;
import static io.fotoapparat.selector.FocusModeSelectorsKt.autoFocus;
import static io.fotoapparat.selector.FocusModeSelectorsKt.continuousFocusPicture;
import static io.fotoapparat.selector.FocusModeSelectorsKt.fixed;
import static io.fotoapparat.selector.LensPositionSelectorsKt.back;
import static io.fotoapparat.selector.LensPositionSelectorsKt.front;
import static io.fotoapparat.selector.PreviewFpsRangeSelectorsKt.highestFps;
import static io.fotoapparat.selector.ResolutionSelectorsKt.highestResolution;
import static io.fotoapparat.selector.SelectorsKt.firstAvailable;
import static io.fotoapparat.selector.SensorSensitivitySelectorsKt.highestSensorSensitivity;

public class CameraActivity extends AppCompatActivity {

    private Fotoapparat fotoapparat;

    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);

    private boolean hasCameraPermission;
    private boolean activeCameraBack = true;
    private PhotoResult photoResult;
    private String filePath;
    private int positionData;

    private CameraView cameraView;
    private Button btnSave, btnRemove;
    private FocusView focusView;
    private View capture;
    private AppCompatSeekBar seekBar;
    private ImageView imageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraView = findViewById(R.id.cameraView);
        focusView = findViewById(R.id.focusView);
        capture = findViewById(R.id.capture);
        btnSave = findViewById(R.id.btn_save);
        btnRemove = findViewById(R.id.btn_remove);
        seekBar = findViewById(R.id.zoomSeekBar);
        imageResult = findViewById(R.id.result);

        hasCameraPermission = permissionsDelegate.hasCameraPermission();
        positionData = getIntent().getIntExtra("POSITION_DATA", 0);

        if (hasCameraPermission) {
            cameraView.setVisibility(View.VISIBLE);
        } else {
            permissionsDelegate.requestCameraPermission();
        }

        fotoapparat = createFotoapparat();

        zoomSeekBar();

        fotoapparat.switchTo(activeCameraBack ? back() : front(), cameraConfiguration);

        capture.setOnClickListener(view -> {
            photoResult = fotoapparat.takePicture();

            MediaActionSound sound = new MediaActionSound();
            sound.play(MediaActionSound.SHUTTER_CLICK);

            photoResult.toBitmap()
                    .whenDone(bitmapPhoto -> {
                        if (bitmapPhoto == null) {
                            return;
                        }

//                        if (hasCameraPermission) {
//                            fotoapparat.stop();
//                        }

                        imageResult.setImageBitmap(bitmapPhoto.bitmap);
                        imageResult.setRotation(-bitmapPhoto.rotationDegrees);

                        imageResult.setVisibility(View.VISIBLE);
                    });
        });

        btnRemove.setOnClickListener(v -> {
            if (imageResult.getVisibility() == View.VISIBLE) {
                Timber.d("remove");
                imageResult.setVisibility(View.GONE);
            }

            if (hasCameraPermission) {
                fotoapparat.start();
            }
        });

        btnSave.setOnClickListener(v -> {
            if (imageResult.getVisibility() == View.VISIBLE) {
                File file = new File(getCacheDir(), UtilsDateTimeProvider.folderNameTimeFormat());

                photoResult.saveToFile(file);
                filePath = file.getPath();
                Timber.d("check file path: " + filePath);

                Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (hasCameraPermission) {
            fotoapparat.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (hasCameraPermission) {
            fotoapparat.stop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            hasCameraPermission = true;
            fotoapparat.start();

            cameraView.setVisibility(View.VISIBLE);
        }
    }

    private Fotoapparat createFotoapparat() {
        return Fotoapparat
                .with(this)
                .into(cameraView)
                .focusView(focusView)
                .previewScaleType(ScaleType.CenterCrop)
                .lensPosition(back())
                .frameProcessor(new SampleFrameProcessor())
                .cameraErrorCallback(new CameraErrorListener() {
                    @Override
                    public void onError(@NotNull CameraException e) {
                        Toast.makeText(CameraActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .build();
    }

    private CameraConfiguration cameraConfiguration = CameraConfiguration
            .builder()
            .photoResolution(standardRatio(
                    highestResolution()
            ))
            .focusMode(firstAvailable(
                    continuousFocusPicture(),
                    autoFocus(),
                    fixed()
            ))
            .flash(firstAvailable(
                    autoRedEye(),
                    autoFlash(),
                    torch(),
                    off()
            ))
            .previewFpsRange(highestFps())
            .sensorSensitivity(highestSensorSensitivity())
            .frameProcessor(new SampleFrameProcessor())
            .build();

    private class SampleFrameProcessor implements FrameProcessor {
        @Override
        public void process(@NotNull Frame frame) {
            // Perform frame processing, if needed
        }
    }

    private void zoomSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                fotoapparat.setZoom(progress / (float) seekBar.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();

        intent.putExtra("FILE", filePath);
        intent.putExtra("POSITION", positionData);

        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }
}
