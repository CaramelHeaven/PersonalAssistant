package com.volgagas.personalassistant.utils.views.sticky_header.calculation;

import android.graphics.Rect;
import android.view.View;

import static android.view.ViewGroup.LayoutParams;
import static android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by CaramelHeaven on 11:41, 23/01/2019.
 */
public class DimensionCalculator {

    /**
     * Populates {@link Rect} with margins for any view.
     *
     *
     * @param margins rect to populate
     * @param view for which to get margins
     */
    public void initMargins(Rect margins, View view) {
        LayoutParams layoutParams = view.getLayoutParams();

        if (layoutParams instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) layoutParams;
            initMarginRect(margins, marginLayoutParams);
        } else {
            margins.set(0, 0, 0, 0);
        }
    }

    /**
     * Converts {@link MarginLayoutParams} into a representative {@link Rect}.
     *
     * @param marginRect Rect to be initialized with margins coordinates, where
     * {@link MarginLayoutParams#leftMargin} is equivalent to {@link Rect#left}, etc.
     * @param marginLayoutParams margins to populate the Rect with
     */
    private void initMarginRect(Rect marginRect, MarginLayoutParams marginLayoutParams) {
        marginRect.set(
                marginLayoutParams.leftMargin,
                marginLayoutParams.topMargin,
                marginLayoutParams.rightMargin,
                marginLayoutParams.bottomMargin
        );
    }



}