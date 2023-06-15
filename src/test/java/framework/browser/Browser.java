package framework.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static framework.browser.DriverFactory.getWebDriver;

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

    public void rejectCookies (){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.id("rejectAllButton")).click();
    }

    public void quitBrowser() {
        driver.quit();
    }

    public boolean checkIfUrlContains(String urlPart) {
        return driver.getCurrentUrl().contains(urlPart);
    }
}
