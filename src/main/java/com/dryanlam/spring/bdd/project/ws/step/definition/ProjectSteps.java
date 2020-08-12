package com.dryanlam.spring.bdd.project.ws.step.definition;

import com.dryanlam.spring.bdd.project.*;
import cucumber.api.java.en.*;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j;

@Log4j
public class ProjectSteps {

    @Autowired
    WsConfiguration wsConfiguration;

    @Autowired
    UiConfiguration uiConfiguration;

    @Autowired
    ProjectGateway projectGateway;

    @Autowired
    Parser parser;

    private Response response;

    @Given("^We have$")
    public void weHave() {
        log.info("hello");
        log.info(uiConfiguration.getProjectEndpoint());
    }

    @When("^We do$")
    public void weDo() {
        log.info("we do");
    }

    @Then("^We see$")
    public void weSee() {
        log.info(wsConfiguration.getProjectEndpoint());
    }

    @When("^We send a POST request to \"([^\"]*)\" endpoint with body:$")
    public void sendRequestWithBody(String uri, String json) {
        UserDTO userDTO = parser.parseFromJson(json, UserDTO.class);
        response = projectGateway.doPostWithBody(userDTO, uri);
    }

    @Then("^We got the Response with status code (\\d+)$")
    public void gotResponseStatusCode(int statusCode) {
        Asserts.assertEquals(statusCode, response.getStatusCode());
    }

    @And("^We got the Response with body:$")
    public void gotResponseBody(String json) {
        UserDTO expected = parser.parseFromJson(json, UserDTO.class);
        UserDTO actual = parser.parseFromJson(response.getBody().prettyPrint(), UserDTO.class);
        Asserts.assertEquals(expected, actual);
    }
}
