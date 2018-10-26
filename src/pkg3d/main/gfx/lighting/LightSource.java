

package pkg3d.main.gfx.lighting;

import pkg3d.main.gfx.Vector;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 * @author asile
 * work in progress class to give objects light
 */
public class LightSource {
    
    private double x,y,z;
    private double brightness;
    
    public LightSource(double x, double y, double z, double brightness){
        this.x = x;
        this.y = y;
        this.z = z;
        this.brightness = brightness;
    }
    
    //returns all the vectors from the source to the corners of a shape
    public Vector[] getRays(Shape shape){
        Vector[] rays = new Vector[shape.getCorners().length];
        for(int i = 0; i < rays.length; i++){
            rays[i] = new Vector(shape.getCorners()[i][0] - x, shape.getCorners()[i][1] - y, shape.getCorners()[i][2] - z);
        }
        return rays;
    }
    
    //getters & setters
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    public double getBrightness(){
        return brightness;
    }
    
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setZ(double z){
        this.z = z;
    }
}
