package com.volgagas.personalassistant.utils.callbacks;

/**
 * Created by CaramelHeaven on 14:11, 26/12/2018.
 * status means: plus - 1, minus - 0
 */
public interface OnButtonPlusMinusClickListener {
    void onHandleCount(int position, int status, int count);
}
