package pkg3d.main.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author asile\
 * handles base level mouse input
 */
public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    private double deltaX, deltaY;
    private boolean leftPressed, rightPressed;
    private int width, height;
    public int mouseWheelMove;
    
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
        if(me.getButton() == MouseEvent.BUTTON3){
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if(me.getButton() == MouseEvent.BUTTON1){
            leftPressed = false;
        }
        if(me.getButton() == MouseEvent.BUTTON3){
            rightPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        mouseWheelMove = mwe.getWheelRotation();
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
    public boolean getRightPressed(){
        return rightPressed;
    }
}
