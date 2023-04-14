package steam.framework;

import org.testng.Assert;
import steam.framework.elements.BaseElement;

public abstract class BasePage {


//
//    public BasePage(By xpathElement) {
//        Assert.assertTrue(driver.findElement(xpathElement).isDisplayed(), "Incorrect page is opened");
//    }

//    public BasePage(String expectedLabel, Label label) {
//        softAssert.assertTrue(label.getText().contains(expectedLabel), "Incorrect page is opened, expected page is -   " + expectedLabel);
//    }

    public BasePage() {

    }

    protected  <T extends BaseElement> void checkPageElementIsDisplayed(T element) {
        Assert.assertTrue(element.checkIsDisplayedWithWait(), "Incorrect page is opened");
    }

}
