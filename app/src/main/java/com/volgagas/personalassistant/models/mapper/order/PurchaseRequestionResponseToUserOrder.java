package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.models.network.PurchaseRequestionResponse;
import com.volgagas.personalassistant.models.network.orders.PurchaseRequisitionNetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaramelHeaven on 15:56, 27/02/2019.
 */
public class PurchaseRequestionResponseToUserOrder extends Mapper<PurchaseRequestionResponse, List<UserOrder>> {
    @Override
    public List<UserOrder> map(PurchaseRequestionResponse value) {
        List<UserOrder> userOrders = new ArrayList<>();
        fillData(userOrders, value);

        return userOrders;
    }

    @Override
    protected void fillData(List<UserOrder> userOrders, PurchaseRequestionResponse purchaseRequestionResponse) {
        for (PurchaseRequisitionNetwork network : purchaseRequestionResponse.getValue()) {
            UserOrder order = new UserOrder();
            order.setName(network.getRequisitionName());
            order.setRequisitionNumber(network.getRequisitionNumber());
            order.setStatus(network.getRequisitionStatus());

            userOrders.add(order);
        }
    }
}
