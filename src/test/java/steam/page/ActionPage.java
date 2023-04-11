package steam.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steam.framework.Browser;
import steam.framework.elements.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.openqa.selenium.By.xpath;
import static steam.framework.BaseTest.browser;
import static steam.framework.BrowserFactory.driver;
import static steam.framework.BrowserFactory.getWebDriver;
import static steam.framework.elements.BaseElement.waitUntilPresenceOfAllElements;

public class ActionPage extends BaseSteamPage {
    private static By pageLocator = xpath("//div[contains(@class, 'ContentHubTitle')][contains(text(), 'Action')]");
    private String saleWidget = "//div[@id='SaleSection_61486']/descendant::div[contains(@class,'salepreviewwidgets_StoreSaleDiscountBox')]";
    private final Button btnNext = new Button(By.xpath("//div[@id='SaleSection_61486']//div[@class='carousel_sliderBody_gtnxY SliderBody']//button[@aria-label='next']"));
    private List<Integer> saleList;
    private List<WebElement> saleWebElementList = new ArrayList<>();
    public ActionPage() {
        super(pageLocator);
    }

    public void getSalesValues() {
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        new WebDriverWait(Browser.driver,10)
//                .ignoring(StaleElementReferenceException.class)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(saleWidget)));
//        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        waitUntilPresenceOfAllElements(By.xpath(saleWidget));
        List<WebElement> btnSaleWidgets = driver.findElements(xpath(saleWidget));
        saleList = Stream.generate(() -> 0)
                .limit(btnSaleWidgets.size())
                .collect(Collectors.toCollection(ArrayList::new));
        addToArrays(btnSaleWidgets);
        btnNext.click();

        waitUntilPresenceOfAllElements(By.xpath(saleWidget));
        addToArrays(driver.findElements(By.xpath(saleWidget)));
        btnNext.click();

        waitUntilPresenceOfAllElements(By.xpath(saleWidget));
        addToArrays(driver.findElements(By.xpath(saleWidget)));

        System.out.println(saleList);

    }

        private void addToArrays(List<WebElement> webElements){
        for (WebElement webElement : webElements) {
            if(!Objects.equals(webElement.getText(), "") && saleList.get(webElements.indexOf(webElement)) == 0) {
                saleList.set(
                        webElements.indexOf(webElement),
                        Integer.valueOf(webElement.getText().substring(1, webElement.getText().length() - 1)));
                saleWebElementList.add(webElement);
            }
        }
//            String saleValue2 = btnSaleWidget2.getText();
//            Pattern pattern = Pattern.compile("\\d{2}(?=%)");
//            Matcher matcher = pattern.matcher(saleValue2);
//            while (matcher.find()) {
//                int saleValueInt = Integer.parseInt(saleValue2.substring(matcher.start(), matcher.end()));
//                saleValues2.add(saleValueInt);}
//        }
//        System.out.println(saleValues2);
    }
//    public static int maxSale(int[] saleList) {
//        Integer maxSale = saleList[0];
//        for (int sale : saleList) {
//            if(sale > maxSale) {
//                maxSale = sale;
//            }
//        }
//        return Arrays.stream(saleList).allMatch(maxSale);
//    }
}

//for (WebElement btnSaleWidget : btnSaleWidgets) {
//        String saleValue = btnSaleWidget.getText();
//        Pattern pattern = Pattern.compile("\\d{2}(?=%)");
//        Matcher matcher = pattern.matcher(saleValue);
//        while (matcher.find()) {
//        int saleValueInt = Integer.parseInt(saleValue.substring(matcher.start(), matcher.end()));
//        saleValues.add(saleValueInt);}
