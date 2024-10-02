package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class PhotoStream {
    public static void stream(List<Path> paths, String op) {
        final String outputPath = op;
        paths.parallelStream()
                .forEach(path -> {
                    Pair<String, BufferedImage> pair = FileHandler.getImagePair(path.toString());
                    BufferedImage newImage = PhotoManipulator.changeGreenBlue(pair.getRight());
                    Pair<String, BufferedImage> newPair = Pair.of(pair.getLeft(), newImage);
                    FileHandler.saveFile(outputPath, newPair);
                });

    }
}
