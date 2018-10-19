package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 *
 * @author asile
 */
public abstract class Entity {
    
    protected Shape s;
    protected double x,y,z;
    protected double xBoundWidth, yBoundHeight, zBoundDepth;
    protected double xMove, yMove, zMove;
    
    public Entity(Shape s, double x, double y , double z){
        this.s = s;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
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
}
