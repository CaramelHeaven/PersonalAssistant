package com.volgagas.personalassistant.presentation.base;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.microsoft.aad.adal.AuthenticationContext;

import java.io.IOException;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public abstract class BaseActivity extends MvpAppCompatActivity {
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private IntentFilter[] intentFiltersArray;
    private String[][] techListArray;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private StringBuilder secretNumbers = null;
    private CompositeDisposable disposable = new CompositeDisposable();
    private AuthenticationContext authContext;
    public boolean permissionToEnableNfc = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            pendingIntent = PendingIntent
                    .getActivity(this, 0,
                            new Intent(this, this.getClass())
                                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            try {
                filter.addDataType("*/*");
            } catch (IntentFilter.MalformedMimeTypeException e) {
                throw new RuntimeException("Exception", e);
            }
            intentFiltersArray = new IntentFilter[]{filter,};
            techListArray = new String[][]{
                    {MifareClassic.class.getName()}
            };
        } else {
            Toast.makeText(this, "NFC is not supported on your device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null && nfcAdapter.isEnabled() && permissionToEnableNfc) {
            nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListArray);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (nfcAdapter != null && nfcAdapter.isEnabled()) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Single.just(tag)
                .subscribeOn(Schedulers.computation())
                .map(MifareClassic::get)
                .doOnSuccess(mifareClassic -> {
                    boolean isAuthenticated = false;
                    try {
                        mifareClassic.connect();
                        if (mifareClassic.isConnected()) {
                            if (mifareClassic.authenticateSectorWithKeyA(0, MifareClassic.KEY_DEFAULT)) {
                                isAuthenticated = true;
                            }
                            if (isAuthenticated) {
                                int blockIndex = mifareClassic.sectorToBlock(0);
                                byte[] block = mifareClassic.readBlock(blockIndex);
                                secretNumbers = bytesToHexString(block);
                            }
                        }
                        mifareClassic.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (secretNumbers != null) {
                        sendDataToServer(secretNumbers.toString());
                    } else {
                        Toast.makeText(this, "Приложите карту еще раз", Toast.LENGTH_SHORT).show();
                    }
                }, this::unsuccessfulResult);
    }

    private void unsuccessfulResult(Throwable throwable) {
        Timber.d("throwable: " + throwable.getMessage());
        Timber.d("throwable: " + throwable.getCause());
    }

    protected abstract void sendDataToServer(String data);

    private StringBuilder bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder builder = new StringBuilder(new String(hexChars));
        return spesialConvert(new StringBuilder(builder
                .substring(0, 8))).insert(0, "0x20")
                .append("000000");
    }

    private StringBuilder spesialConvert(StringBuilder stringFromBlock) {
        if (stringFromBlock.length() == 8) {
            stringFromBlock = new StringBuilder(stringFromBlock).reverse();
            char[] array = stringFromBlock.toString().toCharArray();
            for (int i = 0; i < array.length - 1; i = i + 2) {
                char tmp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = tmp;
            }
            return stringFromBlock.delete(0, stringFromBlock.length()).append(array);
        }
        return null;
    }
}
