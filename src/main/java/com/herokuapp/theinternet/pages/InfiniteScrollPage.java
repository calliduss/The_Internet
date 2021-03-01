package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InfiniteScrollPage extends BasePageObject {

    public InfiniteScrollPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Method scrolls the page from top to bottom for a given amount of time */
    public void infiniteScroll(int seconds) {
        log.info(String.format("Scrolling the page for $s seconds", seconds));
        long currentTimeMillis = System.currentTimeMillis();
        long end = currentTimeMillis + seconds * 1000;
        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
            while (System.currentTimeMillis() < end) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(1000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}