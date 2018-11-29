package pkg3d.main.gfx.object;

import pkg3d.main.gfx.object.shapes.Shape;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;

/**
 * @author asile
 * Manages updating and rendering every polygon object
 */
public class PolygonManager {
    
    private Main main;
    private Camera camera;
    
    private ArrayList<PolygonObject> drawablePolygons = new ArrayList<PolygonObject>();
    private ArrayList<Shape> shapes = new ArrayList();
    
    private int[] renderOrder; //order to draw polygons
    
    public PolygonManager(Main main, Camera camera){
        this.main = main;
        this.camera = camera;
    }
    
    //creates a polygon given arrays of points and adds it to the array list
    public void addPolygon(double[] x, double[] y, double[] z, BufferedImage texture, String viewSide){
        drawablePolygons.add(new PolygonObject(main, camera, x, y, z, texture, viewSide));
    }
    
    //adds a pre-existing polygon to the arraylist
    public void addPolygon(PolygonObject p){
        drawablePolygons.add(p);
    }
    
    //adds a shape  by adding each polygon object in it
    public void addShape(Shape shape){
        for(int i = 0; i < shape.getPolys().length; i++){
            drawablePolygons.add(shape.getPolys()[i]);
        }
        shapes.add(shape);
    }
    
    //removes the shape from the array
    public void removeShape(Shape s){
        for(PolygonObject p: s.getPolys()){
            drawablePolygons.remove(p);
        }
    }
    
    public void update(int width, int height){
        for(int i = 0; i < drawablePolygons.size(); i++){
            drawablePolygons.get(i).update();
            drawablePolygons.get(i).setAvgDist(getDist(drawablePolygons.get(i)));
        }
    }
    
    public void render(Graphics g, int width, int height){
        setOrder();
        for(int i = 0; i < drawablePolygons.size(); i++){
            drawablePolygons.get(renderOrder[i]).render(g);
        }
    }
    
    public void calcLighting(double x, double y, double z){
        for(PolygonObject p: drawablePolygons){
            p.calcLighting(x, y, z);
        }
    }
    
    //sets the order that the polygons should be rendered
    private void setOrder(){
        double[] k = new double[drawablePolygons.size()];
        renderOrder = new int[drawablePolygons.size()];

        for (int i = 0; i < drawablePolygons.size(); i++) {
            k[i] = drawablePolygons.get(i).getAvgDist();
            renderOrder[i] = i;
        }

        double temp;
        int tempr;
        for (int a = 0; a < k.length - 1; a++) {
            for (int b = 0; b < k.length - 1; b++) {
                if (k[b] < k[b + 1]) {
                    temp = k[b];
                    tempr = renderOrder[b];
                    renderOrder[b] = renderOrder[b + 1];
                    k[b] = k[b + 1];

                    renderOrder[b + 1] = tempr;
                    k[b + 1] = temp;
                }
            }
        }
    }
    
    //gets the distance to a polygon object from the camera
    private double getDist(PolygonObject p) {
        double total = 0;
        for (int i = 0; i < p.getX().length; i++) {
            total += getDistanceToP(i,p);
        }
        
        return total / p.getX().length;
    }
    
    //helper method to getDist
    private double getDistanceToP(int i, PolygonObject p) {
        return Math.sqrt(
                (camera.getPosition()[0] - p.getX()[i]) * (camera.getPosition()[0] - p.getX()[i])
              + (camera.getPosition()[1] - p.getY()[i]) * (camera.getPosition()[1] - p.getY()[i])
              + (camera.getPosition()[2] - p.getZ()[i]) * (camera.getPosition()[2] - p.getZ()[i]));
    }
    
    public boolean checkIntersect(double x, double y, double length, double width, double z){
        for(Shape s: shapes){
            if(s.getMaxZ() != s.getMinZ()){//not flat
                if(s.getMaxZ() > z & s.getMinZ() < z){//not lower or higher
                    for (PolygonObject p : s.getPolys()) {
                        if (p.getMinZ() == p.getMaxZ()) {
                            Polygon p2 = new Polygon();
                            for (int i = 0; i < p.getX().length; i++) {
                                p2.addPoint((int) (p.getX()[i]), (int) (p.getY()[i]));
                            }
                            
                            Rectangle2D rect = new Rectangle2D.Double(x, y, length, width);
                            if(p2.intersects(rect)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    //getters
    public ArrayList<PolygonObject> getDrawablePolygons(){
        return drawablePolygons;
    }
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
}
