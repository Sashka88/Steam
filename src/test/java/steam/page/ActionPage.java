package steam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static steam.framework.BaseTest.browser;
import static steam.framework.BrowserFactory.driver;
import static steam.framework.BrowserFactory.getWebDriver;

public class ActionPage extends BaseSteamPage {
    private static By pageLocator = By.xpath("//div[contains(@class, 'ContentHubTitle')][contains(text(), 'Action')]");
    private String saleWidget = "//div[contains(@class, 'horizontalSlider')]//div[contains(@class, 'StoreSaleDiscountBox')]";
    private By btnNext = By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel_sliderBody_gtnxY SliderBody']//button[@aria-label='next']");

    public ActionPage() {
        super(pageLocator);
    }

    public void getSalesValues() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try{Thread.sleep(1000);}
        catch(Exception e){}
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        driver.findElement(btnNext).click();
        List<WebElement> btnSaleWidgets = driver.findElements(By.xpath(saleWidget));
        System.out.println(btnSaleWidgets.size());
        ArrayList<String> saleValues = new ArrayList<>();
        String percent = "%";

        for (WebElement btnSaleWidget : btnSaleWidgets) {
            String saleValue = btnSaleWidget.getText();
            saleValues.add(saleValue);
        }
        System.out.println(saleValues);
    }
    public static int maxSale(int[] sales) {
        int maxSale = sales[0];
        for (int sale : sales) {
            if(sale > maxSale){
                maxSale = sale;
            }
        }
        return maxSale;
    }
}


