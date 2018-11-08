package com.volgagas.personalassistant.utils.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 17:14, 08.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ControlledSwipeViewPager extends ViewPager {

    private Boolean enable = false;

    public ControlledSwipeViewPager(@NonNull Context context) {
        super(context);
    }

    public ControlledSwipeViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return enable && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return enable && super.onInterceptTouchEvent(event);
    }

    public void enableScroll(Boolean disable) {
        this.enable = disable;
    }
}
