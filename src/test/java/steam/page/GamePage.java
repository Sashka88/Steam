package steam.page;

import framework.elements.Label;
import org.openqa.selenium.By;

import static steam.page.ActionPage.finalMaxSale;

public class GamePage extends BaseSteamPage {
    private NavigationMenu navigationMenu;
    private Label lblGamePage = new Label(By.xpath("//div[@class='discount_pct'][contains(text(), '" + finalMaxSale + "')]"));

    public GamePage() {
        super();
        this.navigationMenu = new NavigationMenu();
        checkPageElementIsDisplayed(lblGamePage);
    }

    public GamePage clickInstallSteamOnGamePage() {
        steamHeader.clickInstallSteam();
        return this;
    }
}
