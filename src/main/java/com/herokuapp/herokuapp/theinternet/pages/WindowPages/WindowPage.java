package com.herokuapp.herokuapp.theinternet.pages.WindowPages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowPage extends BasePageObject {

    private By clickHereLink = By.linkText("Click Here");

    public WindowPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Click 'Click Here' link to open new window */
    public void openNewWindow() {
        log.info("Clicking 'Click Here' link");
        click(clickHereLink);
    }

    /** Find page with title "New Window" and switch to it */
    public NewWindowPage switchToNewWindowPage() {
        log.info("Looking for 'New Window' page");
        switchToWindowWithTitle("New Window");
        return new NewWindowPage(driver, log);
    }
}
