package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.models.network.PurchReqLinesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 16:04, 27/02/2019.
 */
public class PurchReqLinesResponseToUserSubOrder extends Mapper<PurchReqLinesResponse, List<UserSubOrder>> {
    @Override
    public List<UserSubOrder> map(PurchReqLinesResponse value) {
        List<UserSubOrder> subOrders = new ArrayList<>();
        fillData(subOrders, value);

        return subOrders;
    }

    @Override
    protected void fillData(List<UserSubOrder> userSubOrders, PurchReqLinesResponse purchReqLinesResponse) {

    }
}
