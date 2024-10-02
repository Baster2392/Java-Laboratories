package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PhotoManipulator {
    public static BufferedImage createCopy(BufferedImage original) {
        return new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                original.getType()
        );
    }

    public static BufferedImage changeGreenBlue(BufferedImage image) {
        BufferedImage newImage = createCopy(image);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int green = color.getGreen();
                int blue = color.getBlue();
                int red = color.getRed();
                Color newColor = new Color(red, blue, green);
                newImage.setRGB(i, j, newColor.getRGB());
            }
        }
        return newImage;
    }
}
