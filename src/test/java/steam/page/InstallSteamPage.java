package steam.page;

import framework.BaseTest;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class InstallSteamPage extends BaseSteamPage {
    private final Label lblInstallSteamPage = new Label(By.xpath("//div[@class='about_subtitle']"));
    private final Button btnInstallSteam = new Button(By.xpath("//div[@class='about_install win ']"));

    public InstallSteamPage() {
        super();
        checkPageElementIsDisplayed(lblInstallSteamPage);
    }

    public InstallSteamPage clickInstallSteam() {
        btnInstallSteam.click();
        return this;
    }

    public InstallSteamPage downloadSteam() {
        BaseTest.fileDownloader.waitForFileDownloaded();
        return this;
    }
}
