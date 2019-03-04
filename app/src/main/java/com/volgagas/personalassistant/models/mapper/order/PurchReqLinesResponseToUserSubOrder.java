package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.mapper.Mapper;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.models.network.PurchReqLinesResponse;
import com.volgagas.personalassistant.models.network.orders.PurchReqLinesNetwork;

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
        for (PurchReqLinesNetwork network : purchReqLinesResponse.getValue()) {
            UserSubOrder subOrder = new UserSubOrder();
            subOrder.setDescription(network.getLineDescription());
            subOrder.setPrice(String.valueOf(network.getPurchasePrice()));
            subOrder.setPriceCode(network.getProjectSalesCurrencyCode());
            subOrder.setQuantity(String.valueOf(network.getRequestedPurchaseQuantity()));
            subOrder.setUnit(network.getPurchaseUnitSymbol());

            userSubOrders.add(subOrder);
        }
    }
}
