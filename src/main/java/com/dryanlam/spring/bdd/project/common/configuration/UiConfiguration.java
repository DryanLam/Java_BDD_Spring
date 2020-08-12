package com.dryanlam.spring.bdd.project.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:ui-endpoints.properties")
public class UiConfiguration {

    @Value("${project.ui.manage.uri:}")
    private String projectEndpoint;

    public String getProjectEndpoint() {
        return projectEndpoint;
    }

    public void setProjectEndpoint(String projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }
}
