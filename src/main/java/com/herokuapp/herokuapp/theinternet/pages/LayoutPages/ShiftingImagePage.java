package com.herokuapp.herokuapp.theinternet.pages.LayoutPages;

import com.herokuapp.herokuapp.theinternet.pages.BasePageObject;
import com.testautomationguru.ocular.Ocular;
import com.testautomationguru.ocular.comparator.OcularResult;
import com.testautomationguru.ocular.snapshot.Snap;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

@Snap("Image.png")
public class ShiftingImagePage extends BasePageObject {

    public ShiftingImagePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** Compare the current page with the reference layout */
    public OcularResult comparePageWithMockup() {
        log.info("Comparing a page with a mock-up");
        return Ocular.snapshot().from(this)
                .sample().using(driver)
                .compare();
    }
}