package ua.com.abakumov.kafkaswing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Utils {

    public static ImageIcon getImageIcon(String name) {
        URL resource = Utils.class.getResource(name);
        BufferedImage buttonIcon;
        try {
            buttonIcon = ImageIO.read(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ImageIcon(buttonIcon);
    }
}
