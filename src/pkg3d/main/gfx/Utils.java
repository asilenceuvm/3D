package pkg3d.main.gfx;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 *
 * @author asile
 */
public class Utils {
    
    
    public void centerMouse(int width, int height) {
        try {
            Robot r = new Robot();
            r.mouseMove((int) width/2, (int) height / 2);
        } catch (AWTException e) {
        }
    }
    
}
