package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileHandler {
    public static List<Path> getListOfFiles(String path) {
        List<Path> files = null;
        Path source = Path.of(path);
        try {
            Stream<Path> stream = Files.list(source);
            files = stream.toList();
        } catch (IOException e) {
            Logger.getGlobal().info(e.getMessage());
        }

        return files;
    }

    public static void saveFile(String savePathStr, Pair<String, BufferedImage> imagePair) {
        Path imagePath = Path.of(imagePair.getLeft());
        Path savePath = Path.of(savePathStr);
        savePath = Path.of(savePath.toString() + "\\" + imagePath.getFileName());
        BufferedImage image = imagePair.getRight();
        try {
            File newFile = new File(savePath.toString());
            ImageIO.write(image, "jpg", newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Pair<String, BufferedImage> getImagePair(String path) {
        Path filePath = Path.of(path);
        BufferedImage image;
        try {
             image = ImageIO.read(filePath.toFile());
        } catch (IOException e) {
            Logger.getGlobal().info(e.getMessage());
            return null;
        }
        return Pair.of(path, image);
    }

}
