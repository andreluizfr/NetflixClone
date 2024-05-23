package com.example.UserAPI.Account.Models.Enums;

public enum Plan {
    BASIC_WITH_ADS(Short.valueOf("1")), 
    BASIC(Short.valueOf("2")), 
    PREMIUM(Short.valueOf("3"));

    private final Short value;

    Plan(Short value) {
        this.value = value;
    }

    public Short getValue() {
        return value;
    }

    public static Plan valueOfShort(Short number) {
        if (number.equals(Short.valueOf((short) 1))) return Plan.BASIC_WITH_ADS;
        if (number.equals(Short.valueOf((short) 2))) return Plan.BASIC;
        if (number.equals(Short.valueOf((short) 3))) return Plan.PREMIUM;
        return null;
    }
}
