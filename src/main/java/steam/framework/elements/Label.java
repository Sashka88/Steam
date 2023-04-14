package steam.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Label extends BaseElement {
    public Label(By xpath) {
        super(xpath);
    }

    @Override
    public List<Label> returnElementsList() {
        List<Label> labelList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            labelList.add(
                    new Label(By.xpath(getXPathToElement(webElementFromList))));
        }
        return labelList;
    }
}
