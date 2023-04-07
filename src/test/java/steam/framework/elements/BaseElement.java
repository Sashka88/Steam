package steam.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steam.framework.Browser;

import static steam.framework.BaseTest.browser;
import static steam.framework.Browser.driver;
import static steam.framework.PropertyReader.getProperty;

public abstract class BaseElement {
    protected static long WAIT_TIMEOUT_SECONDS = Long.parseLong(getProperty("config", "waitingDuration"));
    public By xpath;
    protected WebElement element;

    public BaseElement(By xpath) {
        this.xpath = xpath;
    }

    private WebElement findWebElement() {
        WebElement element = driver.findElement(xpath);
        return element;
    }

    public void click() {
        findWebElement().click();
    }

    public void hoverElement() {
        new Actions(driver).moveToElement(findWebElement()).perform();
    }
    public void hoverElementAndClick() {
        new Actions(driver).moveToElement(findWebElement()).click().perform();
    }


    public String checkIsDisplayed() {
        try {
            WebElement element = driver.findElement(xpath);
            return String.valueOf(element.isDisplayed());
        } catch (NoSuchElementException e) {
            return "false";
        }
    }
    public void waitUntilIsDisplayed(WebElement button) {
        new WebDriverWait(browser.driver, WAIT_TIMEOUT_SECONDS).until(condition -> button.isDisplayed());
    }

    public void waitUntilIsInvisibilityOfElement(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    public void waitUntilVisibilityOfAllElementsLocated(By xpath) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath));
    }
}

