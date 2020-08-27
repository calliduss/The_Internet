package com.herokuapp.herokuapp.theinternet.pages.LayoutPages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShiftingListPage extends BasePageObject {

    private By listOfPhrases = By.xpath("//*[@class='large-6 columns large-centered']");

    public ShiftingListPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public String getListOfQuotes() {
        log.info("Getting a list of quotes");
        return find(listOfPhrases).getText();
    }
}