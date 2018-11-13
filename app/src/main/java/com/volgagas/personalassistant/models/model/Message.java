package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 13:16, 13.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Message {
    private String message;
    private String author;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
