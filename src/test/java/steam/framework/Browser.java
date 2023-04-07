package steam.framework;

import org.openqa.selenium.WebDriver;

import static steam.framework.BrowserFactory.getWebDriver;

public class Browser {
    public static WebDriver driver;

    public Browser(){
    Browser.driver = getWebDriver();
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
