package pkg3d.main.gfx.object.shapes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public class RectangularPrism extends Shape{
    
    
    public RectangularPrism(Camera camera, double x, double y, double z, double xSideLength, double ySideLength, double zSideLength, Color c){
        super(camera);
        polys = new PolygonObject[6];
        
        polys[0] = new PolygonObject(camera, new double[]{x, x + xSideLength, x + xSideLength, x}, 
            new double[]{y, y, y + ySideLength, y + ySideLength}, new double[]{z, z, z, z}, c);
        polys[1] = new PolygonObject(camera, new double[]{x, x + xSideLength, x + xSideLength, x}, 
            new double[]{y, y, y + ySideLength, y + ySideLength}, new double[]{z + zSideLength, z + zSideLength, z + zSideLength, z + zSideLength}, c);
        polys[2] = new PolygonObject(camera, new double[]{x, x + xSideLength, x + xSideLength, x}, 
            new double[]{y, y, y, y}, new double[]{z, z, z + zSideLength, z + zSideLength}, c);
        polys[3] = new PolygonObject(camera, new double[]{x, x + xSideLength, x + xSideLength, x}, 
            new double[]{y + ySideLength, y + ySideLength, y + ySideLength, y + ySideLength}, new double[]{z, z, z + zSideLength, z + zSideLength}, c);
        polys[4] = new PolygonObject(camera, new double[]{x, x, x, x}, 
            new double[]{y, y + ySideLength, y + ySideLength, y}, new double[]{z, z, z + zSideLength, z + zSideLength}, c);
        polys[5] = new PolygonObject(camera, new double[]{x + xSideLength, x + xSideLength, x + xSideLength, x + xSideLength}, 
            new double[]{y, y + ySideLength, y + ySideLength, y}, new double[]{z, z, z + zSideLength, z + zSideLength}, c);
    }
    
}
