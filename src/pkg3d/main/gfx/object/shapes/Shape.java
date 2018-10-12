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
    
    public Shape(Camera camera){
        this.camera = camera;
    }
    
    public PolygonObject[] getPolys(){
        return polys;
    }
    
    public double[][] getCorners(){
        return corners;
    }
}
