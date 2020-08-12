package com.dryanlam.spring.bdd.project.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:ws-endpoints.properties")
public class WsConfiguration {

    @Value("${project.ws.endpoint:}")
    private String projectEndpoint;

    public String getVhcpEndpoint() {
        return projectEndpoint;
    }

    public void setProjectEndpoint(String projectEndpoint) {
        this.projectEndpoint = projectEndpoint;
    }
}
