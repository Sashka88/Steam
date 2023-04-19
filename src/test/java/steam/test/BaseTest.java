package steam.test;

import framework.services.FileDownloader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import framework.browser.Browser;

import static framework.services.PropertyReader.getProperty;

public class BaseTest {
    public static Browser browser;
    public static SoftAssert softAssert;
    public static String downloadPath;
    public static FileDownloader fileDownloader;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        downloadPath = System.getProperty("user.dir") + "\\target";
        browser = new Browser();
        softAssert = new SoftAssert();
        browser.maximizeWindow();
        browser.navigatePage(getProperty("config", "baseUrl"));
        fileDownloader = new FileDownloader(getProperty("config","fileName"));
        fileDownloader.checkAndDeleteFile();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        browser.quitBrowser();
    }
}
