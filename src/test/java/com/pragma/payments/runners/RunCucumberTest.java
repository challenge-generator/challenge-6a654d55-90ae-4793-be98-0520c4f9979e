package com.pragma.payments.runners;

import io.cucumber.junit.platform.Cucumber;
import org.junit.platform.suite.annotation.ConfigurationParameter;
import org.junit.platform.suite.annotation.IncludeEngines;
import org.junit.platform.suite.engine.SuiteEngine;
import org.junit.platform.suite.engine.SuiteLauncherDescriptor;

import static io.cucumber.junit.platform.engine.Constants.*;

@Cucumber
@IncludeEngines("cucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,net.serenitybdd.cucumber.merging.SerenityReporter")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.pragma.payments.steps,com.pragma.payments.runners")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
@ConfigurationParameter(key = TAGS_PROPERTY_NAME, value = "@security")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME, value = "CAMELCASE")
public class RunCucumberTest {
    
    private RunCucumberTest() {
    }
}