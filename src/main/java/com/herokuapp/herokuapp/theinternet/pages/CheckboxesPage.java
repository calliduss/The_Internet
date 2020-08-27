package com.herokuapp.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePageObject {

    private By checboxes = By.xpath("//input[@type='checkbox']");

    public CheckboxesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Get list of all checkboxes and check if unchecked */
    public void selectAllCheckboxes() {
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checboxes);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    /**
     * Verify all available checkboxes are checked. If at least one unchecked,
     * return false
     */
    public boolean areAllCheckboxesChecked() {
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checboxes);
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
               return false;
            }
        }
        return true;
    }
}