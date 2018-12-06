package com.volgagas.personalassistant.presentation.worker_camera;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.volgagas.personalassistant.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
    private boolean isVisible = false;
    private PhotoResult photoResult;
    private ArrayList<String> filesEncoded = new ArrayList<>();
    private int limitPictures, positionData;

    private CameraView cameraView;
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
        seekBar = findViewById(R.id.zoomSeekBar);
        imageResult = findViewById(R.id.result);


        hasCameraPermission = permissionsDelegate.hasCameraPermission();
        limitPictures = getIntent().getIntExtra("LIMIT", 2);
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
            Timber.d("CLICK CLICK CLICK: " + limitPictures);
            if (limitPictures != 0) {
                isVisible = true;
                imageResult.animate().translationX(0).start();
                imageResult.setVisibility(View.VISIBLE);
                photoResult = fotoapparat.takePicture();

                photoResult.toBitmap(scaled(0.25f))
                        .whenDone(bitmapPhoto -> {
                            if (bitmapPhoto == null) {
                                return;
                            }
                            imageResult.setImageBitmap(bitmapPhoto.bitmap);

                            imageResult.setVisibility(View.VISIBLE);
                            imageResult.setRotation(-bitmapPhoto.rotationDegrees);
                        });
            } else {
                Toast.makeText(this, "Больше фотографий сделать нельзя", Toast.LENGTH_SHORT).show();
            }
        });

        provideFrameOnTouchEvent();
    }

    private float currentCloseLocation = 0f, saveStartedLocation = 0f;

    @SuppressLint("ClickableViewAccessibility")
    private void provideFrameOnTouchEvent() {
        imageResult.setOnTouchListener((view, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Timber.d("pressed: " + event.getRawY());
                    saveStartedLocation = event.getRawX();
                    Timber.d("Install position: " + saveStartedLocation);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getAction() != MotionEvent.TOOL_TYPE_FINGER) {
                        currentCloseLocation = saveStartedLocation - event.getRawX();

                        currentCloseLocation *= -1;
                        Timber.d("currentColose: " + currentCloseLocation);
                        Timber.d("eventGetRawX: " + event.getRawX());
                        Timber.d("saveStartedPos: " + saveStartedLocation);
                        //Callback
                        Timber.d("callback 0");
                        imageResult.animate()
                                //.translationXBy(currentCloseLocation)
                                //.translationX(currentCloseLocation)
                                .x(currentCloseLocation)
                                //.xBy(currentCloseLocation)
                                .setDuration(0)
                                .start();

                    }
                    break;
//                case MotionEvent.TOOL_TYPE_FINGER:
//                    if (Math.abs(currentCloseLocation) > 300f) {
//
//                        Timber.d("callback 1");
//                    } else {
//                        Timber.d("callback 2");
//                    }
//                    break;
                default:
                    return false;
            }
            return true;

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
}
