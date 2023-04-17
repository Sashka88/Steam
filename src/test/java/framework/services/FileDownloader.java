package framework.services;

import framework.BaseTest;
import framework.browser.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import java.io.File;
import java.time.Duration;

import static framework.services.PropertyReader.getProperty;

public class FileDownloader {
    private File file;
    protected long WAIT_DOWNLOAD_SECONDS = Long.parseLong(getProperty("config", "waitingDuration"));
    protected long RETRY_DOWNLOAD_SECONDS = Long.parseLong(getProperty("config", "waitingRetry"));

    public FileDownloader(String fileName) {
        this.file = new File(BaseTest.downloadPath + "\\" + fileName) ;
    }

    public void checkAndDeleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    public void waitForFileDownloaded() {
        FluentWait<WebDriver> wait = new FluentWait<>(Browser.driver)
                .withTimeout(Duration.ofSeconds(WAIT_DOWNLOAD_SECONDS))
                .pollingEvery(Duration.ofMillis(RETRY_DOWNLOAD_SECONDS));
        wait.until((webDriver) -> file.canRead());
        Assert.assertTrue(file.canRead(), "File is not downloaded");
    }
}
