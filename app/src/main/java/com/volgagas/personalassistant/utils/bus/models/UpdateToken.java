package com.volgagas.personalassistant.utils.bus.models;

public class UpdateToken {
    private String update;

    public UpdateToken(String update) {
        this.update = update;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    @Override
    public String toString() {
        return "UpdateToken{" +
                "update='" + update + '\'' +
                '}';
    }
}
