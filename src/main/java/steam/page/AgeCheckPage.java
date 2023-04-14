package steam.page;

import org.openqa.selenium.By;
import steam.framework.elements.Button;

public class AgeCheckPage extends BaseSteamPage {
    private Button btnForAge = new Button(By.xpath("//select[@id = 'ageYear']"));
    private Button btnCurrentYear = new Button(By.xpath("//option[@selected='true']"));
    private String accessYear = "//select[@id = 'ageYear']/option[@value='%s']";
    private Button btnViewPage = new Button(By.xpath("//span[contains(text(), 'View Page')]"));

    public AgeCheckPage() {
        super();
        checkPageElementIsDisplayed(btnForAge);
    }

    public AgeCheckPage selectDate(){
        btnForAge.click();
        Button btnAccessYear = new Button (By.xpath(String.format(accessYear, (Integer.parseInt(btnCurrentYear.getText())) - 18)));
        btnAccessYear.click();
        return this;
    }

    public AgeCheckPage submitAge(){
        btnViewPage.click();
        return this;
    }
}
