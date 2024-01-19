package com.foodymoody.be.notification.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodymoody.be.common.exception.JsonConvertException;
import java.util.Map;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;

/**
 * The NotificationDetailsConverter class is an implementation of the AttributeConverter interface used to convert a
 * Map<String, Object> to a JSON string and vice versa.
 */
@RequiredArgsConstructor
@Converter
public class NotificationDetailsConverter implements AttributeConverter<Map<String, Object>, String> {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts the given entity attribute value to a JSON string.
     *
     * @param attribute the entity attribute value to be converted
     * @return the JSON string representation of the attribute
     * @throws JsonConvertException if an error occurs during the JSON conversion process
     */
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException();
        }
    }

    /**
     * Converts the given database column data to a {@code Map<String, Object>} entity attribute.
     *
     * @param dbData the data from the database column to be converted
     * @return the converted entity attribute as a {@code Map<String, Object>}
     * @throws JsonConvertException if an error occurs during the JSON conversion process
     */
    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Map<String, Object>>() {
            });
        } catch (JsonProcessingException e) {
            throw new JsonConvertException();
        }
    }
}
