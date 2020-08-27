package com.herokuapp.herokuapp.theinternet.base;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

//public class TestUtilities extends BaseTest {
public class TestUtilities {

    public String getRandomString() {
        String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        while (sb.length() < 10) {
            int index = (int) (random.nextFloat() * ALLOWED_CHARS.length());
            sb.append(ALLOWED_CHARS.charAt(index));
        }
        String emailDomain = sb.toString();
        return emailDomain;
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
        RandomAccessFile file = new RandomAccessFile(fileName + fileExtension, "rw");
        try (file) {
            file.setLength(sizeInBytes);
        }
        return file.toString();
    }

    public String createTempFile(String fileName, String fileExtension) throws IOException {
        if (isStringNullOrWhiteSpace(fileName) && isStringNullOrWhiteSpace(fileExtension)) {
            throw new IllegalArgumentException("File name and file extension must be specified!");
        }
        File file = new File(fileName + fileExtension);

        return file.toString();
    }
}