package pkg3d.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * @author asile\
 * handles base level mouse input
 */
public class MouseManager implements MouseListener, MouseMotionListener{
    
    private double deltaX, deltaY;
    private boolean leftPressed;
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
    
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            leftPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    //getters & setters
    public double getDeltaX(){
        return deltaX;
    }
    
    public double getDeltaY(){
        return deltaY;
    }
    public boolean getLeftPressed(){
        return leftPressed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MouseManager that = (MouseManager) o;

        if (width != that.width) return false;
        return height == that.height;
    }

}
