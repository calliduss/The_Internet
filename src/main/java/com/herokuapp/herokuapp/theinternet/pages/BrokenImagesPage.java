package com.herokuapp.herokuapp.theinternet.pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrokenImagesPage extends BasePageObject {

    public BrokenImagesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By imagesLocator = By.xpath("//*[@class='example']/img");

    /** Get list of all images */
    public List<WebElement> getListOfImages() {
        log.info("Get list of all images");
        return driver.findElements(imagesLocator);
    }

    /** Check http status code for an image */
    public boolean isImageBroken(WebElement image) {
        log.info("check if an image is broken");
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(image.getAttribute("src"));
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}