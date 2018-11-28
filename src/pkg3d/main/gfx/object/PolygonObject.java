package pkg3d.main.gfx.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;

/**
 * @author asile
 * Backbone of everything drawn in game
 * uses javas built in polygon object to draw to the screen
 */
public class PolygonObject {
    
    private Main main;
    private Camera camera;
    
    private Polygon polygon;
    private BufferedImage texture;
    private int vertecies;
    
    private boolean outlines=false;
    private boolean drawing=true;
    
    private double avgDist;
    private double[] x, y, z;
    
    private DrawPolygon[][] drawPolys;
    private DrawPolygon[] drawedPolys;
    
    private String viewSide;
    
    private double lighting = 1;
        
    public PolygonObject(Main main, Camera camera, double[] x, double[] y, double[] z, 
            BufferedImage texture, String viewSide){
        this.main = main;
        this.camera = camera;
        this.x = x;
        this.y = y;
        this.z = z;
        this.texture = texture;
        this.viewSide = viewSide;
        
        polygon = new Polygon();
        vertecies = x.length;
        
        for(int i = 0; i < vertecies; i++){
            polygon.addPoint((int)x[i], (int)y[i]);
        }
        
        setDrawPolys();
    }
    
    private void setDrawPolys(){
        double xSlope = (x[1] - x[0]) / texture.getWidth();
        double ySlope = (y[3] - y[1]) / texture.getWidth();
        double zSlope = (z[2] - z[0]) / texture.getWidth();
        
        if(ySlope < 0){
            ySlope = -ySlope;
        }
        
        drawPolys = new DrawPolygon[texture.getWidth()][texture.getHeight()];
        drawedPolys = new DrawPolygon[texture.getWidth() * texture.getHeight()];
        
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                int color = texture.getRGB(r, c);
                int alpha = (color >> 24) & 0xff;
                if (alpha != 0) {
                    if (ySlope != 0) {
                        drawPolys[r][c] = new DrawPolygon(main, camera,
                                new double[]{x[0] + ((r) * xSlope), x[0] + ((r + 1) * xSlope), x[0] + ((r + 1) * xSlope), x[0] + ((r) * xSlope)},
                                new double[]{y[0] + c * ySlope, y[0] + c * ySlope, y[0] + ((c + 1) * ySlope), y[0] + ((c + 1) * ySlope)},
                                new double[]{z[0] + ((r) * zSlope), z[0] + ((r + 1) * zSlope), z[0] + ((r + 1) * zSlope), z[0] + ((r) * zSlope)},
                                new Color(texture.getRGB(r, c)));
                    } else {
                        drawPolys[r][c] = new DrawPolygon(main, camera,
                                new double[]{x[0] + ((c) * xSlope), x[0] + ((c + 1) * xSlope), x[0] + ((c + 1) * xSlope), x[0] + ((c) * xSlope)},
                                new double[]{y[0] + c * ySlope, y[0] + c * ySlope, y[0] + ((c + 1) * ySlope), y[0] + ((c + 1) * ySlope)},
                                new double[]{z[0] + ((r) * zSlope), z[0] + ((r) * zSlope), z[0] + ((r + 1) * zSlope), z[0] + ((r + 1) * zSlope)},
                                new Color(texture.getRGB(r, c)));
                    }
                }
            }
        }
        ArrayList<DrawPolygon> drawList = new ArrayList<DrawPolygon>();
        
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                if(drawPolys[r][c] != null){
                    drawList.add(drawPolys[r][c]);
                }
            }
        }
        
        drawedPolys = new DrawPolygon[drawList.size()];
        for(int i = 0; i < drawedPolys.length; i++){
            drawedPolys[i] = drawList.get(i);
        }
    }
    
    //re-calculates where the polygon should be drawn 
    public void update(){
        double[] x = new double[vertecies];
        double[] y = new double[vertecies];
        
        double[] calcPos;
        for (int i = 0; i < vertecies; i++) {
            calcPos = camera.calculatePositionP(this.x[i], this.y[i], z[i]);
            x[i] = (main.getWidth() / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
            y[i] = (main.getHeight() / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
            
            if (camera.getT() < 0) { //distance to polygon is negative therefore behind the camera
                drawing = false;
               // rendering=false;
            }
        }
        
        //re-draw polygon
        polygon.reset();
        for(int i = 0; i < vertecies; i++){
            polygon.xpoints[i] = (int) x[i];
            polygon.ypoints[i] = (int) y[i];
            polygon.npoints = vertecies;
        }
        
        for(int i = 0; i < drawedPolys.length; i++){
            drawedPolys[i].update();
        }
        checkDraw();
    }
    
    private void checkDraw(){
        if (viewSide != null) {
            if (viewSide.equals("+z")) {
                if (camera.getPosition()[2] < z[0]) {
                    drawing = false;
                }
            } else if (viewSide.equals("-z")) {
                if (camera.getPosition()[2] > z[0]) {
                    drawing = false;
                }
            } else if (viewSide.equals("+x")) {
                if (camera.getPosition()[0] > x[0]) {
                    drawing = false;
                }
            } else if (viewSide.equals("-x")) {
                if (camera.getPosition()[0] < x[0]) {
                    drawing = false;
                }
            } else if (viewSide.equals("+y")) {
                if (camera.getPosition()[1] > y[0]) {
                    drawing = false;
                }
            } else {
                if (camera.getPosition()[1] < y[0]) {
                    drawing = false;
                }
            }
            if (viewSide.equals("+z")) {
                if (camera.getPosition()[2] > z[0]) {
                    drawing = true;
                }
            } else if (viewSide.equals("-z")) {
                if (camera.getPosition()[2] < z[0]) {
                    drawing = true;
                }
            } else if (viewSide.equals("+x")) {
                if (camera.getPosition()[0] < x[0]) {
                    drawing = true;
                }
            } else if (viewSide.equals("-x")) {
                if (camera.getPosition()[0] > x[0]) {
                    drawing = true;
                }
            } else if (viewSide.equals("+y")) {
                if (camera.getPosition()[1] < y[0]) {
                    drawing = true;
                }
            } else {
                if (camera.getPosition()[1] > y[0]) {
                    drawing = true;
                }
            }
        }
    }
    
    public void render(Graphics g){
        if (outlines) {
            g.setColor(Color.BLACK);
            g.drawPolygon(polygon);
        }
        if(drawing){
            //g.setClip(polygon);
            for(int i = 0; i < drawedPolys.length; i++){
                drawedPolys[i].render(g);
            }
            //g.setClip(null);
        }
    }
    
    //calculates the lighting on the polygon
    public void calcLighting(double x, double y, double z) {
        PlaneObject lightingPlane = new PlaneObject(this);
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
    
    public Polygon getPolygon(){
        return polygon;
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
    
    public double getMaxZ(){
        double maxZ = z[0];
        for(int i = 0; i < z.length; i++){
            if(z[i] > maxZ){
                maxZ = z[i];
            }
        }
        return maxZ;
    }
    
    public double getMinZ(){
        double minZ = z[0];
        for(int i = 0; i < z.length; i++){
            if(z[i] < minZ){
                minZ = z[i];
            }
        }
        return minZ;
    }
    
    public void addX(double delta){
        for(int i = 0; i < x.length; i++){
            x[i] += delta;
        }
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                drawPolys[r][c].addX(delta);
            }
        }
    }
    public void addY(double delta){
        for(int i = 0; i < x.length; i++){
            y[i] += delta;
        }
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                drawPolys[r][c].addY(delta);
            }
        }
    }
    public void addZ(double delta){
        for(int i = 0; i < x.length; i++){
            z[i] += delta;
        }
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                drawPolys[r][c].addZ(delta);
            }
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
    public void setDrawing(boolean drawing){
        this.drawing = drawing;
    }
    public String getViewSide(){
        return viewSide;
    }
}
