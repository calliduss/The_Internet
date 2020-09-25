package com.herokuapp.herokuapp.theinternet.pages.DownloadUploadFilePages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePageObject {

    private By chooseFileButton = By.id("file-upload");
    private By fileUploadButton = By.id("file-submit");
    private By uploadedFile = By.id("uploaded-files");
    private By dropArea = By.id("drag-drop-upload");

    public FileUploadPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Upload a specific file via button */
    public void uploadFile(String fileName) {
        log.info("Uploading a file");
        find(chooseFileButton).sendKeys(getFilePath(fileName));
        click(fileUploadButton);
    }

    /** Upload a specific file via drug&drop functionality */
    public void dragAndDropFileUpload(String fileName) {
        log.info("Dragging a file to upload");
        dropFile(getFilePath(fileName), find(dropArea), 0, 0);
    }

    /** Get uploaded file name */
    public String getUploadedFileText() {
        log.info("Getting the text of an uploaded file");
        return find(uploadedFile).getText();
    }
}