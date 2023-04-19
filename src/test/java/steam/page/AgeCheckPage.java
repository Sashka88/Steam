package steam.page;

import framework.elements.Button;
import org.openqa.selenium.By;
import steam.page.components.NavigationMenu;

public class AgeCheckPage extends BaseSteamPage {
    private NavigationMenu navigationMenu;
    private Button btnForAge = new Button(By.xpath("//select[@id = 'ageYear']"));
    private Button btnCurrentYear = new Button(By.xpath("//option[@selected='true']"));
    private String xpathAccessYear = "//select[@id = 'ageYear']/option[@value='%s']";
    private Button btnViewPage = new Button(By.xpath("//a[@id='view_product_page_btn']"));

    public AgeCheckPage() {
        super();
        this.navigationMenu = new NavigationMenu();
        checkPageElementIsDisplayed(btnForAge);
    }

    public AgeCheckPage selectDate(){
        btnForAge.click();
        Button btnAccessYear = new Button (By.xpath(String.format(xpathAccessYear, (Integer.parseInt(btnCurrentYear.getText())) - 18)));
        btnAccessYear.click();
        return this;
    }

    public AgeCheckPage submitAge(){
        btnViewPage.click();
        return this;
    }
}
