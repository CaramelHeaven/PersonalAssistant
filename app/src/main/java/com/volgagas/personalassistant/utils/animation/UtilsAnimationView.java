package com.volgagas.personalassistant.utils.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.volgagas.personalassistant.utils.Constants;
import com.volgagas.personalassistant.utils.animation.helpers.Collapsible;
import com.volgagas.personalassistant.utils.animation.helpers.Expandable;
import com.volgagas.personalassistant.utils.bus.RxBus;

/**
 * Created by CaramelHeaven on 13:46, 06/02/2019.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UtilsAnimationView implements Expandable, Collapsible {
    private static volatile UtilsAnimationView INSTANCE;
    private static ValueAnimator valueAnimator;

    public static UtilsAnimationView getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsAnimationView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsAnimationView();
                }
            }
        }

        return INSTANCE;
    }

    @Override
    public void collapseFromHalfOfPartToInitialPos(int height, View view, Context context) {
        collapse(view, 600, dpToPx(height, context));
    }

    @Override
    public void expandFromInitialPosToHalfOfPartScreen(int height, View view, Context context) {
        expand(view, 600, dpToPx(height, context));
    }

    public static ValueAnimator getValueAnimator() {
        return valueAnimator;
    }

    /**
     * Expand view animation
     *
     * @param v            - view which we animated
     * @param duration     - duration of animation
     * @param targetHeight - height which view can be animated
     */
    private static void expand(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        v.setVisibility(View.VISIBLE); //non special param
        valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(animation -> {
            v.getLayoutParams().height = (int) animation.getAnimatedValue();
            v.requestLayout();
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                RxBus.getInstance().passDataToCommonChannel(Constants.EXPANDED);
            }
        });

        valueAnimator.start();
    }

    /**
     * Collapse view
     *
     * @param v            - animated view
     * @param duration     - duration of animation
     * @param targetHeight - view which can be animated
     */
    private static void collapse(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(animation -> {
            v.getLayoutParams().height = (int) animation.getAnimatedValue();
            v.requestLayout();
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                RxBus.getInstance().passDataToCommonChannel(Constants.VIEW_IS_COLLAPSED);
            }
        });

        valueAnimator.start();
    }

    /**
     * Method for convert int to dp for screen
     *
     * @param dp      - initial value for [int]
     * @param context - helper context
     */
    private static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;

        return Math.round((float) dp * density);
    }
}
