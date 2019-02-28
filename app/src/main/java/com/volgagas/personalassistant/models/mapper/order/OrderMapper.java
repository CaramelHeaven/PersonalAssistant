package com.volgagas.personalassistant.models.mapper.order;

import com.volgagas.personalassistant.models.model.order.ServerOrder;
import com.volgagas.personalassistant.models.model.order.ServerSubOrder;
import com.volgagas.personalassistant.models.model.order.UserOrder;
import com.volgagas.personalassistant.models.model.order.UserSubOrder;
import com.volgagas.personalassistant.models.network.PurchOrderLinesResponse;
import com.volgagas.personalassistant.models.network.PurchReqLinesResponse;
import com.volgagas.personalassistant.models.network.PurchaseOrderResponse;
import com.volgagas.personalassistant.models.network.PurchaseRequestionResponse;

import java.util.List;

/**
 * Created by CaramelHeaven on 16:05, 27/02/2019.
 */
public class OrderMapper {
    private final PurchReqLinesResponseToUserSubOrder purchReqLinesResponseToUserSubOrder;
    private final PurchaseRequestionResponseToUserOrder purchaseRequestionResponseToUserOrder;

    private final PurchaseOrderToServerOrder purchaseOrderToServerOrder;
    private final PurchOrderLinesResponseToServerSubOrder purchOrderLinesResponseToServerSubOrder;

    public OrderMapper(PurchReqLinesResponseToUserSubOrder purchReqLinesResponseToUserSubOrder,
                       PurchaseRequestionResponseToUserOrder purchaseRequestionResponseToUserOrder,
                       PurchaseOrderToServerOrder purchaseOrderToServerOrder,
                       PurchOrderLinesResponseToServerSubOrder purchOrderLinesResponseToServerSubOrder) {
        this.purchReqLinesResponseToUserSubOrder = purchReqLinesResponseToUserSubOrder;
        this.purchaseRequestionResponseToUserOrder = purchaseRequestionResponseToUserOrder;
        this.purchaseOrderToServerOrder = purchaseOrderToServerOrder;
        this.purchOrderLinesResponseToServerSubOrder = purchOrderLinesResponseToServerSubOrder;
    }

    public List<UserOrder> map(PurchaseRequestionResponse response) {
        return purchaseRequestionResponseToUserOrder.map(response);
    }

    public List<UserSubOrder> map(PurchReqLinesResponse response) {
        return purchReqLinesResponseToUserSubOrder.map(response);
    }

    public List<ServerOrder> map(PurchaseOrderResponse response) {
        return purchaseOrderToServerOrder.map(response);
    }

    public List<ServerSubOrder> map(PurchOrderLinesResponse response) {
        return purchOrderLinesResponseToServerSubOrder.map(response);
    }
}
