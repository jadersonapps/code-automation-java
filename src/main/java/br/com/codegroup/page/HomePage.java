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

    private static final By SEARCH_BAR = By.id("twotabsearchtextbox");
    private static final By AUTOCOMPLETE_SUGGESTIONS = By.cssSelector("div.autocomplete-results-container div.s-suggestion");

    /**
     * Navigates to the home page URL and waits for the title to contain "Amazon".
     */
    public static void navigateToHomePage() {
        DriverFactory.getDriver().get(ConfigReader.get("browserUrl"));
        DriverFactory.getWait().until(ExpectedConditions.titleContains("Amazon"));
        DriverFactory.getDriver().navigate().refresh();
    }

    /**
     * Types the given text into the search bar, clearing it first.
     *
     * @param text Text to enter in the search bar.
     */
    public static void typeInSearchBar(String text) {
        WebElement input = DriverFactory.getDriver().findElement(SEARCH_BAR);
        input.clear();
        input.sendKeys(text);
    }

    /**
     * Clicks on the search bar.
     */
    public static void clickSearchBar() {
        DriverFactory.getDriver().findElement(SEARCH_BAR).click();
    }

    /**
     * Validates that autocomplete suggestions are visible.
     */
    public static void assertSuggestionsAreVisible() {
        try {
            DriverFactory.getWait().until(ExpectedConditions.visibilityOfElementLocated(AUTOCOMPLETE_SUGGESTIONS));
            List<WebElement> suggestions = DriverFactory.getDriver().findElements(AUTOCOMPLETE_SUGGESTIONS);
            Assert.assertFalse("Expected suggestions to be visible, but none were found.", suggestions.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error while validating visible suggestions: " + e.getMessage());
        }
    }

    /**
     * Validates that autocomplete suggestions are not visible.
     */
    public static void assertSuggestionsAreNotVisible() {
        try {
            DriverFactory.getWait().until(ExpectedConditions.invisibilityOfElementLocated(AUTOCOMPLETE_SUGGESTIONS));
            List<WebElement> suggestions = DriverFactory.getDriver().findElements(AUTOCOMPLETE_SUGGESTIONS);
            Assert.assertTrue("Expected no suggestions to be visible, but some were found.", suggestions.isEmpty());
        } catch (Exception e) {
            Assert.fail("Error while validating suggestions are not visible: " + e.getMessage());
        }
    }
}
