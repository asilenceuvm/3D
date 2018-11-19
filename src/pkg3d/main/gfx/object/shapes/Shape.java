package pkg3d.main.gfx.object.shapes;

import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PlaneObject;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;

/**
 * @author asile
 * abstract class to make as a template for all shapes
 */
public abstract class Shape {
    
    private PolygonManager polygonManager;
    protected Camera camera;
    
    protected PolygonObject[] polys;
    protected double[][] corners;
    protected PlaneObject[] planes;
    protected double x,y,z;
    
    public Shape(PolygonManager polygonManager, Camera camera, double x, double y, double z){
        this.polygonManager = polygonManager;
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
        //cube collision Gilbert–Johnson–Keerthi_distance_algorithm
        for(Shape s: polygonManager.getShapes()){
            //if()
        }
    }
    
    //determines if mouse is over any part of the shape
    public boolean mouseOver(int width, int height){
        for(int i = 0; i < polys.length; i++){
            if(polys[i].mouseOver(width, height)){
                return true;
            }
        }
        return false;
    }
    
    //getters and setters
    public PolygonObject[] getPolys(){
        return polys;
    }
    
    public double[][] getCorners(){
        return corners;
    }
    
    public PlaneObject[] getPlanes(){
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
