package com.volgagas.personalassistant.utils.callbacks;

/**
 * Created by CaramelHeaven on 14:04, 06/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public interface OnResultItemClick {
    void onClick(int position, boolean status);

    void makePhotoClick(int position);

    void makeClearPicture(int position);
}
