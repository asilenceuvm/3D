package pkg3d.main.gfx.object.shapes;

import java.util.ArrayList;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public abstract class Shape {
    
    protected Camera camera;
    protected PolygonObject[] polys;
    protected double[][] corners;
    protected double x,y,z;
    
    public Shape(Camera camera, double x, double y, double z){
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public PolygonObject[] getPolys(){
        return polys;
    }
    
    public double[][] getCorners(){
        return corners;
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
}
