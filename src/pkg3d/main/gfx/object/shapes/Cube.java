package pkg3d.main.gfx.object.shapes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public class Cube extends Shape{
    
    
    public Cube(Camera camera, double x, double y, double z, double sideLength, Color c){
        super(camera);
        polys = new PolygonObject[6];
        
        polys[0] = new PolygonObject(camera, new double[]{x, x + sideLength, x + sideLength, x}, 
            new double[]{y, y, y + sideLength, y + sideLength}, new double[]{z, z, z, z}, c);
        polys[1] = new PolygonObject(camera, new double[]{x, x + sideLength, x + sideLength, x}, 
            new double[]{y, y, y + sideLength, y + sideLength}, new double[]{z + sideLength, z + sideLength, z + sideLength, z + sideLength}, c);
        polys[2] = new PolygonObject(camera, new double[]{x, x + sideLength, x + sideLength, x}, 
            new double[]{y, y, y, y}, new double[]{z, z, z + sideLength, z + sideLength}, c);
        polys[3] = new PolygonObject(camera, new double[]{x, x + sideLength, x + sideLength, x}, 
            new double[]{y + sideLength, y + sideLength, y + sideLength, y + sideLength}, new double[]{z, z, z + sideLength, z + sideLength}, c);
        polys[4] = new PolygonObject(camera, new double[]{x, x, x, x}, 
            new double[]{y, y + sideLength, y + sideLength, y}, new double[]{z, z, z + sideLength, z + sideLength}, c);
        polys[5] = new PolygonObject(camera, new double[]{x + sideLength, x + sideLength, x + sideLength, x + sideLength}, 
            new double[]{y, y + sideLength, y + sideLength, y}, new double[]{z, z, z + sideLength, z + sideLength}, c);
    }
    
}
