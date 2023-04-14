package steam.framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TextBox extends BaseElement {

    public TextBox(By xpath) {
        super(xpath);
    }

    @Override
    public List<TextBox> returnElementsList() {
        List<TextBox> textBoxList = new ArrayList<>();
        getPresentElements();
        for (WebElement webElementFromList : webElementsList) {
            textBoxList.add(
                    new TextBox(By.xpath(getXPathToElement(webElementFromList))));
        }
        return textBoxList;
    }
}
