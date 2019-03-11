package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 17:47, 22.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class UserDynamics {
    private String personalNumber;
    private String workerRecId;

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getWorkerRecId() {
        return workerRecId;
    }

    public void setWorkerRecId(String workerRecId) {
        this.workerRecId = workerRecId;
    }

    @Override
    public String toString() {
        return "UserDynamics{" +
                "personalNumber='" + personalNumber + '\'' +
                ", workerRecId='" + workerRecId + '\'' +
                '}';
    }
}
