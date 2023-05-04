package steam.page.components;

import framework.BaseTest;
import framework.elements.BaseElement;
import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;

public class NavigationMenu {
    private String btnMenu = "//a[contains(@class, 'pulldown_desktop')][contains(text(), '%s')]";
    private String btnSubMenu = "//a[@class = 'popup_menu_item' and contains(text(), '%s')]";
    private Label lblNavigationMenu = new Label(By.xpath("//div[@id='store_nav_area']"));

    public NavigationMenu() {
        checkNavigationMenuIsDisplayed(lblNavigationMenu);
    }

    private <T extends BaseElement> void checkNavigationMenuIsDisplayed(T element) {
        BaseTest.softAssert.assertTrue(element.checkIsDisplayedWithWait(), "Navigation menu isn't displayed");
    }

    public NavigationMenu navigateMenu(String menuItem) {
        Button buttonMenu = new Button(By.xpath(String.format(btnMenu, menuItem)));
        buttonMenu.hoverElementAndClick();
        return this;
    }

    public NavigationMenu clickSubMenuItem(String subMenuItem) {
        Button buttonSubMenu = new Button(By.xpath(String.format(btnSubMenu, subMenuItem)));
        buttonSubMenu.hoverElementAndClick();
        return this;
    }
}
