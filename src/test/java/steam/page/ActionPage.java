package steam.page;

import org.openqa.selenium.By;
import steam.framework.elements.Button;
import steam.framework.elements.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.openqa.selenium.By.xpath;

public class ActionPage extends BaseSteamPage {
    private static By pageLocator = xpath("//div[contains(@class, 'ContentHubTitle')][contains(text(), 'Action')]");
    private Button btnNext = new Button(By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel_sliderBody_gtnxY SliderBody']//button[@aria-label='next']"));
    private Label lblGamesSales = new Label(By.xpath("//div[@id='SaleSection_61486']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]"));
    private List<Integer> saleList = new ArrayList<>();
    private List<Label> saleLabelsList = new ArrayList<>();
    private List<Integer> indexesOfMaxSale = new ArrayList<>();
    private static int finalMaxSale;
    private String gameXpath = "/ancestor::div[contains(@class,'contenthubshared_SpecialsItem_3GfHV')]";



    public ActionPage() {
        super(pageLocator);
    }

    public void getSalesValues() {
        List<Label> gamesSalesList = lblGamesSales.returnElementsList();
        addToArrays(gamesSalesList);
        btnNext.click();
        addToArrays(lblGamesSales.returnElementsList());
        btnNext.click();
        addToArrays(lblGamesSales.returnElementsList());
        findMaxSale();
        clickGame();
    }

    private void addToArrays(List<Label> labels) {
        while (saleList.size() < labels.size()) {
            saleList.add(0);
        }
        for (Label label : labels) {
            if (!Objects.equals(label.getText(), "") && saleList.get(labels.indexOf(label)) == 0) {
                saleList.set(
                        labels.indexOf(label),
                        Integer.valueOf(label.getText().substring(label.getText().lastIndexOf("-") + 1, label.getText().length() - 1)));
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
//            saleLabelsList.get(indexOfMaxSale).clickWithoutWait(btnNext);
            System.out.println(saleLabelsList.get(indexOfMaxSale).xpath);
            System.out.println(saleLabelsList.get(indexOfMaxSale).xpath + gameXpath);
//            Button game = new Button(By.xpath(saleLabelsList.get(indexOfMaxSale).xpath + gameXpath));

            new Button(By.xpath(saleLabelsList.get(indexOfMaxSale).xpath + "/ancestor::div[contains(@class,'contenthubshared_SpecialsItem_3GfHV')]")).clickWithoutWait(btnNext);

            //div[contains(@class,'contenthubshared_SpecialsItem_3GfHV')]
//            System.out.println(saleLabelsList.get(indexOfMaxSale).xpath);
//            System.out.println(saleLabelsList.get(indexOfMaxSale).xpath + gameXpath);
////            game.clickWithoutWait(btnNext);
        } else if (indexesOfMaxSale.size() == 1) {
            Button game = new Button(By.xpath(saleLabelsList.get(indexesOfMaxSale.get(0)).xpath + gameXpath));
            game.clickWithoutWait(btnNext);
        }
    }
}