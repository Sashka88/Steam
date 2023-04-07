package steam.framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement {
    String btnLanguage = "//span[@class='pulldown global_action_link']";

    public Button(By xpath) {
        super(xpath);
    }
}
