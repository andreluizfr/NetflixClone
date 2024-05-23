package com.example.UserAPI.Account.Models.Enums;

import javax.persistence.AttributeConverter;

import org.springframework.stereotype.Component;

@Component
public class PlanConverter implements AttributeConverter<Plan, Short> {

    @Override
    public Short convertToDatabaseColumn(Plan plan) {
        if(plan == null) return null;
        return plan.getValue();
    }

    @Override
    public Plan convertToEntityAttribute(Short dbData) {
        if(dbData == null) return null;
        return Plan.valueOfShort(dbData);
    }
}