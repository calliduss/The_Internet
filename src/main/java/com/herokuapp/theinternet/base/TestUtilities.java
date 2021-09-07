package com.herokuapp.theinternet.base;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;


import static com.herokuapp.theinternet.base.ResourceProvider.SELENOID_DOWNLOAD_API;
import static com.herokuapp.theinternet.base.ResourceProvider.TempFilesFolder;

public class TestUtilities {

    protected Logger log;

    public TestUtilities(Logger log) {
        this.log = log;
    }

    public String generateRandomStringValue() {
        String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while (sb.length() < 10) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            sb.append(ALLOWED_CHARS.charAt(index));
        }
        return sb.toString();
    }

    public String getNameOfLatestFileFromDir(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile.getName();
    }

    public boolean isFileExisted(String expectedFileName, String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();

        assert files != null;
        for (File listOfFile : files) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                if (fileName.equals(expectedFileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFileExistedViaSelenoidApi(String expectedFileName, SessionId sessionId) {
        HttpUriRequest request = new HttpGet(SELENOID_DOWNLOAD_API + sessionId.toString() + "/" + expectedFileName);
        try {
            log.info("Execute GET request: " + request.toString());
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            var statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.info("Status code " + statusCode);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void deleteFileInsideDockerDirectoryViaApi(String filepath, SessionId sessionId) {
        HttpUriRequest request = new HttpGet(SELENOID_DOWNLOAD_API + sessionId.toString() + "/" + filepath);
        try {
            log.info("Execute DELETE request: " + request.toString());
            HttpResponse response = HttpClientBuilder.create().build().execute(request);
            var statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                log.info("Status code " + statusCode);
                throw new NoSuchFileException("File does not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStringNullOrWhiteSpace(String value) {
        if (value == null) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String createTempFile(String fileName, String fileExtension, long sizeInBytes) throws IOException {
        if (isStringNullOrWhiteSpace(fileName) && isStringNullOrWhiteSpace(fileExtension)) {
            throw new IllegalArgumentException("File name and file extension must be specified!");
        }
        log.info("filename is: " + fileName);
        log.info("file extension is: " + fileExtension);
        log.info("tempfolder is: " + TempFilesFolder);
        log.info("file size is: " + sizeInBytes);

//        Process p = Runtime.getRuntime().exec(new String[]{"/bin/sh",
//                "-c",
//                "cd /home/selenium; ls"});

        log.info("list selenium folder content");
        try (Stream<Path> paths = Files.walk(Paths.get("/home/selenium"))) {
            paths.filter(Files::isRegularFile)
                    .forEach(System.out::println);

        }
        
        File file = new File(TempFilesFolder + File.separator, fileName + fileExtension);
        if (file.exists()) {
            throw new FileAlreadyExistsException("file with name " + fileName + " already exists");
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try (randomAccessFile) {
            log.info("creating random file");
            randomAccessFile.setLength(sizeInBytes);
        }
        return file.getAbsolutePath();
    }

    public String createTempFile(String fileName, String fileExtension) {
        if (isStringNullOrWhiteSpace(fileName) && isStringNullOrWhiteSpace(fileExtension)) {
            throw new IllegalArgumentException("File name and file extension must be specified!");
        }

        File file = new File(TempFilesFolder + File.separator, fileName + fileExtension);

        try {
            if (file.exists()) {
                throw new FileAlreadyExistsException("file with name " + fileName + " already exists");
            } else {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public void cleanUpDirectory(String dirPath) {
        Arrays.stream(Objects.requireNonNull(new File(dirPath).listFiles())).forEach(File::delete);
    }
}