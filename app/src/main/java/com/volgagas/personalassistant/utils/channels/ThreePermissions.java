package com.volgagas.personalassistant.utils.channels;

/**
 * Created by CaramelHeaven on 16:42, 01.11.2018.
 * Copyright (c) 2018 VolgaGas. All rights reserved.
 */
public class ThreePermissions {

    private static volatile ThreePermissions INSTANCE;

    private Boolean server = false;
    private Boolean d365Token = false;
    private Boolean sharePointToken = false;

    public static ThreePermissions getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreePermissions.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreePermissions();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "ThreePermissions{" +
                "server=" + server +
                ", d365Token=" + d365Token +
                ", sharePointToken=" + sharePointToken +
                '}';
    }

    public Boolean getServer() {
        return server;
    }

    public void setServer(Boolean server) {
        this.server = server;
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

    public Boolean anyValueIsTrue() {
        return d365Token || server || sharePointToken;
    }

    public Boolean allValuesIsTrue() {
        return d365Token && server && sharePointToken;
    }

    public void resetValues() {
        this.d365Token = false;
        this.server = false;
        this.sharePointToken = false;
    }
}
