package steam.page;

import org.openqa.selenium.By;
import steam.framework.elements.BaseElement;
import steam.framework.elements.Button;
import steam.framework.elements.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class ActionPage extends BaseSteamPage {
    public static int finalMaxSale;
    private Label lblPage = new Label(By.xpath("//div[contains(@class, 'ContentHubTitle')][contains(text(), 'Action')]"));
    private Button btnNext = new Button(By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel_sliderBody_gtnxY SliderBody']//button[@aria-label='next']"));
    private Label lblGamesSales = new Label(By.xpath("//div[@id='SaleSection_61486']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]"));
    private List<Integer> saleList = new ArrayList<>();
    private List<Label> saleLabelsList = new ArrayList<>();
    private List<Integer> indexesOfMaxSale = new ArrayList<>();
    private String xpathGameButton = "/ancestor::div[contains(@class,'contenthubshared_SpecialsItem_3GfHV')]";

    public ActionPage() {
        super();
        checkPageElementIsDisplayed(lblPage);
    }


    public ActionPage getSalesValues() {
        do {
            addToArrays(lblGamesSales.returnElementsList());
            btnNext.click();
        } while (saleLabelsList.size() < saleList.size());
        findMaxSale();
        clickGame();
        return this;
    }

    private void addToArrays(List<Label> labels) {
        while (saleList.size() < labels.size()) {
            saleList.add(0);
        }
        for (Label label : labels) {
            if (!Objects.equals(label.getText(), "") && saleList.get(labels.indexOf(label)) == 0) {
                saleList.set(
                        labels.indexOf(label),
                        Integer.valueOf(label.getText().substring(1,  2)));
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
            String xpath = saleLabelsList.get(indexOfMaxSale).getXpathAsString() + xpathGameButton;
            new Button(By.xpath(xpath)).clickWithoutWait(btnNext);
        }
         else if (indexesOfMaxSale.size() == 1) {
             String xpath = saleLabelsList.get(indexesOfMaxSale.get(0)).getXpathAsString() + xpathGameButton;
            new Button(By.xpath(xpath)).clickWithoutWait(btnNext);
        }
    }
}