package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.ServerSubOrder;
import com.volgagas.personalassistant.models.network.PurchOrderLinesResponse;
import com.volgagas.personalassistant.models.network.orders.PurchOrderLinesNetwork;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 19:27, 27/02/2019.
 */
public class PurchOrderLinesResponseToServerSubOrder extends Mapper<PurchOrderLinesResponse, List<ServerSubOrder>> {
    @Override
    public List<ServerSubOrder> map(PurchOrderLinesResponse value) {
        List<ServerSubOrder> subOrders = new ArrayList<>();
        Timber.d("mapping kek");
        fillData(subOrders, value);

        return subOrders;
    }

    @Override
    protected void fillData(List<ServerSubOrder> serverSubOrders, PurchOrderLinesResponse purchOrderLinesResponse) {
        for (PurchOrderLinesNetwork network : purchOrderLinesResponse.getValue()) {
            ServerSubOrder order = new ServerSubOrder();
            order.setAddress(network.getFormattedDelveryAddress());
            order.setDescription(network.getLineDescription());
            order.setName(network.getProcurementProductCategoryName());

            serverSubOrders.add(order);
        }
    }
}
