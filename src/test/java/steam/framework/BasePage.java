package steam.framework;

import org.openqa.selenium.By;
import org.testng.Assert;

import static steam.framework.BrowserFactory.driver;

public abstract class BasePage {
    public BasePage(String expectedLabel, By Label) {
        Assert.assertTrue(driver.findElement(Label).getText().contains(expectedLabel),
                "Incorrect page is opened, expected page is - " + expectedLabel);
    }

    public BasePage(By xpathElement) {
        Assert.assertTrue(driver.findElement(xpathElement).isDisplayed(), "Incorrect page is opened");
    }
}
