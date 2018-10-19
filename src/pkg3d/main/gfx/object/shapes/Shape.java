package pkg3d.main.gfx.object.shapes;

import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.Plane;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public abstract class Shape {
    
    protected Camera camera;
    protected PolygonObject[] polys;
    protected double[][] corners;
    protected Plane[] planes;
    protected double x,y,z;
    
    public Shape(Camera camera, double x, double y, double z){
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void move(double deltaX, double deltaY, double deltaZ){
        for(int i = 0; i < corners.length; i++){
            corners[i][0] += deltaX;
            corners[i][1] += deltaY;
            corners[i][2] += deltaZ;
        }
        for(int i = 0; i < polys.length; i++){
            polys[i].addX(deltaX);
            polys[i].addY(deltaY);
            polys[i].addZ(deltaZ);
        }
    }
    
    public PolygonObject[] getPolys(){
        return polys;
    }
    
    public double[][] getCorners(){
        return corners;
    }
    
    public Plane[] getPlanes(){
        return planes;
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
