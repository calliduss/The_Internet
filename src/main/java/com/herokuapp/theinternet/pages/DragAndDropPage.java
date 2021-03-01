package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DragAndDropPage extends BasePageObject {

    private By columnA = By.id("column-a");
    private By columnB = By.id("column-b");

    public DragAndDropPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Drag A box and drop it on B box */
    public void dragAtoB() {
        log.info("Drag and drop A box on B box");
        performDragAndDrop(columnA, columnB);
    }

    /** Getting Column A Text */
    public String getColumnAText() {
        String text = driver.findElement(columnA).getText();
        log.info("Column A text is: " + text);
        return  text;
    }

    /** Getting Column B Text */
    public String getColumnBText() {
        String text = driver.findElement(columnB).getText();
        log.info("Column B text is: " + text);
        return  text;
    }
}