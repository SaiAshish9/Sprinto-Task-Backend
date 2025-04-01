package com.sai.sprinto.policy.service.policy.abstractFactory.enums;

public enum UserPolicyType {
    HR_POLICY("HR_POLICY"),
    ENGINEER_POLICY("ENGINEER_POLICY"),
    ADMIN_POLICY("ADMIN_POLICY"),
    CXO_POLICY("CXO_POLICY"),
    CUSTOMER_POLICY("CUSTOMER_POLICY");

    private final String value;

    UserPolicyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
