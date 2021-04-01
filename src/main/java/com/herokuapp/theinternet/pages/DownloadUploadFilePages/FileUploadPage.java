package com.herokuapp.theinternet.pages.DownloadUploadFilePages;

import com.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePageObject {

    private By chooseFileButton = By.id("file-upload");
    private By fileUploadButton = By.id("file-submit");
    private By uploadedFile = By.id("uploaded-files");
    private By dropArea = By.id("drag-drop-upload");
    private By droppedFileName = By.xpath("//*[@id='drag-drop-upload']//*[@class='dz-filename']/span");

    public FileUploadPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Upload a specific file via button */
    public void uploadFile(String filePath) {
        log.info("Uploading a file");

        // Shouldn't click on the [Browse] button, it will trigger an OS level dialogue box and effectively stop a test dead
        // Instead we can use sendKeys method:
        type(filePath, chooseFileButton);
        click(fileUploadButton);
    }

    /** Upload a specific file via drug&drop functionality */
    public void dragAndDropFileUpload(String filePath) {
        log.info("Dragging a file to upload");
        dropFile(filePath, find(dropArea), 0, 0);
    }

    /** Get uploaded file name */
    public String getUploadedFileText() {
        log.info("Getting the text of an uploaded file");
        try {
            return find(uploadedFile).getText();
        } catch (TimeoutException | NoSuchElementException e) {
            return find(droppedFileName).getText();
        }
    }
}