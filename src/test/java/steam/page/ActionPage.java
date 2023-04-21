package steam.page;

import framework.elements.Button;
import framework.elements.Label;
import org.openqa.selenium.By;
import steam.page.components.NavigationMenu;

import java.util.*;

public class ActionPage extends BaseSteamPage {
    public static int finalMaxSale;
    private NavigationMenu navigationMenu;
    private String xpathPageLabel = "//div[contains(@class, 'ContentHubTitle')][contains(text(), '%s')]";
    private Button btnNext = new Button(By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel_sliderBody_gtnxY SliderBody']//button[@aria-label='next']"));
    private Label lblGamesSales = new Label(By.xpath("//div[@id='SaleSection_61486']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]"));
    private List<Integer> saleList = new ArrayList<>();
    private List<Label> saleLabelsList = new ArrayList<>();
    private List<Integer> indexesOfMaxSale = new ArrayList<>();
    private String xpathGameButton = "/ancestor::div[contains(@class,'contenthubshared_SpecialsItem_3GfHV')]";

    public ActionPage(String subMenu) {
        super();
        this.navigationMenu = new NavigationMenu();
        checkPageElementIsDisplayed(new Label(By.xpath(String.format(xpathPageLabel, subMenu))));
    }

    public ActionPage getSalesValues() {
        List<Label> lblGamesSalesValue = lblGamesSales.getElementsList();
        for(int i = 0; i < 3; i++) {
            addToArrays(lblGamesSalesValue);
            btnNext.click();
        }
        findMaxSale();
        clickGame();
        return this;
    }

    private void addToArrays(List<Label> labels) {
        for(int i = 0; i < labels.size(); i++) {
            saleList.add(0);
        }
        for (Label label : labels) {
            if (!label.getText().isEmpty()) {
                saleList.set(
                        labels.indexOf(label),
                        Integer.valueOf(label.getText().substring(1, 3)));
                saleLabelsList.add(label);
            }
        }
    }

    public void findMaxSale() {
        finalMaxSale = saleList.get(0);
        for (int i = 0; i < saleList.size(); i++) {
            if (saleList.get(i) > finalMaxSale) {
                finalMaxSale = saleList.get(i);
                indexesOfMaxSale.clear();
                indexesOfMaxSale.add(i);
            } else if (saleList.get(i) == finalMaxSale) {
                indexesOfMaxSale.add(i);
            }
        }
    }

    private void clickGame() {
        if (indexesOfMaxSale.size() > 1) {
            int indexOfMaxSale = indexesOfMaxSale.get(new Random().nextInt(indexesOfMaxSale.size()));
            String xpath = saleLabelsList.get(indexOfMaxSale).getXpathAsString()+ xpathGameButton;
            new Button(By.xpath(xpath)).clickWithoutWait(btnNext);
        }
         else if (indexesOfMaxSale.size() == 1) {
             String xpath = saleLabelsList.get(indexesOfMaxSale.get(0)).getXpathAsString() + xpathGameButton;
            new Button(By.xpath(xpath)).clickWithoutWait(btnNext);
        }
    }
}
