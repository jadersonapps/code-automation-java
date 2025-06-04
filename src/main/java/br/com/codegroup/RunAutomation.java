package br.com.codegroup;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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
        tags = "@Regressivo",
        monochrome = true
)
public class RunAutomation {
        // Runner class - no methods needed
}
