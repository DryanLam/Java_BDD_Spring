package com.dryanlam.spring.bdd.project.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dryanlam.spring.bdd.project.common.exception.ProjectException;
import java.io.IOException;

/**
 * The default ObjectMapper will throw an error on unknown/undefined properties
 * This class help  bypass such the problems.
 * 
 */
public class JsonObjectMapper extends ObjectMapper {

    private static final JsonObjectMapper INSTANCE = new JsonObjectMapper();

    public static JsonObjectMapper get() {
        return INSTANCE;
    }

    private JsonObjectMapper() {
    }

    @Override
    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new ProjectException("Could not parse data to JSON value", e);
        }
    }

    @Override
    public <T> T readValue(String content, Class<T> valueType) {
        try {
            return super.readValue(content, valueType);
        } catch (IOException e) {
            throw new ProjectException("Could not parse JSON string to data type " + valueType, e);
        }
    }

    @Override
    public <T> T readValue(String content, TypeReference valueTypeRef) {
        try {
            return super.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw new ProjectException("Could not parse JSON string to data type " + valueTypeRef.getType(), e);
        }
    }
}
