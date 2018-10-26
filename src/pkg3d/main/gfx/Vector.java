package pkg3d.main.gfx;

/**
 * @author asile
 * Vector class for calculations
 */
public class Vector {
    
    private double x, y, z;
    
    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        
        //converts to unit vector
        double length = Math.sqrt(x * x + y * y + z * z);
        if(length > 0){
            this.x = x/length;
            this.y = y/length;
            this.z = z/length;
        }
    }
    
    //calculates the cross product of the given vectors
    public static Vector crossProduct(Vector v1, Vector v2){
        Vector crossProduct = new Vector(
            v1.y * v2.z - v1.z * v2.y,
            v1.z * v2.x - v1.x * v2.z,
            v1.x * v2.y - v1.y * v2.x);
        return crossProduct;
    }
    
    //prints the vectors components
    @Override
    public String toString(){
        return x + " " + y + " " + z;
    }
    
    //getters
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
