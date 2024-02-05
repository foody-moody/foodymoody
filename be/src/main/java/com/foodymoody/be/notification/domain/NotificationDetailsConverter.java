package com.foodymoody.be.notification.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodymoody.be.common.exception.JsonConvertException;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class NotificationDetailsConverter implements AttributeConverter<NotificationDetails, String> {

    @Override
    public String convertToDatabaseColumn(NotificationDetails attribute) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException();
        }
    }

    @Override
    public NotificationDetails convertToEntityAttribute(String dbData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(dbData, new TypeReference<NotificationDetails>() {
            });
        } catch (JsonProcessingException e) {
            throw new JsonConvertException();
        }
    }
}
