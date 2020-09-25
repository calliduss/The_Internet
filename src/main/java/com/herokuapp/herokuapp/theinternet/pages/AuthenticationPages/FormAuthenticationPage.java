package com.herokuapp.herokuapp.theinternet.pages.AuthenticationPages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthenticationPage extends BasePageObject {

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//*[@type='submit']");
    private By errorMessage = By.id("data-alert");

    public FormAuthenticationPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Type credentials to login the secure area */
    public SecureAreaPage loginToSecureArea(String username, String password) {
        log.info("logging to secure area");
        type(username, usernameField);
        type(password, passwordField);
        click(loginButton);
        return new SecureAreaPage(driver, log);
    }

    /** Get an error message if the credentials are incorrect */
    public String getErrorMessage() {
        log.info("Getting an error message");
        return find(errorMessage).getText();
    }
}