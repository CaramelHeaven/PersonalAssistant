package com.volgagas.personalassistant.models.model;

/**
 * Created by CaramelHeaven on 13:16, 13.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class Message {
    private String author;
    private String message;
    private boolean completedSendToServer;

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }

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

    @Override
    public String toString() {
        return "Message{" +
                "author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", completedSendToServer=" + completedSendToServer +
                '}';
    }

    public boolean isCompletedSendToServer() {
        return completedSendToServer;
    }

    public void setCompletedSendToServer(boolean completedSendToServer) {
        this.completedSendToServer = completedSendToServer;
    }
}
