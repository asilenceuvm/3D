package pkg3d.main.gfx.object.shapes;

import java.awt.Color;
import java.util.ArrayList;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public class Pyramid extends Shape{
    
    public Pyramid(Camera camera, double x, double y, double z, double baseLength, double height, Color c){
        super(camera);
        
        polys = new PolygonObject[5];
        
        polys[0] = new PolygonObject(camera, new double[]{x, x + baseLength, x + baseLength, x}, 
            new double[]{y, y, y + baseLength, y + baseLength}, new double[]{z, z, z, z}, c);
        polys[1] = new PolygonObject(camera, new double[]{x, x + baseLength, x + baseLength / 2},
                new double[]{y, y, y + baseLength / 2}, new double[]{z, z, z + height}, c);
        polys[2] = new PolygonObject(camera, new double[]{x + baseLength, x+ baseLength, x + baseLength / 2},
                new double[]{y, y + baseLength, y + baseLength / 2}, new double[]{z, z, z + height}, c);
        polys[3] = new PolygonObject(camera, new double[]{x, x + baseLength, x + baseLength / 2},
                new double[]{y + baseLength, y + baseLength, y + baseLength / 2}, new double[]{z, z, z + height}, c);
        polys[4] = new PolygonObject(camera, new double[]{x, x, x + baseLength / 2},
                new double[]{y, y + baseLength, y + baseLength / 2}, new double[]{z, z, z + height}, c);
    }

}
