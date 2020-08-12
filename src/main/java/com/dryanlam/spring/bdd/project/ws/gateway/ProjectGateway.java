package com.dryanlam.spring.bdd.project.ws.gateway;

import com.dryanlam.spring.bdd.project.common.configuration.WsConfiguration;
import com.dryanlam.spring.bdd.project.common.gateway.BaseGateway;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static io.restassured.RestAssured.given;

@Service
public class ProjectGateway extends BaseGateway {

    @Autowired
    WsConfiguration wsConfiguration;

    @Autowired
    public ProjectGateway(RestTemplate restTemplate, @Value("${project.ws.manage.uri}") String baseUri) {
        super(restTemplate, baseUri);
    }

    public Response doPostWithBody(Object bodyObj, String uriPath) {
        return given().contentType(ContentType.JSON)
                      .body(bodyObj)
                      .post(buildUri(uriPath))
                      .thenReturn();
    }
}
