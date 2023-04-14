package steam.framework;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import static steam.framework.PropertyReader.getProperty;

public class BaseTest {
    public static Browser browser;
    public static SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        browser = new Browser();
        softAssert = new SoftAssert();
        browser.maximizeWindow();
        browser.navigatePage(getProperty("config", "baseUrl"));
    }

//    @AfterMethod(alwaysRun = true)
//    public void teardown() {
//        browser.quitBrowser();
//    }
}
