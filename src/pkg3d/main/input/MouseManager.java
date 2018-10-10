package pkg3d.main.input;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author asile
 */
public class MouseManager implements MouseMotionListener{
    
    private double deltaX, deltaY;
    private int width, height;
    
    public MouseManager(int width, int height){
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        deltaX = me.getX() - width/2;
        deltaY = me.getY() - height/2;
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        deltaX = me.getX() - width / 2;
        deltaY = me.getY() - height / 2;
    }
    
    public double getDeltaX(){
        return deltaX;
    }
    
    public double getDeltaY(){
        return deltaY;
    }
    
    
}
