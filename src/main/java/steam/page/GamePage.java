package steam.page;

import org.openqa.selenium.By;
import steam.framework.elements.Button;
import steam.framework.elements.Label;

import static steam.page.ActionPage.finalMaxSale;

public class GamePage extends BaseSteamPage {
    private Label lblGamePage = new Label (By.xpath("//div[@class='discount_pct'][contains(text(), '" + finalMaxSale + "')]"));
    private Button btnInstallSteam = new Button(By.xpath("//div[@class='header_installsteam_btn header_installsteam_btn_green']"));

    public GamePage() {
        super();
        checkPageElementIsDisplayed(lblGamePage);
    }

    public GamePage clickInstallSteam(){
        btnInstallSteam.click();
        return this;
    }


}
