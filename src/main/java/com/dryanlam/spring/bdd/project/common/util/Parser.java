package com.dryanlam.spring.bdd.project.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import cucumber.api.DataTable;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {

    public <T> List<T> parseDatatableToList(DataTable dataTable, Class clazz) {
        return new ArrayList(dataTable.asList(clazz));
    }

    public <T> T parseFromJson(String jsonString, Class clazz) {
        return (T) JsonObjectMapper.get().readValue(jsonString, clazz);
    }

    public <T> T parseFromJson(String jsonString, TypeReference typeReference) {
        return JsonObjectMapper.get().readValue(jsonString, typeReference);
    }
}