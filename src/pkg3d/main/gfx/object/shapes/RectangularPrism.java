package pkg3d.main.gfx.object.shapes;

import java.awt.image.BufferedImage;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.Plane;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;

/**
 * @author asile
 * main building block and only currently functioning shape
 */
public class RectangularPrism extends Shape{
    
    
    public RectangularPrism(PolygonManager polygonManager, Camera camera, double x, double y, double z, double xSideLength, double ySideLength, double zSideLength, BufferedImage texture){
        super(polygonManager, camera,x,y,z);
        
        corners = new double[8][3];
        
        //one square
        corners[0] = new double[]{x,y,z};
        corners[1] = new double[]{x + xSideLength, y, z};
        corners[2] = new double[]{x + xSideLength, y + ySideLength, z};
        corners[3] = new double[]{x, y + ySideLength, z};
        //parallel square
        corners[4] = new double[]{x,y,z + zSideLength};
        corners[5] = new double[]{x + xSideLength, y, z + zSideLength};
        corners[6] = new double[]{x + xSideLength, y + ySideLength, z + zSideLength};
        corners[7] = new double[]{x, y + ySideLength, z + zSideLength};
        
        
        //inefficent but I couldn't think of a better way
        polys = new PolygonObject[6];
        
        polys[0] = new PolygonObject(camera, new double[]{corners[0][0], corners[1][0], corners[2][0], corners[3][0]},
            new double[]{corners[0][1], corners[1][1], corners[2][1], corners[3][1]}, 
                new double[]{corners[0][2], corners[1][2], corners[2][2], corners[3][2]}, texture);
        
        polys[1] = new PolygonObject(camera, new double[]{corners[0][0], corners[1][0], corners[5][0], corners[4][0]},
            new double[]{corners[0][1], corners[1][1], corners[5][1], corners[4][1]}, 
                new double[]{corners[0][2], corners[1][2], corners[5][2], corners[4][2]}, texture);
        
        polys[2] = new PolygonObject(camera, new double[]{corners[4][0], corners[5][0], corners[6][0], corners[7][0]},
            new double[]{corners[4][1], corners[5][1], corners[6][1], corners[7][1]}, 
                new double[]{corners[4][2], corners[5][2], corners[6][2], corners[7][2]}, texture);
        
        polys[3] = new PolygonObject(camera, new double[]{corners[1][0], corners[2][0], corners[6][0], corners[5][0]},
            new double[]{corners[1][1], corners[2][1], corners[6][1], corners[5][1]}, 
                new double[]{corners[1][2], corners[2][2], corners[6][2], corners[5][2]}, texture);
        
        polys[4] = new PolygonObject(camera, new double[]{corners[2][0], corners[3][0], corners[7][0], corners[6][0]},
            new double[]{corners[2][1], corners[3][1], corners[7][1], corners[6][1]}, 
                new double[]{corners[2][2], corners[3][2], corners[7][2], corners[6][2]}, texture);
        
        polys[5] = new PolygonObject(camera, new double[]{corners[0][0], corners[4][0], corners[7][0], corners[3][0]},
            new double[]{corners[0][1], corners[4][1], corners[7][1], corners[3][1]}, 
                new double[]{corners[0][2], corners[4][2], corners[6][2], corners[3][2]}, texture);
        
        planes = new Plane[6];
        
        for(int i  = 0; i < planes.length; i++){
            planes[i] = new Plane(polys[i]);
        }
        
    }
    
}
