package com.herokuapp.herokuapp.theinternet.pages.FramePages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BasePageObject {

    private String frameTop = "frame-top";
    private String frameBottom = "frame-bottom";
    private String frameLeft = "frame-left";
    private String frameMiddle = "frame-middle";
    private String frameRight = "frame-right";
    private By textInFrame = By.tagName("body");

    public NestedFramesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Find nested iframes and switch between them */
    public void switchBetweenNestedFrames() {
        log.info("Switching between nested iframes inside top iframe");
        driver.switchTo().frame(frameTop);
        driver.switchTo().frame(frameLeft);
        driver.switchTo().frame(frameMiddle);
        driver.switchTo().frame(frameRight);
    }

    /** Find iframe on the bottom of the page and switch to it */
    public void switchToBottomFrame() {
        log.info("Switching to the bottom iframe");
        driver.switchTo().frame(frameBottom);
    }

    /** Get a text on top of an iframe */
    public String getTextInsideFrame() {
        log.info("Getting a text inside an iframe");
        return find(textInFrame).getText();
    }
}