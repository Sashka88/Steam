package steam.page;

import org.openqa.selenium.By;
import steam.framework.elements.Button;
import steam.framework.elements.Label;

public class MainMenu extends BaseSteamPage{
    private String btnMenu = "//a[contains(@class, 'pulldown_desktop')][contains(text(), '%s')]";
    private String btnSubMenu = "//a[@class = 'popup_menu_item' and contains(text(), '%s')]";
    private Label pageLocator = new Label(By.xpath("//div[@id='store_nav_area']"));
    public MainMenu() {
        super();
        checkPageElementIsDisplayed(pageLocator);
    }
    public MainMenu navigateMenu(String menuItem) {
        Button buttonMenu = new Button(By.xpath(String.format(btnMenu, menuItem)));
        buttonMenu.hoverElementAndClick();
        return this;
    }

    public MainMenu clickSubMenuItem(String subMenuItem) {
        Button buttonSubMenu = new Button(By.xpath(String.format(btnSubMenu, subMenuItem)));
        buttonSubMenu.hoverElementAndClick();
        return this;
    }
    }

