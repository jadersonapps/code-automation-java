package br.com.amazonecommerce;

import br.com.amazonecommerce.config.DriverFactory;
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
                "br.com.amazonecommerce.step",
                "br.com.amazonecommerce.config"
        },
        plugin = {
                "pretty",
                "br.com.amazonecommerce.config.Logger",
                "br.com.amazonecommerce.config.Listener"
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