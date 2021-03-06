package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger log;
    private boolean hasBasicAuthChromeExtension;
    private String resourcesPath = System.getProperty("user.dir")
            + File.separator + ("src")
            + File.separator + ("main")
            + File.separator + ("resources")
            + File.separator;

    public BrowserDriverFactory(String browser, Logger log, boolean hasBasicAuthChromeExtension) {
        this.browser = browser;
        this.log = log;
        this.hasBasicAuthChromeExtension = hasBasicAuthChromeExtension;
    }

    public WebDriver createDriver() {
        log.info("Create driver: " + browser);
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", resourcesPath + "chromedriver.exe");
                driver.set(new ChromeDriver(setGeneralChromeOptions()));
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", resourcesPath + "geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;

            default:
                System.out.println("Unable to launch the browser " + browser + " starting chrome");
                System.setProperty("webdriver.chrome.driver", resourcesPath + "chromedriver.exe");
                driver.set(new ChromeDriver(setGeneralChromeOptions()));
                break;
        }
        return driver.get();
    }

    public WebDriver createChromeWithProfile(String profile) {
        log.info("Starting chrome driver with profile " + profile);
        ChromeOptions options = setGeneralChromeOptions();
        options.addArguments("user-data-dir" + resourcesPath + "ChromeProfiles" + profile);

        System.setProperty("webdriver.chrome.driver", resourcesPath + "chromedriver.exe");
        driver.set(new ChromeDriver(options));
        return driver.get();
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName) {
        log.info("Starting chrome driver with: " + deviceName + "emulation");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions options = setGeneralChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        System.setProperty("webdriver.chrome.driver", resourcesPath + "chromedriver.exe");
        driver.set(new ChromeDriver(options));
        return driver.get();
    }

    private ChromeOptions setGeneralChromeOptions() {
        log.info("Set general chrome options");
        var extensionsFolderPath = resourcesPath + File.separator + "ChromeExtensions" + File.separator;
        var downloadedFolderPath = resourcesPath + "DownloadedFiles";
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadedFolderPath);

        ChromeOptions options = new ChromeOptions();

        // add basicAuth chrome extension
        if (hasBasicAuthChromeExtension) {
            options.addExtensions(new File(extensionsFolderPath + "basicAuth.crx"));
        }

        return options.setExperimentalOption("prefs", chromePrefs);
    }

    public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("driver", driver);

        return iTestContext;
    }
}