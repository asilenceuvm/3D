package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 * @author asile
 * abstract class to handle entities
 * Entites are interactable objects composed of shapes
 */
public abstract class Entity {
    
    protected Main main;
    
    //actual composition
    protected Shape s;
    protected double xBoundWidth, yBoundHeight, zBoundDepth;
    
    //movement 
    protected double xMove, yMove, zMove;
    
    //boolean to determine if entity should be removed from game
    private boolean remove;
    
    public Entity(Main main, Shape s){
        this.main = main;
        this.s = s;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
    
    //checks whether enemy should be removed
    public void checkRemove(){
        if(s.mouseOver(main.getWidth(), main.getHeight())){
            if(main.getState().getCurScene().getController().getShooting()){
                remove = true;
            }
        }
    }
    //movement method
    //always moves now, but collision will be added later
    protected void move(double deltaX, double deltaY, double deltaZ){
        s.move(deltaX, deltaY, deltaZ);
    }
    
    //to be changed later
    protected boolean collisionWithShape(double x, double y, double z){
        return false;
    }
    
    //getters
    public Shape getShape(){
        return s;
    }
    
    public boolean getRemove(){
        return remove;
    }
}
