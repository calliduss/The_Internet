package com.herokuapp.herokuapp.theinternet.pages.LayoutPages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShiftingContentPage extends BasePageObject {

    private By menuElementLink = By.linkText("Example 1: Menu Element");
    private By imageLink = By.linkText("Example 2: An image");
    private By listLink = By.linkText("Example 3: List");

    public ShiftingContentPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Click on link with a shifting menu */
    public ShiftingMenuElementPage openShiftingMenuElementPage() {
        log.info("Opening the page with a shifting menu item");
        click(menuElementLink);
        return new ShiftingMenuElementPage(driver, log);
    }

    /** Click on link with a shifting image */
    public ShiftingImagePage openShiftingImagePage() {
        log.info("Opening the page with a shifting image");
        click(imageLink);
        return new ShiftingImagePage(driver, log);
    }

    /** Click on link with a shifting list */
    public ShiftingListPage openShiftingListPage() {
        log.info("Opening the page with a shifting list items");
        click(listLink);
        return new ShiftingListPage(driver, log);
    }
}