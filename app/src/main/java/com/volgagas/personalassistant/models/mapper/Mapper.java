package com.volgagas.personalassistant.models.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public abstract class Mapper<T1, T2> {
    public abstract T2 map(T1 value);

    public List<T2> map(List<T1> values) {
        List<T2> returnValues = new ArrayList<>(values.size());
        for (T1 value : values) {
            returnValues.add(map(value));
        }
        return returnValues;
    }

    protected abstract void fillData(T2 t2, T1 t1);
}
