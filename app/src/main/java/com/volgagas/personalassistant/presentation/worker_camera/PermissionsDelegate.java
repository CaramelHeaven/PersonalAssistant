package com.volgagas.personalassistant.presentation.worker_camera;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.volgagas.personalassistant.R;

/**
 * Created by CaramelHeaven on 10:04, 04/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class PermissionsDelegate {

    private static final int REQUEST_CODE = 10;
    private final Activity activity;

    public PermissionsDelegate(Activity activity) {
        this.activity = activity;
    }

    public boolean hasCameraPermission() {
        int permissionCheckResult = ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.CAMERA
        );
        return permissionCheckResult == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions() {
        ActivityCompat.requestPermissions(
                activity,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,},
                REQUEST_CODE
        );
    }

    public boolean resultGranted(int requestCode,
                                 String[] permissions,
                                 int[] grantResults) {

        if (requestCode != REQUEST_CODE) {
            return false;
        }

        if (grantResults.length < 1) {
            return false;
        }
        if (!(permissions[0].equals(Manifest.permission.CAMERA))) {
            return false;
        }

        View noPermissionView = activity.findViewById(R.id.no_permission);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (noPermissionView != null) {
                noPermissionView.setVisibility(View.GONE);
            }
            return true;
        }

        requestPermissions();

        if (noPermissionView != null) {
            noPermissionView.setVisibility(View.VISIBLE);
        }

        return false;
    }
}