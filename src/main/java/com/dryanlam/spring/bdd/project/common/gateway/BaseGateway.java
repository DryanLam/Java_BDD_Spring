package com.dryanlam.spring.bdd.project.common.gateway;

import com.dryanlam.spring.bdd.project.common.exception.ProjectException;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.net.URISyntaxException;

public class BaseGateway {

    protected RestTemplate restTemplate;

    protected final String baseUri;

    public boolean isAvailable() {
        return true;
    }

    public BaseGateway(RestTemplate restTemplate, String baseUri) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
    }

    protected URI buildUri(String relativeUri) {
        try {
            return new URI(buildURIAsString(relativeUri));
        } catch (URISyntaxException e) {
            throw new ProjectException("*** ERROR: Could not build URI", e);
        }
    }

    protected String buildURIAsString(String relativeUri) {
        return baseUri + relativeUri;
    }
}
