package pkg3d.main.gfx.object.shapes;

import java.awt.Graphics;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public abstract class Shape {
    
    protected Camera camera;
    protected PolygonObject[] polys;
    
    public Shape(Camera camera){
        this.camera = camera;
    }
    
    public PolygonObject[] getPolys(){
        return polys;
    }
}
