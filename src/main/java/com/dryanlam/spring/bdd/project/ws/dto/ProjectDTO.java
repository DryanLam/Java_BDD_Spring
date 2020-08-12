package com.dryanlam.spring.bdd.project.ws.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class ProjectDTO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDTO.class.getCanonicalName());

    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof ProjectDTO)) return false;
        ProjectDTO projectDTO = (ProjectDTO) ob;
        return Objects.equals(projectDTO.getName(), name)
            && Objects.equals(projectDTO.getCity(), city);
    }

    @Override
    public int hashCode() {
        return (name == null ? 0 : name.hashCode())
             + (city == null ? 0 : city.hashCode());
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage());
        }
        return "";
    }
}
