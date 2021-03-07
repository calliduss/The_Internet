package com.herokuapp.theinternet;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.AuthenticationPages.BasicAuthPage;
import com.herokuapp.theinternet.pages.AuthenticationPages.FormAuthenticationPage;
import com.herokuapp.theinternet.pages.AuthenticationPages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class LoginTests extends BaseTest {

    @Test
    public void loginViaFormAuthentication() {
        // Arrange
        var login = "tomsmith";
        var password = "SuperSecretPassword!";
        var expectedSuccessfulLoginMessage = "You logged into a secure area!";
        WelcomePage welcomePage = new WelcomePage(driver, log);
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver, log);
        SecureAreaPage secureAreaPage = new SecureAreaPage(driver, log);

        // Act
        welcomePage.openFormAuthenticationPage();
        formAuthenticationPage.loginWith(login, password);

        // Assert
        assertThat(secureAreaPage.getSuccessMessage()).isEqualTo(expectedSuccessfulLoginMessage);
    }

    @Test(dataProvider = "isBasicAuthChromeExtensionEnabled")
    public void loginViaBrowserAlertWindow(boolean isChromeExtensionEnabled) {
        var login = "admin";
        var password = "admin";
        var expectedSuccessfulLoginMessage = "Congratulations! You must have the proper credentials.";
        WelcomePage welcomePage = new WelcomePage(driver, log);
        BasicAuthPage basicAuthPage = new BasicAuthPage(driver, log);

        welcomePage.openBasicAuthPage();
        if (!isChromeExtensionEnabled) { basicAuthPage.loginWith(login, password); }

        assertThat(basicAuthPage.getSuccessMessage()).isEqualTo(expectedSuccessfulLoginMessage);
    }
}