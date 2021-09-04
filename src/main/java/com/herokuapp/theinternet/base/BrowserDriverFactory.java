package com.herokuapp.theinternet.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.herokuapp.theinternet.base.ResourceProvider.*;

public class BrowserDriverFactory {

    private RemoteWebDriver driver;
    private String browser;
    private Logger log;
    private boolean hasBasicAuthChromeExtension;
    private String profile;
    private String mobileDevice;

   public RemoteWebDriver createDriver() {
       log.info("Create driver: " + browser);
       DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("browserName", browser.toLowerCase());

       switch(browser.toLowerCase()) {
           case "chrome": capabilities.setCapability(ChromeOptions.CAPABILITY, setGeneralChromeOptions());
           case "firefox": capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS,setGeneralFirefoxOptions());
           default: capabilities.setCapability(ChromeOptions.CAPABILITY, setGeneralChromeOptions());
       }

       capabilities.setCapability("selenoid:options", Map.<String, Object>of(
               "enableVNC", true,
               "enableVideo", false
       ));
       try {
            driver = new RemoteWebDriver(URI.create(SELENOID_URL).toURL(), capabilities);

           // to upload local files to remote Selenium instance
           driver.setFileDetector(new LocalFileDetector());
       } catch (MalformedURLException e) {
           e.printStackTrace();
       }
        return driver;
    }

    public ChromeOptions createChromeWithProfile(String profile) {
        log.info("Starting chrome driver with profile " + profile);
        ChromeOptions options = setGeneralChromeOptions();
        return options.addArguments("user-data-dir" + ChromeProfilesFolder + File.separator + profile);
    }

    public ChromeOptions createChromeWithMobileEmulation(String deviceName) {
        log.info("Starting chrome driver with: " + deviceName + "emulation");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions options = setGeneralChromeOptions();
        return options.setExperimentalOption("mobileEmulation", mobileEmulation);
    }

    private ChromeOptions setGeneralChromeOptions() {
        log.info("Set general chrome options");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);

        // add basicAuth chrome extension
        if (hasBasicAuthChromeExtension) {
            chromeOptions.addExtensions(new File(ChromeExtensionsFolder + File.separator + "basicAuth.crx"));
        }

        chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>(){
            {
                put("profile.default_content_settings.popups", 0);
                put("download.default_directory", DownloadedFilesFolder);
                put("download.prompt_for_download", false);
                put("download.directory_upgrade", true);
                put("safebrowsing.enabled", false);
                put("plugins.always_open_pdf_externally", true);
                put("plugins.plugins_disabled", new ArrayList<String>(){
                    {
                        add("Chrome PDF Viewer");
                    }
                });
            }
        });
       return chromeOptions;
    }

    private FirefoxOptions setGeneralFirefoxOptions() {
        log.info("Set general chrome options");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setCapability("moz:firefoxOptions", new HashMap<String, Object>(){
            {
                put("prefs", new HashMap<String, Object>(){
                    {
                        put("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                    }
                });
            }
        });
        return firefoxOptions;
    }

    public static ITestContext setContext(ITestContext iTestContext, RemoteWebDriver driver) {
        iTestContext.setAttribute("driver", driver);

        return iTestContext;
    }

    public static class Builder {
        private String browser;
        private Logger log;
        private boolean hasBasicAuthChromeExtension;
        private String profile;
        private String mobileDevice;

        public Builder() {}

        public Builder withBrowser(String browser) {
            this.browser = browser;
            return this;
        }

        public Builder withLog(Logger log) {
            this.log = log;
            return this;
        }

        public Builder withExtension(boolean hasBasicAuthChromeExtension) {
            this.hasBasicAuthChromeExtension = hasBasicAuthChromeExtension;
            return this;
        }

        public Builder withProfile(String profile) {
            this.profile = profile;
            return this;
        }

        public Builder withMobileDevice(String mobileDevice) {
            this.mobileDevice = mobileDevice;
            return this;
        }

        public BrowserDriverFactory build() {
            BrowserDriverFactory browserDriverFactory = new BrowserDriverFactory();
            browserDriverFactory.browser = this.browser;
            browserDriverFactory.log = this.log;
            browserDriverFactory.hasBasicAuthChromeExtension = this.hasBasicAuthChromeExtension;
            browserDriverFactory.profile = this.profile;
            browserDriverFactory.mobileDevice = mobileDevice;

            return browserDriverFactory;
        }
    }
}