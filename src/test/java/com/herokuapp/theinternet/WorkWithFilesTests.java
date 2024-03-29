package com.herokuapp.theinternet;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.DownloadUploadFilePages.FileDownloadPage;
import com.herokuapp.theinternet.pages.DownloadUploadFilePages.FileUploadPage;
import com.herokuapp.theinternet.pages.WelcomePage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;


public class WorkWithFilesTests extends BaseTest {

    @Test
    public void downloadFileFromList() {
        var fileDownloadPage = new FileDownloadPage(driver, log);
        var welcomePage = new WelcomePage(driver, log);

        welcomePage.openFileDownloadPage();
        var files = fileDownloadPage.getFileNames();
        var randomIndex = new Random().nextInt(files.size() - 1);
        var file = files.get(randomIndex).getText();

        fileDownloadPage.downloadFile(file);

        assertTrue(testUtilities.isFileExistedViaSelenoidApi(file, sessionId), "file " + file + " was not downloaded!");
        testUtilities.deleteFileInsideDockerDirectoryViaApi(file, sessionId);
    }

    @Test
    public void uploadFileViaButton() throws IOException {
        var fileName = testUtilities.generateRandomStringValue();
        var fileExtension = ".pdf";
        var fileUploadPage = new FileUploadPage(driver, log);
        var welcomePage = new WelcomePage(driver, log);
        var file = "";
        var fileSize = 1024 * 1000 * 11; // 11 Mb

        welcomePage.openFileUploadPage();
        try {
            file = testUtilities.createTempFile(fileName, fileExtension, fileSize);
            fileUploadPage.uploadFile(file);
        } finally {
            Files.deleteIfExists(Paths.get(file));
        }

        assertThat(fileUploadPage.getUploadedFileText()).isEqualTo(fileName + fileExtension);
    }

    @Test
    public void uploadFileUsingDragAndDrop() throws IOException {
        var fileName = testUtilities.generateRandomStringValue();
        var fileExtension = ".pdf";
        var fileUploadPage = new FileUploadPage(driver, log);
        var welcomePage = new WelcomePage(driver, log);
        var file = "";

        welcomePage.openFileUploadPage();
        try {
            file = testUtilities.createTempFile(fileName, fileExtension);
            fileUploadPage.dragAndDropFileUpload(file);
        } finally {
            Files.deleteIfExists(Paths.get(file));
        }

        assertThat(fileUploadPage.getUploadedFileText()).isEqualTo(fileName + fileExtension);
    }
}