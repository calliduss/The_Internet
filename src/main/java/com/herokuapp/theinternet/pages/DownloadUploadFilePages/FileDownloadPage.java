package com.herokuapp.theinternet.pages.DownloadUploadFilePages;

import com.herokuapp.theinternet.pages.BasePageObject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FileDownloadPage extends BasePageObject {

    private By fileName = By.xpath("//*[@id='content']//a");
    private String fileLink = "*#*";

    public FileDownloadPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Collect list of files */
    public List<WebElement> getFileNames() {
        return findAll(fileName);
    }

    /** Download a particular file from the list */
    public void downloadFile(String fileName) {
        log.info(String.format("Downloading a file with the specified name: %s", fileName));
        By link = By.linkText(fileLink.replace("*#*", fileName));
        click(link);
        sleep(4000); // let browser download a file
    }
}