package com.sai.sprinto.policy.enums;

public enum PolicyType {
    INFOSEC("INFOSEC"),
    ACCEPTABLE_USE("ACCEPTABLE_USE"),
    CRYPTOGRAPHIC("CRYPTOGRAPHIC");

    private final String value;

    PolicyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
