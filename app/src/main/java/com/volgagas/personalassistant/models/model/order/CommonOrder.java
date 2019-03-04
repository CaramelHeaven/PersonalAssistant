package com.volgagas.personalassistant.models.model.order;

import java.util.List;

/**
 * Created by CaramelHeaven on 18:27, 04/03/2019.
 */
public class CommonOrder {
    private List<UserOrder> userOrders;
    private List<ServerOrder> serverOrders;

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public List<ServerOrder> getServerOrders() {
        return serverOrders;
    }

    public void setServerOrders(List<ServerOrder> serverOrders) {
        this.serverOrders = serverOrders;
    }
}
