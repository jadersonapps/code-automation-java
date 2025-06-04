package br.com.codegroup;

import br.com.codegroup.config.DriverFactory;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

/**
 * Entry point for running automated tests using Cucumber with JUnit.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "br.com.codegroup.step",
                "br.com.codegroup.config"
        },
        plugin = {
                "pretty",
                "br.com.codegroup.config.Logger",
                "br.com.codegroup.config.Listener"
        },
        tags = "@Autocomplete",
        monochrome = true
)
public class RunAutomation {

        @AfterClass
        public static void teardownSuite() {
                DriverFactory.quitDriver();
        }
}