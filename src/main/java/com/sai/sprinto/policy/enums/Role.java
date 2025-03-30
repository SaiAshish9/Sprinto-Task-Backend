package com.sai.sprinto.policy.enums;

public enum Role {
    ADMIN("ADMIN"),
    HR("HR"),
    ENGINEER("ENGINEER"),
    CUSTOMER("CUSTOMER");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

