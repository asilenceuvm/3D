package pkg3d.main.gfx.object;

import pkg3d.main.gfx.Vector;

/**
 *
 * @author asile
 */
public class Plane {
    private Vector vector1, vector2, newVector;
    private double[] points = new double[3];

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

    public Plane(Vector V1, Vector V2, double[] points) {
        this.points = points;
        this.vector1 = V1;
        this.vector2 = V2;
        newVector = Vector.crossProduct(V1, V2);
    }
    
    public Vector getNewVector(){
        return newVector;
    }
    
    public double[] getPoints(){
        return points;
    }
}
