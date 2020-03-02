package utils;

import static utils.Conditions.VISIBLE;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SimpleAPI {

    protected abstract WebDriver getDriver();

    protected void open(String url) {
        getDriver().get(url);
    }

    protected WebElement $(By locator) {
        return $(locator, VISIBLE);
    }

    protected WebElement $(String css) {
        return $(By.cssSelector(css));
    }

    protected WebElement $(By locator, Function<By, ExpectedCondition<WebElement>> condition) {
        return waitFor(condition.apply(locator));
    }

    protected WebElement $(By locator, Conditions condition) {
        return waitFor(condition.getCondition().apply(locator));
    }

    protected List<WebElement> $$(By locator) {
        return getDriver().findElements(locator);
    }

    protected List<WebElement> $$(String css) {
        return getDriver().findElements(By.cssSelector(css));
    }

    protected List<WebElement> $$(By locator, Function<By, ExpectedCondition<List<WebElement>>> condition) {
        return waitFor(condition.apply(locator));
    }

    protected List<WebElement> $$(By locator, int expNumberToBe) {
        return $$(locator, loc -> ExpectedConditions.numberOfElementsToBe(loc, expNumberToBe));
    }

    protected <T> T waitFor(ExpectedCondition<T> condition, long timeout) {
        return (new WebDriverWait(getDriver(), timeout)).until(condition);
    }

    protected <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, 10l);
    }
}
