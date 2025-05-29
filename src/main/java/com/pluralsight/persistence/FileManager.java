package com.pluralsight.persistence;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileManager {


    /*
     * Provides basic file read/write operations.
     *
     * Fields:
     * - baseDir: String
     *
     * Methods:
     * - writeToFile(filename: String, payload: String): void
     * - readFromFile(filename: String): String
     */

    private String baseDir;

    public FileManager(String baseDir) {
        this.baseDir = baseDir;
    }

    public void writeToFile(String filename, String payload) {
        Path filePath = Paths.get(baseDir, filename);
        try {
            Files.write(filePath, payload.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to write to " + filePath + ": " + e.getMessage());
        }
    }

    public String readFromFile(String filename) {
        Path filePath = Paths.get(baseDir, filename);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.err.println("Failed to read from " + filePath + ": " + e.getMessage());
            return "";
        }
    }

}
