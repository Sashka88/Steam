package steam.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.framework.BaseTest;
import steam.framework.Browser;
import steam.framework.services.PropertyLocalizationReader;
import steam.page.*;

import static steam.framework.PropertyReader.getProperty;

public class SteamProject extends BaseTest {

    @Parameters({"language"})
    @Test
    public void steamTest(String language) {
        PropertyLocalizationReader propertyLocalizationReader = new PropertyLocalizationReader("");
        new HomePage().defineLanguage();
        new MainMenu().navigateMenu("Categories")
                .clickSubMenuItem("Action");
        new ActionPage().getSalesValues();
        if (Browser.driver.getCurrentUrl().contains("/agecheck")){
            new AgeCheckPage().selectDate()
                    .submitAge();
        }
        new GamePage().clickInstallSteam();
    }
}
