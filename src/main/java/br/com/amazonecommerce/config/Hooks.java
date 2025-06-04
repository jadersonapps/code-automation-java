package br.com.amazonecommerce.config;

import br.com.amazonecommerce.util.Commons;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Hooks for Cucumber scenarios to manage ExtentReports and screenshots after each step.
 */
public class Hooks {

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    /**
     * Executed before each scenario.
     * Initializes a new ExtentTest instance for the current scenario.
     *
     * @param scenario the current Cucumber scenario object
     */
    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest scenarioTest = Report.getInstance().createTest(scenario.getName());
        test.set(scenarioTest);
    }

    /**
     * Executed after each scenario.
     * Flushes the report to save information and removes the ExtentTest from ThreadLocal.
     *
     * @param scenario the current Cucumber scenario object
     */
    @After
    public void afterScenario(Scenario scenario) {
        Report.getInstance().flush();
        test.remove();
    }

    /**
     * Executed after each step of the scenario.
     * Captures a screenshot of the current browser state and adds it to the ExtentReports.
     * Creates a report node for the current step, marking it as pass or fail.
     *
     * @param scenario the current Cucumber scenario object
     */
    @AfterStep
    public void afterEachStep(Scenario scenario) {
        if (DriverFactory.getDriver() == null || test.get() == null) {
            return;
        }

        try {
            String currentStepName = Logger.getCurrentStepName();
            if (currentStepName == null || currentStepName.isEmpty()) {
                return;
            }

            String executionFolder = Report.getExecutionFolder();
            File screenshotsFolder = new File(executionFolder, "screenshots");
            if (!screenshotsFolder.exists() && !screenshotsFolder.mkdirs()) {
                test.get().log(Status.WARNING, "Unable to create screenshots folder: " + screenshotsFolder.getAbsolutePath());
                return;
            }

            String screenshotFileName = Commons.generateFileName("step_" + System.currentTimeMillis()) + ".png";
            File screenshotFile = new File(screenshotsFolder, screenshotFileName);

            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            try (FileOutputStream out = new FileOutputStream(screenshotFile)) {
                out.write(screenshot);
            }

            String relativePath = "screenshots/" + screenshotFileName;

            ExtentTest stepNode = test.get().createNode(currentStepName);

            if (scenario.isFailed()) {
                Throwable error = Listener.getCurrentStepError();
                String errorMessage = (error != null) ? error.getMessage() : "Unknown error during step execution.";
                stepNode.log(Status.FAIL, "❌🔥 Step execution failed! Error: " + errorMessage)
                        .addScreenCaptureFromPath(relativePath);
            } else {
                stepNode.log(Status.PASS, "✅ Step executed successfully!")
                        .addScreenCaptureFromPath(relativePath);
            }

        } catch (IOException e) {
            test.get().log(Status.WARNING, "Error capturing screenshot: " + e.getMessage());
        }
    }
}
