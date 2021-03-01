package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContextMenuPage extends BasePageObject {

    private By contextMenu = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Right click to open the context menu */
    public void openContextMenu() {
        log.info("Right-click on the context menu");
        rightMouseClick(contextMenu);
    }
}