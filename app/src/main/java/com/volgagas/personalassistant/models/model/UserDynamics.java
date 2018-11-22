package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 17:47, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserDynamics {
    private String personalNumber;

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    @Override
    public String toString() {
        return "UserDynamics{" +
                "personalNumber='" + personalNumber + '\'' +
                '}';
    }
}
