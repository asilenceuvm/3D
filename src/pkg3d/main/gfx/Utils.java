package pkg3d.main.gfx;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Robot;

/**
 * @author asile
 * extra methods that don't fit well into specific classes
 */
public class Utils {
    
    //draws the crosshair
    public void drawMouseAim(Graphics g, int width, int height, int aimSight) {
        g.setColor(Color.white);
        g.drawLine((int) (width / 2 - aimSight), (int) (height / 2), (int) (width / 2 + aimSight), (int) (height / 2));
        g.drawLine((int) (width / 2), (int) (height / 2 - aimSight), (int) (width / 2), (int) (height / 2 + aimSight));
    }
    
    //re-centers the mouse for the mouse movement
    public void centerMouse(int width, int height) {
        try {
            Robot r = new Robot();
            r.mouseMove((int) width/2, (int) height / 2);
        } catch (AWTException e) {
        }
    }
}
