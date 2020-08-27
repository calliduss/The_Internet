package com.herokuapp.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicLoadingPage extends BasePageObject {

    private By exampleLink = By.xpath("//*[@href='/dynamic_loading/2']");
    private By startButton = By.xpath("//*[@id='start']/button");
    private By finishText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Check http status code for an image */
    public void openExampleLink() {
        log.info("Clicking example link on the page");
        click(exampleLink);
    }

    /** Check http status code for an image */
    public void startCalculation() {
        log.info("Clicking on start button");
        click(startButton);
    }

    /** Check http status code for an image */
    public String getFinishMessage() {
//        waitForVisibilityOf(finishText, 5);
        log.info("Getting text after calculation");
        WebElement explicitWait = new WebDriverWait(driver, 6)
                .until(ExpectedConditions.presenceOfElementLocated(finishText));
        return find(finishText).getText();
    }
}