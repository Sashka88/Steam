package steam.page;

import framework.elements.Label;
import org.openqa.selenium.By;

public class HomePage extends BaseSteamPage {
    private NavigationMenu navigationMenu;
    private Label lblHomePage = new Label(By.xpath("//div[@class = 'home_page_col_wrapper']"));

    public HomePage() {
        super();
        this.navigationMenu = new NavigationMenu();
        checkPageElementIsDisplayed(lblHomePage);
    }

    public HomePage defineLanguageOnHomePage(String language) {
        steamHeader.defineLanguage(language);
        return this;
    }

    public HomePage navigateMenuOnHomePage(String menuItem, String subMenu) {
        navigationMenu.navigateMenu(menuItem).clickSubMenuItem(subMenu);
        return this;
    }
}
