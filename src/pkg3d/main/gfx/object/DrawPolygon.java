package pkg3d.main.gfx.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;

/**
 * @author asile
 * Backbone of everything drawn in game
 * uses javas built in polygon object to draw to the screen
 */
public class DrawPolygon {
    
    private Main main;
    private Camera camera;
    
    private Polygon polygon;
    private BufferedImage texture;
    private int vertecies;
    
    private boolean rendering=true;
    
    private double[] x, y, z;
    private double deltaX, deltaY;
        
    public DrawPolygon(Main main, Camera camera, double[] x, double[] y, double[] z, BufferedImage texture){
        this.main = main;
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
    public void update(){
        double[] x = new double[vertecies];
        double[] y = new double[vertecies];
        
        double[] calcPos;
        rendering = true;
        for (int i = 0; i < vertecies; i++) {
            calcPos = camera.calculatePositionP(this.x[i], this.y[i], z[i]);
            x[i] = (main.getWidth() / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
            y[i] = (main.getHeight() / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
            
            if (camera.getT() < 0) { //distance to polygon is negative therefore behind the camera
                rendering = false;
            }
        }
        
        //re-draw polygon
        polygon.reset();
        for(int i = 0; i < vertecies; i++){
            polygon.xpoints[i] = (int) x[i] + (int)deltaX;
            polygon.ypoints[i] = (int) y[i] + (int)deltaY;
            polygon.npoints = vertecies;
        }
    }
    
    public void render(Graphics g){
        if (rendering) {
           g.setClip(polygon);
           g.drawImage(texture, polygon.getBounds().x, polygon.getBounds().y,polygon.getBounds().width,polygon.getBounds().height, null);
           g.setClip(null);
        }
        
    }
    
    //getters & setters
    public Polygon getPolygon(){
        return polygon;
    }
    
    public void setTexture(BufferedImage texture){
        this.texture = texture;
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
        for(int i = 0; i < y.length; i++){
            y[i] += delta;
        }
    }
    public void addZ(double delta){
        for(int i = 0; i < z.length; i++){
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

