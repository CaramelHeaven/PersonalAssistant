package com.volgagas.personalassistant.utils.views.sticky_header.util;

/**
 * Created by CaramelHeaven on 11:44, 23/01/2019.
 */

import android.support.v7.widget.RecyclerView;

/**
 * Interface for getting the orientation of a RecyclerView from its LayoutManager
 */
public interface OrientationProvider {

    public int getOrientation(RecyclerView recyclerView);

    public boolean isReverseLayout(RecyclerView recyclerView);
}
