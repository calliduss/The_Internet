package com.herokuapp.theinternet.pages.FramePages;

import com.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WYSIWYGEditorPage extends BasePageObject {

    private By editorFrame = By.id("mce_0_ifr");
    private By input = By.id("mce_0");

    public WYSIWYGEditorPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Type a text into editor */
    public void typeTextIntoEditorWindow(String text) {
        log.info("Entering a text into editor window");
        driver.switchTo().frame(find(editorFrame));
        type(text, input);
    }
}