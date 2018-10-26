package pkg3d.main.gfx.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import pkg3d.main.gfx.Camera;

/**
 * @author asile
 * Backbone of everything drawn in game
 * uses javas built in polygon object to draw to the screen
 */
public class PolygonObject {
    
    private Camera camera;
    
    private Polygon polygon;
    private BufferedImage texture;
    private int vertecies;
    
    private boolean outlines=true;
    private boolean rendering=true;
    
    private double avgDist;
    private double[] x, y, z;
    
    private double lighting = 1;
    
    public PolygonObject(Camera camera, double[] x, double[] y, double[] z, BufferedImage texture){
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
        this.texture = texture;
        
        polygon = new Polygon();
        vertecies = x.length;
        
        for(int i = 0; i < vertecies; i++){
            polygon.addPoint((int)x[i], (int)y[i]);
        }
        
    }
    
    //re-calculates where the polygon should be drawn 
    public void update(int width, int height){
        double[] x = new double[vertecies];
        double[] y = new double[vertecies];
        
        double[] calcPos;
        rendering = true;
        for (int i = 0; i < vertecies; i++) {
            calcPos = camera.calculatePositionP(this.x[i], this.y[i], z[i]);
            x[i] = (width / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
            y[i] = (height / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
            
            if (camera.getT() < 0) { //distance to polygon is negative therefore behind the camera
                rendering = false;
            }
        }
        
        //re-draw polygon
        polygon.reset();
        for(int i = 0; i < vertecies; i++){
            polygon.xpoints[i] = (int) x[i];
            polygon.ypoints[i] = (int) y[i];
            polygon.npoints = vertecies;
        }
    }
    
    public void render(Graphics g, int width, int height){
        if (rendering) {
            if (outlines) {
                g.setColor(Color.BLACK);
                g.drawPolygon(polygon);
            }
            
            //to be worked on, code for correctly rendering the polygon
            g.setClip(polygon);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform backup = g2d.getTransform();
            double angle = (Math.atan2(polygon.ypoints[1] - polygon.ypoints[0], polygon.xpoints[1] - polygon.xpoints[0]));
            AffineTransform a = AffineTransform.getRotateInstance(0, 0, 0);
            g2d.setTransform(a);
            g.drawImage(texture, polygon.getBounds().x, polygon.getBounds().y,polygon.getBounds().width,polygon.getBounds().height, null);
            //g.drawImage(texture,0,0,polygon.getBounds().width,polygon.getBounds().height, null);
            //g.setColor(new Color(.2f,.2f,.2f, (float)lighting));
            //g.fillPolygon(polygon);
            g2d.setTransform(backup);
            g.setClip(null);
            
            
        }
    }
    
    //calculates the lighting on the polygon
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
    }
    
    //determines if the polygon is in the center of the screen
    public boolean mouseOver(int width, int height) {
        return polygon.contains(width / 2, height / 2);
    }
    
    //getters & setters
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
    
    public void setLighting(double lighting){
        this.lighting = lighting;
    }
}
