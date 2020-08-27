package com.herokuapp.herokuapp.theinternet.pages;

import com.herokuapp.herokuapp.theinternet.pages.AuthenticationPages.BasicAuthPage;
import com.herokuapp.herokuapp.theinternet.pages.AuthenticationPages.FormAuthenticationPage;
import com.herokuapp.herokuapp.theinternet.pages.DownloadUploadFilePages.FileDownloadPage;
import com.herokuapp.herokuapp.theinternet.pages.DownloadUploadFilePages.FileUploadPage;
import com.herokuapp.herokuapp.theinternet.pages.FramePages.FramesPage;
import com.herokuapp.herokuapp.theinternet.pages.LayoutPages.ShiftingContentPage;
import com.herokuapp.herokuapp.theinternet.pages.WindowPages.WindowPage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends BasePageObject {

    private By basicAuthLinkLocator = By.linkText("Basic Auth");
    private By brokenImagesLinkLocator = By.linkText("Broken Images");
    private By checkboxesLinkLocator = By.linkText("Checkboxes");
    private By contextMenuLinkLocator = By.linkText("Context Menu");
    private By dragAndDropLinkLocator = By.linkText("Drag and Drop");
    private By dropdownLinkLocator = By.linkText("Dropdown");
    private By dynamicLoadingLinkLocator = By.linkText("Dynamic Loading");
    private By fileDownloadLinkLocator = By.linkText("File Download");
    private By fileUploadLinkLocator = By.linkText("File Upload");
    private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
    private By framesLinkLocator = By.linkText("Frames");
    private By horizontalSliderLinkLocator = By.linkText("Horizontal Slider");
    private By hoversLinkLocator = By.linkText("Hovers");
    private By infiniteScrollLinkLocator = By.linkText("Infinite Scroll");
    private By javaScriptAlertsLinkLocator = By.linkText("JavaScript Alerts");
    private By multipleWindowsLinkLocator = By.linkText("Multiple Windows");
    private By shiftingContentLinkLocator = By.linkText("Shifting Content");

    public WelcomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open BasicAuthPage by clicking on 'Basic Auth' Link
     */
    public BasicAuthPage openBasicAuthPage() {
        log.info("Clicking 'Basic Auth' link on Welcome Page");
        click(basicAuthLinkLocator);
        return new BasicAuthPage(driver, log);
    }

    /**
     * Open BrokenImagesPage by clicking on 'Broken Images' Link
     */
    public BrokenImagesPage openBrokenImagesPage() {
        log.info("Clicking 'Broken Images' link on Welcome Page");
        click(brokenImagesLinkLocator);
        return new BrokenImagesPage(driver, log);
    }

    /**
     * Open CheckboxesPage by clicking on 'Checkboxes' Link
     */
    public CheckboxesPage openCheckboxesPage() {
        log.info("Clicking 'Checkboxes' link on Welcome Page");
        click(checkboxesLinkLocator);
        return new CheckboxesPage(driver, log);
    }

    /**
     * Open CheckboxesPage by clicking on 'Context Menu' Link
     */
    public ContextMenuPage openContextMenuPage() {
        log.info("Clicking 'Context Menu' link on Welcome Page");
        click(contextMenuLinkLocator);
        return new ContextMenuPage(driver, log);
    }

    /**
     * Open DragAndDropPage by clicking on 'Drag and Drop' Link
     */
    public DragAndDropPage openDragAndDropPage() {
        log.info("Clicking 'Drag and Drop' link on Welcome Page");
        click(dragAndDropLinkLocator);
        return new DragAndDropPage(driver, log);
    }

    /**
     * Open DropdownPage by clicking on 'Dropdown' Link
     */
    public DropdownPage openDropdownPage() {
        log.info("Clicking 'Dropdown' link on Welcome Page");
        click(dropdownLinkLocator);
        return new DropdownPage(driver, log);
    }

    /**
     * Open DynamicLoadingPage by clicking on 'Dynamic Loading' Link
     */
    public DynamicLoadingPage openDynamicLoadingPage() {
        log.info("Clicking 'Dynamic Loading' link on Welcome Page");
        click(dynamicLoadingLinkLocator);
        return new DynamicLoadingPage(driver, log);
    }

    /**
     * Open FileDownloadPage by clicking on 'File Download' Link
     */
    public FileDownloadPage openFileDownloadPage() {
        log.info("Clicking 'File Download' link on Welcome Page");
        click(fileDownloadLinkLocator);
        return new FileDownloadPage(driver, log);
    }

    /** Open FileUploadPage by clicking on 'File Upload' Link */
    public FileUploadPage openFileUploadPage() {
        log.info("Clicking 'File Upload' link on Welcome Page");
        click(fileUploadLinkLocator);
        return new FileUploadPage(driver, log);
    }

    /**
     * Open FormAuthenticationPage by clicking on 'Form Authentication' Link
     */
    public FormAuthenticationPage openFormAuthenticationPage() {
        log.info("Clicking 'Form Authentication' link on Welcome Page");
        click(formAuthenticationLinkLocator);
        return new FormAuthenticationPage(driver, log);
    }

    /**
     * Open FramesPage by clicking on 'Frames' Link
     */
    public FramesPage openFramesPage() {
        log.info("Clicking 'Frames' link on Welcome Page");
        click(framesLinkLocator);
        return new FramesPage(driver, log);
    }

    /**
     * Open HorizontalSliderPage by clicking on 'Horizontal Slider' Link
     */
    public HorizontalSliderPage openHorizontalSliderPage() {
        log.info("Clicking 'Horizontal Slider' link on Welcome Page");
        click(horizontalSliderLinkLocator);
        return new HorizontalSliderPage(driver, log);
    }

    /**
     * Open HoversPage by clicking on 'Hovers' Link
     */
    public HoversPage openHoversPage() {
        log.info("Clicking 'Hovers' link on Welcome Page");
        click(hoversLinkLocator);
        return new HoversPage(driver, log);
    }

    /**
     * Open InfiniteScrollPage by clicking on 'Infinite Scroll' Link
     */
    public InfiniteScrollPage openInfiniteScrollPage() {
        log.info("Clicking 'Infinite Scroll' link on Welcome Page");
        click(infiniteScrollLinkLocator);
        return new InfiniteScrollPage(driver, log);
    }

    /**
     * Open JavaScriptAlertsPage by clicking on 'JavaScript Alerts' Link
     */
    public JavaScriptAlertsPage openJavaScriptAlertsPage() {
        log.info("Clicking 'JavaScript Alerts' link on Welcome Page");
        click(javaScriptAlertsLinkLocator);
        return new JavaScriptAlertsPage(driver, log);
    }

    /**
     * Open MultipleWindowsPage by clicking on 'Multiple Windows' Link
     */
    public WindowPage openMultipleWindowsPage() {
        log.info("Clicking 'Multiple Windows' link on Welcome Page");
        click(multipleWindowsLinkLocator);
        return new WindowPage(driver, log);
    }

    /**
     * Open ShiftingContentPage by clicking on 'Shifting Content' Link
     */
    public ShiftingContentPage openShiftingContentPage() {
        log.info("Clicking 'Shifting Content' link on Welcome Page");
        click(shiftingContentLinkLocator);
        return new ShiftingContentPage(driver, log);
    }
}
