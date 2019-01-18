package com.volgagas.personalassistant.models.model.common;

import android.graphics.drawable.Drawable;

/**
 * Created by CaramelHeaven on 09:46, 18/01/2019.
 */
public class HomeModel {
    private String title;
    private Drawable drawable;

    public HomeModel(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
