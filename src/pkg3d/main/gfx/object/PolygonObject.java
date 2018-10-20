package pkg3d.main.gfx.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import pkg3d.main.gfx.Camera;

/**
 *
 * @author asile
 */
public class PolygonObject {
    
    private Polygon polygon;
    private Color color;
    private int vertecies;
    private Camera camera;
    
    private boolean outlines=true;
    private boolean rendering=true;
    
    private double avgDist;
    private double[] x, y, z;
    
    private double lighting = 1;
    private boolean opaque = true;
    
    public PolygonObject(Camera camera, double[] x, double[] y, double[] z, Color color){
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
        
        polygon = new Polygon();
        vertecies = x.length;
        
        for(int i = 0; i < vertecies; i++){
            polygon.addPoint((int)x[i], (int)y[i]);
        }
        
    }
    
    public void update(int width, int height){
        double[] x = new double[vertecies];
        double[] y = new double[vertecies];
        
        double[] calcPos;
        rendering = true;
        for (int i = 0; i < vertecies; i++) {
            calcPos = camera.calculatePositionP(this.x[i], this.y[i], z[i]);
            x[i] = (width / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
            y[i] = (height / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
            if (camera.getT() < 0) {
                rendering = false;
            }
        }
        polygon.reset();
        for(int i = 0; i < vertecies; i++){
            polygon.xpoints[i] = (int) x[i];
            polygon.ypoints[i] = (int) y[i];
            polygon.npoints = vertecies;
        }
    }
    
    public void render(Graphics g){
        if (rendering) {
            if (outlines) {
                g.setColor(Color.BLACK);
                g.drawPolygon(polygon);
            }
            if(opaque){
                g.setColor(new Color((int)(color.getRed() * lighting), (int)(color.getBlue() * lighting), (int)(color.getGreen() * lighting)));
            } else {
                g.setColor(color);
            }
            g.fillPolygon(polygon);
        }
    }
    
    public void calcLighting(double x, double y, double z) {
        Plane lightingPlane = new Plane(this);
        double angle = Math.acos(((lightingPlane.getNewVector().getX() * x)
                + (lightingPlane.getNewVector().getY() * y) + (lightingPlane.getNewVector().getZ() * z))
                / (Math.sqrt(x * x + y * y + z * z)));

        lighting = 0.2 + 1 - Math.sqrt(Math.toDegrees(angle) / 180);

        if (lighting > 1) {
            lighting = 1;
        }
        if (lighting < 0.3) {
            lighting = 0.3;
        }
        //if(Double.isNaN(angle)){
          //  lighting = 0.5; 
        //}
    }
    
    public boolean mouseOver(int width, int height) {
        return polygon.contains(width / 2, height / 2);
    }

    public void setLighting(double lighting){
        this.lighting = lighting;
    }
    public void setOpaque(boolean opaque){
        this.opaque = opaque;
    }
    
    //getters
    public double getAvgDist(){
        return avgDist;
    }
    public void setAvgDist(double avgDist){
        this.avgDist = avgDist;
    }
    
    public void setX(double[] x){
        this.x = x;
    }
    public void setY(double[] y){
        this.y = y;
    }
    public void setZ(double[] z){
        this.z = z;
    }
    
    public void addX(double delta){
        for(int i = 0; i < x.length; i++){
            x[i] += delta;
        }
    }
    public void addY(double delta){
        for(int i = 0; i < x.length; i++){
            y[i] += delta;
        }
    }
    public void addZ(double delta){
        for(int i = 0; i < x.length; i++){
            z[i] += delta;
        }
    }
    
    public double[] getX(){
        return x;
    }
    public double[] getY(){
        return y;
    }
    public double[] getZ(){
        return z;
    }
}
