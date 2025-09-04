package com.journalapp.entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RolesConverter implements AttributeConverter<List<String>, String> {
    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(List<String> roles) {
        return String.join(DELIMITER, roles);
    }

    @Override
    public List<String> convertToEntityAttribute(String rolesString) {
        return Arrays.asList(rolesString.split(DELIMITER));
    }
}
