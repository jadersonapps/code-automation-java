package br.com.amazonecommerce.util;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class providing common helper methods.
 */
public class Commons {

    /**
     * Captures a screenshot for the given step and attaches it to the ExtentTest report.
     *
     * @param driver   WebDriver instance.
     * @param stepName Name of the current step.
     * @param folder   Directory where the screenshot will be saved.
     * @param test     ExtentTest instance to attach the screenshot.
     */
    public static void captureScreenshotForStep(WebDriver driver, String stepName, String folder, ExtentTest test) {
        String screenshotName = generateFileName(stepName) + ".png";
        Path screenshotPath = Path.of(folder, screenshotName);

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Files.createDirectories(screenshotPath.getParent());
            try (FileOutputStream out = new FileOutputStream(screenshotPath.toFile())) {
                out.write(screenshot);
            }

            test.log(Status.INFO, "📸 Step: " + stepName);
            test.addScreenCaptureFromPath(screenshotName);
        } catch (IOException e) {
            System.err.println("Error saving screenshot: " + e.getMessage());
        }
    }

    /**
     * Generates a sanitized file name using the given text and current timestamp.
     *
     * @param text Input text to base the file name on.
     * @return Sanitized file name string.
     */
    public static String generateFileName(String text) {
        return text.replaceAll("[^a-zA-Z0-9]", "_") + "_" + System.currentTimeMillis();
    }

    /**
     * Generates a timestamp string in the format yyyyMMdd-HHmmss.
     *
     * @return Timestamp string.
     */
    public static String generateTimestamp() {
        return new SimpleDateFormat("dd-MM-yyyy HHmmss").format(new Date());
    }

    /**
     * Creates a new directory for the execution logs with timestamp in its name.
     *
     * @return Path to the created directory as a string.
     */
    public static String createExecutionDirectory() {
        String dir = "logs/execution-" + generateTimestamp();
        Path path = Path.of(dir);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println("Failed to create execution directory: " + e.getMessage());
        }
        return dir;
    }
}
