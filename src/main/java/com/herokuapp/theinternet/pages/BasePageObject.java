package com.herokuapp.theinternet.pages;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BasePageObject {

    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Sleep
     */
    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find an element using the given locator
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Find all elements using the given locator
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Click on an element with given locator when its visible
     */
    protected void click(By locator) {
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    /** Perform a right-mouse click on an element */
    protected void rightMouseClick(By locator) {
        Actions action = new Actions(driver).contextClick(find((locator)));
        action.build().perform();
    }

    /**
     * Get the full path to a file in local storage
     */
    protected String getFilePath(String fileName) {
        String filePath = FilenameUtils.getFullPath(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(fileName))
                .getPath());

        if (filePath == null) {
            throw new WebDriverException("File not found: " + filePath);
        }

        return filePath;
    }

    /**
     * Type the given text into an an element with the given locator
     */
    protected void type(String text, By locator) {
        waitForVisibilityOf(locator, 5);
//        find(locator).clear();
        find(locator).sendKeys(text);
    }

    /** Press Key on locator */
    protected void pressKey(By locator, Keys key) {
        find(locator).sendKeys(key);
    }

    /**
     * Get an alert message
     */
    public String getBrowserAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    /**
     * Select an option with the given locator
     */
    protected void selectFromDropdownList(String option, By locator) {
        WebElement dropdownElement = find(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(option);
    }

    /** Drag 'from' element to 'to' element */
    protected void performDragAndDrop(By from, By to) {
        WebElement draggable = driver.findElement(from);
        WebElement target = driver.findElement(to);
        new Actions(driver).dragAndDrop(draggable, target).perform();
    }

    /** Drag a file to an upload area */
    protected void dropFile(String filePath, WebElement target, int offsetX, int offsetY) {
        if (filePath == null) {
            throw new WebDriverException("File not found: " + filePath);
        }

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String JS_DROP_FILE =
                "var target = arguments[0]," +
                        "    offsetX = arguments[1]," +
                        "    offsetY = arguments[2]," +
                        "    document = target.ownerDocument || document," +
                        "    window = document.defaultView || window;" +
                        "" +
                        "var input = document.createElement('INPUT');" +
                        "input.type = 'file';" +
                        "input.style.display = 'none';" +
                        "input.onchange = function () {" +
                        "  var rect = target.getBoundingClientRect()," +
                        "      x = rect.left + (offsetX || (rect.width >> 1))," +
                        "      y = rect.top + (offsetY || (rect.height >> 1))," +
                        "      dataTransfer = { files: this.files };" +
                        "" +
                        "  ['dragenter', 'dragover', 'drop'].forEach(function (name) {" +
                        "    var evt = document.createEvent('MouseEvent');" +
                        "    evt.initMouseEvent(name, !0, !0, window, 0, 0, 0, x, y, !1, !1, !1, !1, 0, null);" +
                        "    evt.dataTransfer = dataTransfer;" +
                        "    target.dispatchEvent(evt);" +
                        "  });" +
                        "" +
                        "  setTimeout(function () { document.body.removeChild(input); }, 25);" +
                        "};" +
                        "document.body.appendChild(input);" +
                        "return input;";

        WebElement input = (WebElement) jse.executeScript(JS_DROP_FILE, target, offsetX, offsetY);
        input.sendKeys(filePath);
        wait.until(ExpectedConditions.stalenessOf(input));
    }

    /** Perform mouse hover over an element */
    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /** Wait for alert present and then switch to it */
    protected Alert switchToAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    /**
     * Switch to a bookmark with a given name
     */
    protected void switchToWindowWithTitle(String expectedTitle) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> windowsIterator = allWindows.iterator();

//        while (windowsIterator.hasNext()) {
//            String windowHandle = windowsIterator.next();
//            if (!windowHandle.equals(currentWindow)) {
//                driver.switchTo().window(windowHandle);
//                if (driver.getTitle().equals(expectedTitle)) {
//                    break;
//                }
//            }
//        }

        while (windowsIterator.hasNext()) {
            String windowHandle = windowsIterator.next();
            if (!windowHandle.equals(currentWindow)) {
                if (driver.getTitle().equals(expectedTitle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
        }
    }

    /**
     * Wait for specific ExpectedCondition for the given amount of time in seconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    /**
     * Wait for the given number of seconds for an element with the given locator to be visible
     * on the page
     */
    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }
}
