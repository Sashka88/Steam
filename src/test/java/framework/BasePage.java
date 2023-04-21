package framework;

import framework.elements.BaseElement;
import org.testng.Assert;

public abstract class BasePage {

    public BasePage() {
    }

    protected  <T extends BaseElement> void checkPageElementIsDisplayed(T element) {
        Assert.assertTrue(element.checkIsDisplayedWithWait(), "Incorrect page is opened");
    }
}
