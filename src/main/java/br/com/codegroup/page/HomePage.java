package br.com.codegroup.page;

import org.junit.Assert;
import br.com.codegroup.config.DriverFactory;
import br.com.codegroup.util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Represents the Amazon home page with search functionalities.
 */
public class HomePage {

    /**
     * Locator for the search input bar.
     */
    public static final By INPUT_FIELD_SEARCH_BAR = By.id("twotabsearchtextbox");

    /**
     * Locator for autocomplete suggestion elements.
     */
    public static final By LIST_AUTOCOMPLETE_SUGGESTIONS = By.cssSelector("div.autocomplete-results-container div.s-suggestion");

    /**
     * Navigates to the Amazon home page using the URL configured in config.properties,
     * waits until the title contains "Amazon", and refreshes the page.
     */
    public static void navigateToHomePage() {
        DriverFactory.getDriver().get(ConfigReader.get("browserUrl"));
        DriverFactory.getWait().until(ExpectedConditions.titleContains("Amazon"));
        DriverFactory.getDriver().navigate().refresh();
    }

    /**
     * Types the given text into the search bar after clearing any existing text.
     *
     * @param text Text to be entered in the search bar.
     */
    public static void typeInSearchBar(String text) {
        WebElement input = DriverFactory.getDriver().findElement(INPUT_FIELD_SEARCH_BAR);
        input.clear();
        input.sendKeys(text);
    }

    /**
     * Clicks on the search bar element.
     */
    public static void clickSearchBar() {
        DriverFactory.getDriver().findElement(INPUT_FIELD_SEARCH_BAR).click();
    }

    /**
     * Asserts that autocomplete suggestions are visible.
     * Fails the test if no suggestions are found.
     */
    public static void assertSuggestionsAreVisible() {
        try {
            DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(LIST_AUTOCOMPLETE_SUGGESTIONS));
            List<WebElement> suggestions = DriverFactory.getDriver().findElements(LIST_AUTOCOMPLETE_SUGGESTIONS);
            Assert.assertFalse("Expected suggestions to be visible, but none were found.", suggestions.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error while validating visible suggestions: " + e.getMessage());
        }
    }

    /**
     * Asserts that autocomplete suggestions are not visible.
     * Fails the test if any suggestion is still visible.
     */
    public static void assertSuggestionsAreNotVisible() {
        try {
            DriverFactory.getWait().until(ExpectedConditions.invisibilityOfElementLocated(LIST_AUTOCOMPLETE_SUGGESTIONS));
            List<WebElement> suggestions = DriverFactory.getDriver().findElements(LIST_AUTOCOMPLETE_SUGGESTIONS);
            Assert.assertTrue("Expected no suggestions to be visible, but some were found.", suggestions.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error while validating suggestions are not visible: " + e.getMessage());
        }
    }
}
