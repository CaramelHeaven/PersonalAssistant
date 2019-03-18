package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.models.network.PurchaseOrderResponse;
import com.volgagas.personalassistant.models.network.orders.PurchaseOrderNetwork;

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
        for (PurchaseOrderNetwork network : purchaseOrderResponse.getValue()) {
            ServerOrder serverOrder = new ServerOrder();
            serverOrder.setDeliveryAddress(network.getFormattedDeliveryAddress());
            serverOrder.setPurchaseOrderName(network.getPurchaseOrderName());
            serverOrder.setStatus(network.getDocumentApprovalStatus());
            serverOrder.setPurchaseOrderNumber(network.getPurchaseOrderNumber());

            serverOrders.add(serverOrder);
        }
    }
}
