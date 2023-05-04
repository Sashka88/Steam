package steam.test;

import framework.BaseTest;
import framework.services.PropertyReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.page.*;

public class SteamTest extends BaseTest {

    @Parameters({"language"})
    @Test
    public void steamTest(String language) {
        PropertyReader propertyReader = new PropertyReader(language);
        new HomePage()
                .defineLanguageOnHomePage(language)
                .navigateMenuOnHomePage(propertyReader.getLanguageProperty("menuItem"), propertyReader.getLanguageProperty("subMenu"));
        new ActionPage(propertyReader.getLanguageProperty("subMenu"))
                .getSalesValues();
        if (browser.checkIfUrlContains("/agecheck")) {
            new AgeCheckPage()
                    .selectDate()
                    .submitAge();
        }
        new GamePage()
                .clickInstallSteamOnGamePage();
        new InstallSteamPage()
                .clickInstallSteam()
                .downloadSteam();
    }
}
