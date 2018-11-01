package com.volgagas.personalassistant.models.model;

import java.util.Objects;

import timber.log.Timber;

public class User {
    private String name;
    private String position;
    private String codekey;
    private String lastEntered;
    private String category;

    private String userCliendId;
    private String dynamics365Token;
    private String sharePointToken;

    public void setBaseFields(User user) {
        Timber.d("setBase felds" + user.toString());
        this.name = user.getName();
        this.position = user.getPosition();
        this.codekey = user.getCodekey();
        this.lastEntered = user.getLastEntered();
        this.category = user.getCategory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(codekey, user.codekey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, codekey);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", codekey='" + codekey + '\'' +
                ", lastEntered='" + lastEntered + '\'' +
                ", category='" + category + '\'' +
                ", userCliendId='" + userCliendId + '\'' +
                ", dynamics365Token='" + dynamics365Token + '\'' +
                ", sharePointToken='" + sharePointToken + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCodekey() {
        return codekey;
    }

    public void setCodekey(String codekey) {
        this.codekey = codekey;
    }

    public String getLastEntered() {
        return lastEntered;
    }

    public void setLastEntered(String lastEntered) {
        this.lastEntered = lastEntered;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserCliendId() {
        return userCliendId;
    }

    public void setUserCliendId(String userCliendId) {
        this.userCliendId = userCliendId;
    }

    public String getDynamics365Token() {
        return dynamics365Token;
    }

    public void setDynamics365Token(String dynamics365Token) {
        this.dynamics365Token = dynamics365Token;
    }

    public String getSharePointToken() {
        return sharePointToken;
    }

    public void setSharePointToken(String sharePointToken) {
        this.sharePointToken = sharePointToken;
    }

    public void clear() {
        this.name = "";
        this.position = "";
        this.codekey = "";
        this.lastEntered = "";
        this.category = "";

        this.userCliendId = "";
        this.dynamics365Token = "";
        this.sharePointToken = "";
    }
}
