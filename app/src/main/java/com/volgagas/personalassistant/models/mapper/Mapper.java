package com.volgagas.personalassistant.models.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class Mapper<T1, T2> {
    public abstract T2 map(T1 value);

    /**
     * Base mapper method
     *
     * @param values - T1 - data from server
     */
    public List<T2> map(List<T1> values) {
        List<T2> returnValues = new ArrayList<>(values.size());
        for (T1 value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }

    /**
     * Fill fields for our classes
     *
     * @param t2 - data used inside application
     * @param t1 - data from server
     */
    protected abstract void fillData(T2 t2, T1 t1);
}
