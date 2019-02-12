package com.volgagas.personalassistant.utils.callbacks;

/**
 * Created by CaramelHeaven on 14:11, 26/12/2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * status means: plus - 1, minus - 0
 */
public interface OnButtonPlusMinusClickListener {
    void onHandleCount(int position, int status, int count);

    void onHandleEditText(int pos, int count);
}
