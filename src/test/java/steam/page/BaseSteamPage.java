package steam.page;

import org.openqa.selenium.By;
import steam.framework.BasePage;

public class BaseSteamPage extends BasePage {
    public BaseSteamPage(String expectedLabel, By Label) {
        super(expectedLabel, Label);
    }
    public BaseSteamPage(By xpathElement) {
        super(xpathElement);
    }
}
