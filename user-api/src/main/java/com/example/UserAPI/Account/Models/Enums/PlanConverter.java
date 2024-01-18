package com.example.UserAPI.Account.Models.Enums;

import javax.persistence.AttributeConverter;

public class PlanConverter implements AttributeConverter<Plan, Short> {

    @Override
    public Short convertToDatabaseColumn(final Plan plan) {
        if(plan == null) return null;
        return plan.getValue();
    }

    @Override
    public Plan convertToEntityAttribute(final Short dbData) {
        if (dbData == 1) {
            return Plan.BASIC_WITH_ADS;
        } else if (dbData == 2) {
            return Plan.BASIC;
        } else if (dbData == 3) {
            return Plan.PREMIUM;
        } else {
            return null;
        }
    }
}