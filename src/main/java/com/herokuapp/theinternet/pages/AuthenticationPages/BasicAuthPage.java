package com.herokuapp.theinternet.pages.AuthenticationPages;

import com.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class BasicAuthPage extends BasePageObject {

    private By successMessage = By.xpath("//*[@id='content']//p");

    public BasicAuthPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Authenticate in browser alert popup */
    public BasicAuthPage loginWith(String login, String password) {
        log.info("Basic auth via browser alert");
        authenticateAlertPopup(login, password, false);
        switchToRecentlyOpenedTab();
        return new BasicAuthPage(driver, log);
    }

    /** Get a success message if the credentials are correct */
    public String getSuccessMessage() {
        log.info("Getting successful login message");
        return getTextNode(find(successMessage));
    }
}
