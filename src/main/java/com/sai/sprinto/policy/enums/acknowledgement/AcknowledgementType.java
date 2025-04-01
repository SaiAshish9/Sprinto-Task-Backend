package com.sai.sprinto.policy.enums.acknowledgement;

public enum AcknowledgementType {
    MANUAL("MANUAL"),
    PERIODIC("PERIODIC"),
    CUSTOMER("CUSTOMER");

    private final String value;

    AcknowledgementType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
