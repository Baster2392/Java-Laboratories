package org.example;

import java.nio.file.Path;
import java.util.List;

public class Main {
    String outputPath = "D:\\Studia\\Platformy technologiczne\\PhotoStream\\src\\main\\java\\org\\example\\output";
    public static void main(String[] args) {
        String outputPath = "src/main/java/org/example/output";
        List<Path> listOfFiles = FileHandler.getListOfFiles("src/main/java/org/example/photos");
        long time = System.currentTimeMillis();
        PhotoStream.stream(listOfFiles, outputPath);
        System.out.println("Running time: " + (System.currentTimeMillis() - time));
    }
}