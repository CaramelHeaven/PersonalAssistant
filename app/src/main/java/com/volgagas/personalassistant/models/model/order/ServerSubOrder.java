package com.volgagas.personalassistant.models.model.order;

/**
 * Created by CaramelHeaven on 19:12, 27/02/2019.
 */
public class ServerSubOrder {
    private String name;
    private String description;
    private String address;

    @Override
    public String toString() {
        return "ServerSubOrder{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
