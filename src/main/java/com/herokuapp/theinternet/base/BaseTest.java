package com.herokuapp.theinternet.base;

import com.testautomationguru.ocular.Ocular;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.nio.file.Paths;

import static com.herokuapp.theinternet.base.BrowserDriverFactory.setContext;

public class BaseTest {

    public TestUtilities testUtilities;
    public boolean hasBasicAuthChromeExtension;

    protected RemoteWebDriver driver;
    protected Logger log;

    private static ITestContext context;

    @Parameters({"browser", "chromeProfile", "deviceName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser,
                      @Optional String profile,
                      @Optional String mobileDevice,
                      @Optional Object[] isBasicAuthChromeExtensionEnabled,
                      ITestContext context) {

        testUtilities = new TestUtilities();

        if (isBasicAuthChromeExtensionEnabled.length > 0) {
            hasBasicAuthChromeExtension = (boolean) isBasicAuthChromeExtensionEnabled[0];
        }

        // Specifying the path for the mockups and for the layout comparison result
        Ocular.config()
                .resultPath(Paths.get("ComparisonResult"))
                .snapshotPath(Paths.get("Mockups"));
//                .saveSnapshot(true); //or false??? remove after the first run

        String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);

        ConfigFactory.setProperty("env", Servers.PROD.getServer());
        Environment cfg = ConfigFactory.create(Environment.class);

        BrowserDriverFactory factory = new BrowserDriverFactory.Builder()
                .withBrowser(browser)
                .withLog(log)
                .withExtension(hasBasicAuthChromeExtension)
                .withProfile(profile)
                .withMobileDevice(mobileDevice).build();

        driver = factory.createDriver();

        driver.manage().window().maximize();
        this.context = setContext(context, driver);

        driver.get(cfg.url());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("Close driver");
        driver.quit();
    }

    @DataProvider(name = "isBasicAuthChromeExtensionEnabled")
    public Object[][] isBasicAuthChromeExtensionEnabled() {
        return new Object[][]{{false}, {true}};
    }
}