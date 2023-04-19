package framework.browser;

import steam.test.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        switch (System.getProperty("browser")) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-popup-blocking");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.addArguments("safebrowsing-disable-extension-blacklist");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", BaseTest.downloadPath);
                prefs.put("download.prompt_for_download", false);
                prefs.put("safebrowsing.enabled", true);
                prefs.put("download.directory_upgrade", true);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", BaseTest.downloadPath);
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip");
                driver = new FirefoxDriver(firefoxOptions);
            }
            default -> throw new RuntimeException("Incorrect Browser Name");
        }
        return driver;
    }
}
