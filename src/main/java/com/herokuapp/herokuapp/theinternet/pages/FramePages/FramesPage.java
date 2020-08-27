package com.herokuapp.herokuapp.theinternet.pages.FramePages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FramesPage extends BasePageObject {

    private By nestedFramesLink = By.linkText("Nested Frames");
    private By iFrameLink = By.linkText("iFrame");

    public FramesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public NestedFramesPage openNestedFramesPage() {
        log.info("Opening nested frames page");
        click(nestedFramesLink);
        return new NestedFramesPage(driver, log);
    }

    public WYSIWYGEditorPage openEditorPage() {
        log.info("Opening WYSIWYG editor page");
        click(iFrameLink);
        return new WYSIWYGEditorPage(driver, log);
    }
}
