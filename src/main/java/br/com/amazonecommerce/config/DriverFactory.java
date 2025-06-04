package br.com.amazonecommerce.config;

import br.com.amazonecommerce.util.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Factory class responsible for managing WebDriver lifecycle and wait configurations.
 * Supports Chrome and Firefox browsers.
 */
public class DriverFactory {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private DriverFactory() {
        // Prevent instantiation
    }

    /**
     * Initializes the WebDriver based on the 'browserName' configuration.
     * Maximizes the browser window after startup.
     * If already initialized, the existing instance will be reused.
     */
    public static void initializeDriver() {
        if (driver != null) {
            return; // Driver already initialized
        }

        String browser = ConfigReader.get("browserName").toLowerCase();
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
    }

    /**
     * Quits the WebDriver and cleans up the resources.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
        }
    }

    /**
     * Returns the WebDriverWait instance, initializing it if necessary.
     * The timeout is configured via 'browserTimeSleep' in the config properties (default 10 seconds).
     *
     * @return a WebDriverWait instance
     */
    public static WebDriverWait getWait() throws InterruptedException {
        if (wait == null) {
            int timeout = 10;
            try {
                timeout = Integer.parseInt(ConfigReader.get("browserTimeSleep"));
            } catch (NumberFormatException e) {
                System.err.println("Invalid 'browserTimeSleep' value in config.properties, using default 10 seconds.");
            }
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        }
        Thread.sleep(5);
        return wait;
    }

    /**
     * Returns the current WebDriver instance.
     *
     * @return WebDriver instance or null if not initialized
     */
    public static WebDriver getDriver() {
        return driver;
    }
}
