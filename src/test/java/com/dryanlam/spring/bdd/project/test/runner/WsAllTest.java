package com.dryanlam.spring.bdd.project.test.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/java/com/dryanlam/spring/bdd/project/test/features"},
        plugin = {"pretty", "json:target/test-classes/reports/ProjectWS.json"},
        glue = {"com.dryanlam.spring.bdd.project.ws.step.definition"}
)
public class WsAllTest extends AbstractTestNGCucumberTests {
}
