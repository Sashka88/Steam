package steam.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static steam.framework.DriverFactory.getWebDriver;

public class Browser {
    public static WebDriver driver;
    public static JavascriptExecutor executor;

    public Browser(){
        Browser.driver = getWebDriver();
        Browser.executor = (JavascriptExecutor)driver;
}

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void navigatePage(String url) {
        driver.get(url);
    }

    public void quitBrowser() {
        driver.quit();
    }
}
