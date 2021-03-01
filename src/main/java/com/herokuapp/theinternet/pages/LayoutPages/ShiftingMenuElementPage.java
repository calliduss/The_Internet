package com.herokuapp.theinternet.pages.LayoutPages;

import com.herokuapp.theinternet.pages.BasePageObject;
import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import com.testautomationguru.ocular.snapshot.Snap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Snap("Menu.png")
public class ShiftingMenuElementPage extends BasePageObject {

    private By galleryButton = By.xpath("//*[@href='/gallery/']");

    public ShiftingMenuElementPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Compare the current position of the elements with the reference layout */
    public OcularResult compareElementOnPageWithMockup() {
        log.info("Comparing the position of an element on a page with a mock-up");
        return Ocular.snapshot().from(this)     //read the @Snap value
                .sample().using(driver)         //take the current page screenshot
                .element(find(galleryButton))   //compare a specific element instead of a whole web page
                .compare();
    }
}