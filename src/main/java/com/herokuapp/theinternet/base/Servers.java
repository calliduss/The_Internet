package com.herokuapp.theinternet.base;

public enum Servers {

    PROD("prod"),
    QA("qa");

    private String value;

    private Servers(String value) {
        this.value = value;
    }

    public String getServer() {
        return value;
    }
}