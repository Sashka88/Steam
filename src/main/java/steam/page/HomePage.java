package steam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steam.framework.elements.Button;
import steam.framework.elements.Label;

import static steam.framework.Browser.driver;

public class HomePage extends BaseSteamPage {
    private Button btnLanguageMenu = new Button(By.xpath("//span[@id = 'language_pulldown']"));
    private String btnLanguage = "//a[contains(@class, 'popup_menu_item tight')][contains(text(), 'English')]";
    private Label pageLocator = new Label(By.xpath("//div[@class = 'home_page_col_wrapper']"));

    public HomePage() {
        super();
        checkPageElementIsDisplayed(pageLocator);
    }

    public HomePage defineLanguage() {
        btnLanguageMenu.click();
        Button language = new Button(By.xpath(String.format(btnLanguage)));
        switch (String.valueOf(language.checkIsDisplayed())) {
            case "true" -> {
                language.click();
            }
            case "false" -> {
                btnLanguageMenu.click();
            }
        }
        return this;
    }
}
