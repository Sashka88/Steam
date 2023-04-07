package steam.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.framework.BaseTest;
import steam.page.ActionPage;
import steam.page.HomePage;
import steam.page.MainMenu;

public class SteamProject extends BaseTest {

    @Parameters({})
    @Test
    public void steamTest() {
        new HomePage().defineLanguage();
        new MainMenu().navigateMenu("Categories")
                .clickSubMenuItem("Action");
        new ActionPage().getSalesValues();
    }
}
