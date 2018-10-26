package pkg3d.main.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author asile
 * quick class that loads images
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("res\\textures\\" + fileName));
        } catch (IOException e) {
            System.out.println("failed to read file");
        }
        return img;
    }
}
