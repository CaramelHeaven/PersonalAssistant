package com.volgagas.personalassistant.presentation.worker_camera;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.volgagas.personalassistant.R;
import com.volgagas.personalassistant.utils.services.SystemMyTimeProvider;

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
    private boolean isVisible = false;
    private PhotoResult photoResult;
    private String filePath;
    private int limitPictures, positionData;

    private CameraView cameraView;
    private FocusView focusView;
    private View capture;
    private AppCompatSeekBar seekBar;
    private ImageView imageResult;
    private GestureDetector gestureDetector;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

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
        gestureDetector = new GestureDetector(CameraActivity.this, new GestureListener());
        limitPictures = getIntent().getIntExtra("LIMIT", 0);
        positionData = getIntent().getIntExtra("POSITION_DATA", 0);

        if (hasCameraPermission) {
            cameraView.setVisibility(View.VISIBLE);
        } else {
            permissionsDelegate.requestCameraPermission();
        }

        fotoapparat = createFotoapparat();

        zoomSeekBar();
        imageResultTouch();

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
                            imageResult.setRotation(-bitmapPhoto.rotationDegrees);
                        });
            } else {
                Toast.makeText(this, "Больше фотографий сделать нельзя", Toast.LENGTH_SHORT).show();
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

    @SuppressLint("ClickableViewAccessibility")
    private void imageResultTouch() {
        imageResult.setOnTouchListener((view, motionEvent) -> {
            gestureDetector.onTouchEvent(motionEvent);
            return true;
        });
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (isVisible) {
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(CameraActivity.this, "Удалено", Toast.LENGTH_SHORT).show();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
                    alphaAnimation.setDuration(250);
                    alphaAnimation.setFillAfter(true);
                    imageResult.startAnimation(alphaAnimation);
                    imageResult.animate().translationX(1200)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageResult.setVisibility(View.GONE);
                                }
                            });
                    isVisible = false;
                    return false;
                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    limitPictures -= 1;
                    Toast.makeText(CameraActivity.this, "Сохранено", Toast.LENGTH_SHORT).show();
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
                    alphaAnimation.setDuration(250);
                    alphaAnimation.setFillAfter(true);
                    imageResult.startAnimation(alphaAnimation);
                    imageResult.animate().translationX(-1200)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    imageResult.setVisibility(View.GONE);
                                }
                            });
                    File file = new File(getCacheDir(), SystemMyTimeProvider.folderNameTimeFormat());

                    photoResult.saveToFile(file);
                    filePath = file.getPath();
                }
            }
            return false;
        }
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
