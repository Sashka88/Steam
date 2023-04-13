package steam.framework.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steam.framework.Browser;
import steam.framework.JavaScript;

import java.util.ArrayList;
import java.util.List;

import static steam.framework.Browser.driver;
import static steam.framework.Browser.executor;
import static steam.framework.PropertyReader.getProperty;

public abstract class BaseElement {
    protected static long WAIT_TIMEOUT_SECONDS = Long.parseLong(getProperty("config", "waitingDuration"));
    public By xpath;
    protected WebElement element;
    protected List<WebElement> webElementsList = new ArrayList<>();

    public BaseElement(By xpath) {
        this.xpath = xpath;
    }

    private WebElement findWebElement() {
        WebElement element = driver.findElement(xpath);
        return element;
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

    private WebElement getPresentElement() {
        waitUntilPresenceOfElement(xpath);
        return element = driver.findElement(xpath);
    }

    private WebElement getClickableElement() {
        getPresentElement();
        waitUntilElementToBeClickable(xpath);
        return element;
    }

    public void click() {
        getClickableElement().click();
    }

    public void hoverElement() {
        new Actions(driver).moveToElement(findWebElement()).perform();
    }

    public void hoverElementAndClick() {
        new Actions(driver).moveToElement(findWebElement()).click().perform();
    }


    public Boolean checkIsDisplayed() {
        try {
            WebElement element = driver.findElement(xpath);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected abstract List<? extends BaseElement> returnElementsList();

    public String getXPathToElement(WebElement element) {
        return (String)
                executor.executeScript(JavaScript.GET_ELEMENT_XPATH.getScript(), element);
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

    public void clickWithoutWait(Button next) {
        try {
//            driver.findElement(xpath).click();
            getPresentElement().click();
        } catch (InvalidSelectorException | ElementNotInteractableException e) {
            next.click();
            clickWithoutWait(next);
        }

    }
}