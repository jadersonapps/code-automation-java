package br.com.amazonecommerce.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Singleton manager for ExtentReports used in automated test reporting.
 */
public class Report {

    private static ExtentReports extent;
    private static String executionFolder;

    /**
     * Returns the singleton instance of ExtentReports, creating it if necessary.
     * @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            executionFolder = br.com.amazonecommerce.util.Commons.createExecutionDirectory();
            createInstance(executionFolder + "/report.html");
        }
        return extent;
    }

    /**
     * Gets the folder where the test execution artifacts (reports, screenshots) are stored.
     * @return path to execution folder
     */
    public static String getExecutionFolder() {
        return executionFolder;
    }

    /**
     * Creates and configures the ExtentReports instance with a Spark reporter.
     * @param filePath the full file path to the HTML report
     */
    private static void createInstance(String filePath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automated Test Report");
        sparkReporter.config().setReportName("Automated Execution");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }
}
