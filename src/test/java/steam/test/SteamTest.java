package steam.test;

import framework.services.PropertyLocalizationReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.page.*;

public class SteamTest extends BaseTest {

    @Parameters({"language"})
    @Test
    public void steamTest(String language) {
        PropertyLocalizationReader propertyLocalizationReader = new PropertyLocalizationReader(language);
        new HomePage()
                .defineLanguageOnHomePage(language)
                .navigateMenuOnHomePage(propertyLocalizationReader.getProperty("menuItem"), propertyLocalizationReader.getProperty("subMenu"));
        new ActionPage(propertyLocalizationReader.getProperty("subMenu"))
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
