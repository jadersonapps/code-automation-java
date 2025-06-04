package br.com.codegroup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class responsible for loading and providing configuration values
 * defined in the config.properties file located in src/main/resources.
 *
 * <p>Recommended usage for reading parameters such as base URL, browser,
 * timeouts, etc.
 *
 * <p>Example usage:
 * <pre>{@code
 * String url = ConfigReader.get("url");
 * }</pre>
 *
 * Thread-safe and loads properties once at class loading.
 */
public final class ConfigReader {

    /**
     * Holds all loaded properties from the config.properties file.
     */
    private static final Properties properties = new Properties();

    /**
     * The name and path of the configuration file.
     */
    private static final String CONFIG_FILE_NAME = "/config.properties";

    /**
     * Static block to load properties from the config file at class load time.
     * Ensures the file is loaded once and reused during the application lifecycle.
     */
    static {
        try (InputStream input = ConfigReader.class.getResourceAsStream(CONFIG_FILE_NAME)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + CONFIG_FILE_NAME);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration file: " + CONFIG_FILE_NAME, e);
        }
    }

    /**
     * Private constructor to prevent instantiation.
     * This class is intended to be used statically.
     */
    private ConfigReader() {
        throw new UnsupportedOperationException("Utility class - should not be instantiated.");
    }

    /**
     * Returns the value associated with the specified key from config.properties.
     *
     * @param key The key to look for.
     * @return The corresponding value.
     * @throws RuntimeException if the key is not found.
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config.properties: " + key);
        }
        return value;
    }

    /**
     * Returns the value associated with the specified key or the default value
     * if the key is not found.
     *
     * @param key          The key to look for.
     * @param defaultValue The default value to return if key is absent.
     * @return The value corresponding to the key or defaultValue if not found.
     */
    public static String getOrDefault(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
