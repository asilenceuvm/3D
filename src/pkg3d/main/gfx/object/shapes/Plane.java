package pkg3d.main.gfx.object.shapes;

import java.awt.image.BufferedImage;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public class Plane extends Shape{
    
    public Plane(Main main, PolygonManager polygonManager, Camera camera, double x, double y, double z,
            double xSideLength, double ySideLength, double zSideLength, BufferedImage texture, String viewSide) {
        super(main, polygonManager, camera, x, y, z);
        
        corners = new double[4][3];
        
        corners[0] = new double[]{x,y,z};
        corners[1] = new double[]{x + xSideLength, y, z};
        corners[2] = new double[]{x + xSideLength, y + ySideLength, z};
        corners[3] = new double[]{x, y + ySideLength, z};
        
        polys = new PolygonObject[1];
        
        polys[0] = new PolygonObject(main, camera, new double[]{corners[0][0], corners[1][0], corners[2][0], corners[3][0]},
            new double[]{corners[0][1], corners[1][1], corners[2][1], corners[3][1]}, 
                new double[]{corners[0][2], corners[1][2], corners[2][2], corners[3][2]}, texture, viewSide);
    }
    
    
}
