package com.volgagas.personalassistant.utils.callbacks;

import android.view.View;

/**
 * Created by CaramelHeaven on 14:04, 06/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface OnResultItemClick {
    void onClick(int position, View view, boolean status);

    void makePhotoClick(int position);
}
