package com.herokuapp.theinternet.base;

import java.io.File;

public class ResourceProvider {

    private static final String RESOURCES_PATH = System.getProperty("user.dir")
            + File.separator + ("src")
            + File.separator + ("main")
            + File.separator + ("resources")
            + File.separator;

    public static String DownloadedFilesFolder = RESOURCES_PATH + "DownloadedFiles";
    public static String ChromeDriverPath = RESOURCES_PATH + "chromedriver.exe";
    public static String GeckoDriverPath = RESOURCES_PATH + "geckodriver.exe";
    public static String ChromeProfilesFolder = RESOURCES_PATH + "ChromeProfiles";
    public static String ChromeExtensionsFolder = RESOURCES_PATH + "ChromeExtensions";
    public static String TempFilesFolder = RESOURCES_PATH + "TempFiles";
}
