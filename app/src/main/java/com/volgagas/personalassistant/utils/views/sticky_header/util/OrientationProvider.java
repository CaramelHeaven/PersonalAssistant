package com.volgagas.personalassistant.utils.views.sticky_header.util;

import android.support.v7.widget.RecyclerView;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * Interface for getting the orientation of a RecyclerView from its LayoutManager
 */
public interface OrientationProvider {

    public int getOrientation(RecyclerView recyclerView);

    public boolean isReverseLayout(RecyclerView recyclerView);
}
