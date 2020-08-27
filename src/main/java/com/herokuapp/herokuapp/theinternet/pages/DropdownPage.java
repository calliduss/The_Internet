package com.herokuapp.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropdownPage extends BasePageObject {

    private By dropdownList = By.id("dropdown");
    private By selectedOption = By.xpath("//*[@selected='selected']");
    private String option = "//select/option[contains(text(), '*#*')]";

    public DropdownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Method selects given option from dropdown */
    public void selectOption(String optionToSelect) {
        log.info("Select option " + optionToSelect + " from dropdown");
        By itemInDropdownList = By.xpath(option.replace("*#*", optionToSelect));
        click(dropdownList);
        click(itemInDropdownList);
    }

    /** Method returns selected option in dropdown as a string */
    public String getSelectedOption() {
        String selected = find(selectedOption).getText();
        log.info("Selected option is " + selected);
        return selected;
    }
}