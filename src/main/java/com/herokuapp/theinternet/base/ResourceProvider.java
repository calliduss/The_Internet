package com.herokuapp.theinternet.base;

import java.io.File;

public class ResourceProvider {

    private static final String RESOURCES_PATH = System.getProperty("user.dir")
            + File.separator + ("src")
            + File.separator + ("main")
            + File.separator + ("resources")
            + File.separator;

//    public static String DownloadedFilesFolder = RESOURCES_PATH + "DownloadedFiles";
    public static String DownloadedFilesFolder = File.separator + "home" + File.separator + "selenium" + File.separator + "Downloads";
    public static String ChromeProfilesFolder = RESOURCES_PATH + "ChromeProfiles";
    public static String ChromeExtensionsFolder = RESOURCES_PATH + "ChromeExtensions";
    public static String TempFilesFolder = RESOURCES_PATH + "TempFiles";
    public static String SELENOID_URL = "http://selenoid:4444/wd/hub";
}
