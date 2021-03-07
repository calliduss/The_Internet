package com.herokuapp.theinternet.pages.AuthenticationPages;

import com.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {

    private By successMessage = By.id("flash");
    private By logoutButton = By.xpath("//*[@href='/logout']");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Click the logout button */
    public FormAuthenticationPage logout() {
        log.info("Logging out the secure area");
        click(logoutButton);
        return new FormAuthenticationPage(driver, log);
    }

    /** Get a success message if the credentials are correct */
    public String getSuccessMessage() {
        log.info("Getting successful login message");
        return getTextNode(find(successMessage));
    }
}