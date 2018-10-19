package pkg3d.main.gfx.object;

import pkg3d.main.gfx.object.shapes.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.gfx.Camera;

/**
 *
 * @author asile
 */
public class PolygonManager {
    
    private ArrayList<PolygonObject> drawablePolygons = new ArrayList();
    private ArrayList<Shape> shapes = new ArrayList();
    private Camera camera;
    private int[] renderOrder;
    
    public PolygonManager(Camera camera){
        this.camera = camera;
    }
    
    public void addPolygon(double[] x, double[] y, double[] z, Color color){
        drawablePolygons.add(new PolygonObject(camera, x, y, z, color));
    }
    
    public void addPolygon(PolygonObject p){
        drawablePolygons.add(p);
    }
    
    public void addShape(Shape shape){
        for(int i = 0; i < shape.getPolys().length; i++){
            drawablePolygons.add(shape.getPolys()[i]);
        }
        shapes.add(shape);
    }
    
    public void update(int width, int height){
        for(int i = 0; i < drawablePolygons.size(); i++){
            drawablePolygons.get(i).update(width, height);
            drawablePolygons.get(i).setAvgDist(getDist(drawablePolygons.get(i)));
        }
    }
    
    public void render(Graphics g){
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
    
    private double getDist(PolygonObject p) {
        double total = 0;
        for (int i = 0; i < p.getX().length; i++) {
            total += getDistanceToP(i,p);
        }
        
        return total / p.getX().length;
    }

    private double getDistanceToP(int i, PolygonObject p) {
        return Math.sqrt(
                (camera.getPosition()[0] - p.getX()[i]) * (camera.getPosition()[0] - p.getX()[i])
              + (camera.getPosition()[1] - p.getY()[i]) * (camera.getPosition()[1] - p.getY()[i])
              + (camera.getPosition()[2] - p.getZ()[i]) * (camera.getPosition()[2] - p.getZ()[i]));
    }
    
    public ArrayList getDrawablePolygons(){
        return drawablePolygons;
    }
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
}
