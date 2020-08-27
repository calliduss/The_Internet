package com.herokuapp.herokuapp.theinternet.base;

import com.testautomationguru.ocular.Ocular;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static com.herokuapp.herokuapp.theinternet.base.BrowserDriverFactory.setContext;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    private static ITestContext context;

    @Parameters({"browser", "chromeProfile", "deviceName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, @Optional String profile,
                      @Optional String deviceName, ITestContext context) {

        // Specifying the path for the mockups and for the layout comparison result
        Ocular.config()
                .resultPath(Paths.get("ComparisonResult"))
                .snapshotPath(Paths.get("Mockups"));
//                .saveSnapshot(true); //or false??? remove after the first run

        String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        ConfigFactory.setProperty("env", Servers.PROD.getServer());
        Environment cfg = ConfigFactory.create(Environment.class);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);

        if (profile != null) {
            driver = factory.createChromeWithProfile(profile);
        } else if (deviceName != null) {
            driver = factory.createChromeWithMobileEmulation(deviceName);
        } else {
            driver = factory.createDriver();
        }

        driver.manage().window().maximize();
        this.context = setContext(context, driver);

        driver.get(cfg.url());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        driver.quit();
    }
}