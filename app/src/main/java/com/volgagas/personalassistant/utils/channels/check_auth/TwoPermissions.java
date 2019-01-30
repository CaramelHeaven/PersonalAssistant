package com.volgagas.personalassistant.utils.channels.check_auth;

/**
 * Created by CaramelHeaven on 15:57, 28.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 * <p>
 * This class only for update tokens from D365 and SharePoint
 */
public class TwoPermissions {
    private static TwoPermissions INSTANCE;

    private Boolean d365Token = false;
    private Boolean sharePointToken = false;
    private String updatedString = "";

    public static TwoPermissions getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreePermissions.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TwoPermissions();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "TwoPermissions{" +
                "d365Token=" + d365Token +
                ", sharePointToken=" + sharePointToken +
                ", updatedString='" + updatedString + '\'' +
                '}';
    }

    public Boolean getD365Token() {
        return d365Token;
    }

    public void setD365Token(Boolean d365Token) {
        this.d365Token = d365Token;
    }

    public Boolean getSharePointToken() {
        return sharePointToken;
    }

    public void setSharePointToken(Boolean sharePointToken) {
        this.sharePointToken = sharePointToken;
    }

    public void resetValues() {
        this.d365Token = false;
        this.sharePointToken = false;
        updatedString = "";
    }

    public Boolean allValuesIsTrue() {
        return d365Token && sharePointToken;
    }

    public String getUpdatedString() {
        return updatedString;
    }

    public void setUpdatedString(String updatedString) {
        this.updatedString = updatedString;
    }
}
