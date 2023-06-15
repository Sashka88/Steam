package steam.page;

import framework.elements.Button;
import framework.elements.DropdownMenu;
import org.openqa.selenium.By;
import steam.page.components.NavigationMenu;

public class AgeCheckPage extends BaseSteamPage {
    private NavigationMenu navigationMenu;
    private DropdownMenu dmForAge = new DropdownMenu(By.xpath("//select[@id = 'ageYear']"));
    private DropdownMenu dmCurrentYear = new DropdownMenu(By.xpath("//option[@selected='true']"));
    private String xpathAccessYear = "//select[@id = 'ageYear']/option[@value='%s']";
    private Button btnViewPage = new Button(By.xpath("//a[@id='view_product_page_btn']"));

    public AgeCheckPage() {
        super();
        this.navigationMenu = new NavigationMenu();
        checkPageElementIsDisplayed(dmForAge);
    }

    public AgeCheckPage selectDate(){
        dmForAge.click();
        DropdownMenu dmAccessYear = new DropdownMenu (By.xpath(String.format(xpathAccessYear, (Integer.parseInt(dmCurrentYear.getText())) - 18)));
        dmAccessYear.click();
        return this;
    }

    public AgeCheckPage submitAge(){
        btnViewPage.click();
        return this;
    }
}
