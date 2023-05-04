package framework.elements;

import framework.browser.Browser;
import framework.JavaScript;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static framework.browser.Browser.driver;
import static framework.browser.Browser.executor;
import static framework.services.PropertyReader.getPropertyByFilename;

public abstract class BaseElement {
    protected long WAIT_TIMEOUT_SECONDS = Long.parseLong(getPropertyByFilename("config", "waitingDuration"));
    public By xpath;
    protected WebElement element;
    protected List<WebElement> webElementsList = new ArrayList<>();

    public BaseElement(By locator) {
        this.xpath = locator;
    }

    private void waitUntilPresenceOfElement(By xpath) {
        new WebDriverWait(Browser.driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    private void waitUntilElementToBeClickable(By xpath) {
        new WebDriverWait(Browser.driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(xpath));
    }

    private void waitUntilElementToBeVisible(By xpath) {
        new WebDriverWait(Browser.driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    private WebElement getPresentElement() {
        waitUntilPresenceOfElement(xpath);
        return element = driver.findElement(xpath);
    }

    private WebElement getVisibleElement() {
        getPresentElement();
        waitUntilElementToBeVisible(xpath);
        return element;
    }

    private WebElement getClickableElement() {
        getPresentElement();
        waitUntilElementToBeClickable(xpath);
        return element;
    }

    public void click() {
        getClickableElement().click();
    }

    public void hoverElementAndClick() {
        new Actions(driver).moveToElement(getVisibleElement()).click().perform();
    }

    public Boolean checkIsDisplayedWithoutWait() {
        try {
            WebElement element = driver.findElement(xpath);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public Boolean checkIsDisplayedWithWait() {
        getPresentElement();
        waitUntilElementToBeVisible(xpath);
        return element.isDisplayed();
        }

    protected abstract List<? extends BaseElement> getElementsList();

    public String getXpathForElements(WebElement element) {
        return (String) executor.executeScript(JavaScript.GET_ELEMENT_XPATH.getScript(), element);
    }

    protected void waitUntilPresentAll() {
        new WebDriverWait(Browser.driver, WAIT_TIMEOUT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
    }

    public void getPresentElements() {
        waitUntilPresentAll();
        webElementsList = driver.findElements(xpath);
    }

    public String getText() {
        return getPresentElement().getText();
    }

    public String getXpathAsString() {return String.valueOf(xpath).substring(String.valueOf(xpath).indexOf("/"));}

    public void clickOnSlider(Button next) {
        try {
            driver.findElement(xpath).click();
        } catch (InvalidSelectorException | ElementNotInteractableException e) {
            next.click();
            clickOnSlider(next);
        }
    }
}
