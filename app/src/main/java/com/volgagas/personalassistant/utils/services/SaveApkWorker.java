package com.volgagas.personalassistant.utils.services;

import android.content.Context;
import android.support.annotation.NonNull;

import com.volgagas.personalassistant.data.cache.CachePot;
import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.bus.RxBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * Created by CaramelHeaven on 10:43, 19/02/2019.
 */
public class SaveApkWorker extends Worker {
    public SaveApkWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        ResponseBody body = CachePot.getInstance().getBodyApk();
        boolean isSuccessfulSave = false;

        if (body != null) {
            File fileApk = new File(getApplicationContext().getFilesDir(), Constants.APK_FILE_NAME);

            // todo change the file location/name according to your needs
            if (fileApk.exists()) {
                fileApk.delete();
            }

            //make progress
            RxBus.getInstance().passDataToCommonChannel(Constants.APK_PROGRESS_50);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();

                outputStream = new FileOutputStream(fileApk);

                boolean isWrite = false;

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break; //another when break the first time - error for read bytes
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;

                    isWrite = true; // write successful
                    Timber.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                RxBus.getInstance().passDataToCommonChannel(Constants.APK_PROGRESS_80);
                outputStream.flush();

                isSuccessfulSave = isWrite;
            } catch (IOException e) {
                e.printStackTrace();
                isSuccessfulSave = false;
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if (isSuccessfulSave) {
            RxBus.getInstance().passDataToCommonChannel(Constants.APK_PROGRESS_100);
            return Result.success();
        } else {
            RxBus.getInstance().passDataToCommonChannel(Constants.APK_PROGRESS_FAILED);
            return Result.failure();
        }
    }
}
