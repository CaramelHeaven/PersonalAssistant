package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.models.network.PurchaseOrderResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 19:16, 27/02/2019.
 */
public class PurchaseOrderToServerOrder extends Mapper<PurchaseOrderResponse, List<ServerOrder>> {

    @Override
    public List<ServerOrder> map(PurchaseOrderResponse value) {
        List<ServerOrder> serverOrders = new ArrayList<>();
        fillData(serverOrders, value);

        return serverOrders;
    }

    @Override
    protected void fillData(List<ServerOrder> serverOrders, PurchaseOrderResponse purchaseOrderResponse) {

    }
}
