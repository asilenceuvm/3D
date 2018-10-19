package pkg3d.main.gfx;

/**
 *
 * @author asile
 */
public class Vector {
    
    private double x, y, z;
    
    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        
        double length = Math.sqrt(x * x + y * y + z * z);
        if(length > 0){
            this.x = x/length;
            this.y = y/length;
            this.z = z/length;
        }
    }
    
    public static Vector crossProduct(Vector v1, Vector v2){
        Vector crossProduct = new Vector(
            v1.y * v2.z - v1.z * v2.y,
            v1.z * v2.x - v1.x * v2.z,
            v1.x * v2.y - v1.y * v2.x);
        return crossProduct;
    }
    
    public String toString(){
        return x + " " + y + " " + z;
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
