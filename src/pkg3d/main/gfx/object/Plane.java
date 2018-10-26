package pkg3d.main.gfx.object;

import pkg3d.main.gfx.Vector;

/**
 * @author asile
 * Mathematical plane object
 */
public class Plane {
    private Vector vector1, vector2, newVector;
    private double[] points = new double[3];
    
    //creates a plane using a polygon object
    public Plane(PolygonObject p) {
        points[0] = p.getX()[0];
        points[1] = p.getY()[0];
        points[2] = p.getZ()[0];

        vector1 = new Vector(p.getX()[1] - p.getX()[0],
                p.getY()[1] - p.getY()[0],
                p.getZ()[1] - p.getZ()[0]);

        vector2 = new Vector(p.getX()[2] - p.getX()[0],
                p.getY()[2] - p.getY()[0],
                p.getZ()[2] - p.getZ()[0]);

        newVector = Vector.crossProduct(vector1, vector2);
    }
    
    //creates a plane given two vectors and an array of points
    public Plane(Vector V1, Vector V2, double[] points) {
        this.points = points;
        this.vector1 = V1;
        this.vector2 = V2;
        newVector = Vector.crossProduct(V1, V2);
    }
   
    //getts the intersection between a vector and this plane
    public double[] getIntersection(Vector v, double[] start){
        double t, plane, vector, div;
        double[] points = new double[3];
        
        plane = (newVector.getX() * points[0] + newVector.getY() * points[1] + newVector.getZ() * points[2]);
        vector =  (newVector.getX() * start[0] + newVector.getY() * start[1] + newVector.getZ() * start[2]);
        div = newVector.getX() * v.getX() + newVector.getY() * v.getY() + newVector.getZ() * v.getZ();
        
        t = (plane - vector) / div;
        
        points[0] = start[0] + t * v.getX();
        points[1] = start[1] + t * v.getY();
        points[2] = start[2] + t * v.getZ();
        
        return points;
    }
    
    //getters
    public Vector getNewVector(){
        return newVector;
    }
    
    public double[] getPoints(){
        return points;
    }
}
