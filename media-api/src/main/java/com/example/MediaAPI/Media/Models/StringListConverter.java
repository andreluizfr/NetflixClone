package com.example.MediaAPI.Media.Models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

public class StringListConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(final List<String> list) {
        return String.join(",", list.stream().map(str -> str.replace(",", "")).collect(Collectors.toList()));
    }

    @Override
    public List<String> convertToEntityAttribute(final String dbData) {
        if(dbData != null) {
            return Arrays.asList(dbData.split(","));
        } else {
            return null;
        }
    }
}