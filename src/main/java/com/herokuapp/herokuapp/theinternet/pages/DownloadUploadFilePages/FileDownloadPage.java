package com.herokuapp.herokuapp.theinternet.pages.DownloadUploadFilePages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileDownloadPage extends BasePageObject {

    private String fileLink = "*#*";

    public FileDownloadPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Download a particular file from the list */
    public void downloadFile(String fileName) {
        log.info(String.format("Downloading a file with the specified name: %s", fileName));
        By link = By.linkText(fileLink.replace("*#*", fileName));
        click(link);
    }
}