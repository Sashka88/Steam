package steam.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Button extends BaseElement {

    public Button(By xpath) {
        super(xpath);
    }

    @Override
    public List<Button> returnElementsList() {
        List<Button> buttonsList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            buttonsList.add(
                    new Button(By.xpath(getXPathToElement(webElementFromList))));
        }
        return buttonsList;
    }
}
