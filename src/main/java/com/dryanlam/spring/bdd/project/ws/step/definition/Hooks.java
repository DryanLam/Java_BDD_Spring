package com.dryanlam.spring.bdd.project.ws.step.definition;

import com.dryanlam.spring.bdd.project.common.configuration.AppConfiguration;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AppConfiguration.class)
public class Hooks {

    @Before
    public void setUp() {
    }

    @After
    public void cleanUp() {
    }
}
