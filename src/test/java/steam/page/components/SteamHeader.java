package steam.page.components;

import framework.BaseTest;
import framework.elements.BaseElement;
import framework.elements.Button;
import framework.elements.DropdownMenu;
import org.openqa.selenium.By;

public class SteamHeader {
    private Button btnInstallSteam = new Button(By.xpath("//div[@class='header_installsteam_btn header_installsteam_btn_green']"));
    private DropdownMenu drpLanguageMenu = new DropdownMenu(By.xpath("//span[@id = 'language_pulldown']"));
    private String btnLanguageOption = "//a[contains(@class, 'popup_menu_item tight')][contains(text(), '%s')]";

    public SteamHeader() {
        checkHeaderElementIsDisplayed(btnInstallSteam);
    }

    private <T extends BaseElement> void checkHeaderElementIsDisplayed(T element) {
        BaseTest.softAssert.assertTrue(element.checkIsDisplayedWithWait(), "Steam Header isn't displayed");
    }

    public SteamHeader clickInstallSteam() {
        btnInstallSteam.click();
        return this;
    }

    public SteamHeader defineLanguage(String language) {
        drpLanguageMenu.click();
        DropdownMenu dmLanguage = new DropdownMenu(By.xpath(String.format(btnLanguageOption, language)));
        switch (String.valueOf(dmLanguage.checkIsDisplayedWithoutWait())) {
            case "true" -> dmLanguage.click();
            case "false" -> drpLanguageMenu.click();
        }
        return this;
    }
}
