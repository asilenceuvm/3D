package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 *
 * @author asile
 */
public abstract class Entity {
    
    protected Main main;
    protected Shape s;
    protected double x,y,z;
    protected double xBoundWidth, yBoundHeight, zBoundDepth;
    protected double xMove, yMove, zMove;
    
    private boolean remove;
    
    public Entity(Main main, Shape s, double x, double y , double z){
        this.main = main;
        this.s = s;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
    public void checkRemove(int width, int height){
        if(s.mouseOver(width, height)){
            if(main.getMouseManager().getLeftPressed()){
                remove = true;
            }
        }
    }
    
    protected void move(double deltaX, double deltaY, double deltaZ){
        s.move(deltaX, deltaY, deltaZ);
        x+=deltaX;
        y+=deltaY;
        z+=deltaZ;
    }
    
    protected boolean collisionWithShape(double x, double y, double z){
        return false;
    }
    
    public Shape getShape(){
        return s;
    }
    
    public boolean getRemove(){
        return remove;
    }
}