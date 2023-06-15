package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DropdownMenu extends BaseElement {
        public DropdownMenu(By xpath) {
            super(xpath);
        }

        @Override
        public List<framework.elements.DropdownMenu> getElementsList() {
            List<framework.elements.DropdownMenu> dropdownMenuList = new ArrayList<>();
            getPresentElements();
            for (WebElement webElementFromList : webElementsList) {
                dropdownMenuList.add(
                        new framework.elements.DropdownMenu(By.xpath(getXpathForElements(webElementFromList))));
            }
            return dropdownMenuList;
        }
    }
