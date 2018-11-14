package pkg3d.main.gfx.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.ImageLoader;
import pkg3d.main.gfx.object.shapes.Shape;

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
    
    private DrawPolygon[][] drawPolys;
    private double[] newX, newY, newZ;
    
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
        
        newX = x;
        newY = y;
        
        double xSlope = (x[1]-x[0]) / texture.getWidth();
        double ySlope = (y[2]-y[1]) / texture.getWidth();
        double zSlope = (z[1]-z[0]) / texture.getWidth();
        
        drawPolys = new DrawPolygon[texture.getWidth()][texture.getHeight()];
        /*
        drawPolys[0][0] = new DrawPolygon(camera, 
                new double[]{x[0], x[0] - xSlope, x[0] - xSlope, x[0]},
                new double[]{y[0], y[0] - ySlope, y[0] - ySlope, y[0]}, 
                new double[]{z[0], z[0], z[0] - zSlope, z[0] - zSlope}, 
                texture.getSubimage(0, 0, 1, 1));
        
        drawPolys[0][0] = new DrawPolygon(camera, 
                new double[]{x[0]/4, x[1] /4, x[2] /4, x[3]/4},
                new double[]{y[0]/4, y[1] /4, y[2] /4, y[3]/4}, 
                new double[]{z[0]/4, z[1]/4, z[2] /4, z[3] /4}, 
                texture.getSubimage(0, 0, 1, 1));*/
        drawPolys[0][0] = new DrawPolygon(camera, 
                new double[]{x[0],x[0] + xSlope, x[0] +xSlope,x[0]},
                new double[]{y[0],y[0],y[0] + ySlope,y[0] + ySlope}, 
                new double[]{z[0] + zSlope,z[0],z[0],z[0] + zSlope}, 
                texture.getSubimage(0, 0, 1, 1));
        
        for(int r = 0; r < texture.getWidth(); r++){
            if(r>0){
                drawPolys[r][0] = new DrawPolygon(camera,
                        new double[]{drawPolys[r-1][0].getX()[2], drawPolys[r-1][0].getX()[2], drawPolys[r-1][0].getX()[2], drawPolys[0][r-1].getX()[2]},
                        new double[]{drawPolys[r-1][0].getY()[2], drawPolys[r-1][0].getY()[2], drawPolys[r-1][0].getY()[2] + ySlope, drawPolys[r-1][0].getY()[2] + ySlope},
                        new double[]{drawPolys[r-1][0].getZ()[2] + zSlope, drawPolys[r-1][0].getZ()[2], drawPolys[r-1][0].getZ()[2], drawPolys[r-1][0].getZ()[2] + zSlope},
                        texture.getSubimage(r, 0, 1, 1));
            }
            for(int c = 1; c < texture.getHeight(); c++){
                drawPolys[r][c] = new DrawPolygon(camera,
                        new double[]{drawPolys[r][c-1].getX()[1], drawPolys[r][c-1].getX()[1] + xSlope, drawPolys[r][c-1].getX()[1] + xSlope, drawPolys[r][c-1].getX()[1]},
                        new double[]{drawPolys[r][c-1].getY()[1], drawPolys[r][c-1].getY()[1], drawPolys[r][c-1].getY()[1] + ySlope, drawPolys[r][c-1].getY()[1] + ySlope},
                        new double[]{drawPolys[r][c-1].getZ()[1] + zSlope, drawPolys[r][c-1].getZ()[1], drawPolys[r][c-1].getZ()[1], drawPolys[r][c-1].getZ()[1] + zSlope},
                        texture.getSubimage(r, c, 1, 1));
            }
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
            
            //newX[i] = (width / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
            //newY[i] = (height / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
            
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
            //newX[i] = x[i];
            //newY[i] = y[i];
        }
        for(int r=0; r < texture.getHeight(); r++){
            for(int c=0; c < texture.getHeight(); c++){
                drawPolys[r][c].update(width, height);
            }
        }
        //drawPolys[0][0].update(width, height);
        /*
        for(int r = 0; r < drawPolys.length; r++){
            for(int c = 0; c < drawPolys[0].length; c++){
                drawPolys[r][c].
            }
        }*/
    }
    
    public void render(Graphics g, int width, int height){
        if (rendering) {
            if (outlines) {
                g.setColor(Color.BLACK);
                g.drawPolygon(polygon);
            }
            
            
            //g.setClip(polygon);
            for(int r=0; r < texture.getHeight(); r++){
                for(int c=0; c < texture.getHeight(); c++){
                drawPolys[r][c].render(g);
                }
            }
            //drawPolys[0][0].render(g);
            //Graphics2D g2d = (Graphics2D) g;
            //AffineTransform backup = g2d.getTransform();
            //double angle = (Math.atan2(polygon.getBounds().y, polygon.getBounds().x));
            //AffineTransform a = AffineTransform.getRotateInstance(0, polygon.getBounds().x, polygon.getBounds().y);
            //a.shear(0, 0);
            //g2d.setTransform(a);
            /*
            double[] pixelX = new double[texture.getWidth()];
            double[] pixelY = new double[texture.getHeight()];
            double[] calcPos;
                
             for (int i = 0; i < texture.getHeight(); i++) {
                for (int j = 0; j < texture.getWidth(); j++) {
                    calcPos = camera.calculatePositionP(x[i/6] + i,y[j/6] + j, 0);
                    int color = texture.getRGB(i, j);
                    g.setColor(new Color(color));

                    pixelX[i] = (width / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
                    pixelY[i] = (height / 2 - camera.getFocusPos()[1]) + calcPos[1] * camera.getZoom();
                    g.fillRect(((int) pixelX[i] + (i * (polygon.getBounds().width / texture.getWidth()))),
                            ((int) pixelY[i] + (j * (polygon.getBounds().height / texture.getHeight()))),
                            polygon.getBounds().width / 16, polygon.getBounds().height / 16);
                }
            }*/
            /*
            double[] pixelX = new double[texture.getWidth()];
            double[] calcPos;
            
            for (int i = 0; i < texture.getWidth(); i++) {
                calcPos = camera.calculatePositionP(x[0], y[0], 0);
                pixelX[i] = (width / 2 - camera.getFocusPos()[0]) + calcPos[0] * camera.getZoom();
                g.drawImage(texture.getSubimage(i, 0, 1, texture.getHeight()), (int)pixelX[i], polygon.getBounds().y,
                        polygon.getBounds().width/16, polygon.getBounds().height, null);
            }*/
            /*
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform a = new AffineTransform();
            AffineTransform backup = g2d.getTransform();
            a.scale(.0625, .0625);
            //a.translate(polygon.getBounds().x * 16, polygon.getBounds().y * 16);
            //System.out.println(newX[0]);
            //drawPolys[0].getPolygon().translate((int)newX[0], (int)newY[0]);
 /*           a.translate(polygon.xpoints[0] * 15, polygon.ypoints[0] * 15);
            g2d.setTransform(a);
 
 /*           
            drawPoly.render(g, width, height);*/
            //g2d.setClip(polygon);
            /*
            g.setClip(polygon);
            a.translate(polygon.xpoints[0] * 15, polygon.ypoints[0] * 15);
            for(int r = 0; r < texture.getWidth(); r++){
                for(int c = 0; c < texture.getHeight(); c++){
                    //a.translate(-r * 16, -c * 16);
                    a.translate(c,r);
                    g2d.setTransform(a);
                    drawPoly.setTexture(texture.getSubimage(r, c, 1, 1));
                    drawPoly.render(g);
                }
            }
            g2d.setTransform(backup);*/
            //g.drawImage(texture, (int)xPos, (int)yPos, polygon.getBounds().width, polygon.getBounds().height, null);
            /*
            Polygon[][] pixels = new Polygon[texture.getWidth()][texture.getHeight()];
            pixels[0][0] = new Polygon();
            for(int i = 0; i < vertecies; i++){
                pixels[0][0].addPoint((int)newX[i], (int)newY[i]);
                //System.out.println(newX[i]/16);
                pixels[0][0].xpoints[i] /= 4;
            }
            dr
            //pixels[0][0].ypoints = new int[]{(int)newY[0],  (int)newY[1], (int)newY[2] / texture.getHeight(), (int)newY[3] / texture.getHeight()};
            //pixels[0][0].xpoints = new int[]{(int)newX[0], (int)newX[1] / texture.getWidth(), (int)newX[2] / texture.getWidth(), (int)newX[3]};
            //System.out.println("y =" +  (int)y[0] + " " +  (int)y[1] + " " +  (int)y[2] / texture.getHeight() + " " +  (int)y[3] / texture.getHeight());
            //System.out.println((int)x[0] + " " + (int)x[1] / texture.getWidth() + " " + (int)x[2] / texture.getWidth() + " " + (int)x[3]);
            
            int color = texture.getRGB(0, 0);
            g.setColor(new Color(color));
            g.fillPolygon(pixels[0][0]);
            g.drawPolygon(pixels[0][0]);
            
            double xSlope = y[1] - y[0] / x[1] - x[0];
            double ySlope = x[1] - x[0] / y[1] - y[0];
            /*
            for(int i = 1; i < pixels[0].length; i++){
                for(int j = 1; j < pixels.length; j++){
                    pixels[i][j] = new Polygon();
                    pixels[i][j].xpoints = new int[]{pixels[i-1][j-1].xpoints[0], pixels[i-1][j-1].xpoints[1] + (int)xSlope * i, 
                        pixels[i-1][j-1].xpoints[2] + (int)xSlope * i, pixels[i-1][j-1].xpoints[3]};
                    pixels[i][j].ypoints = new int[]{pixels[i-1][j-1].ypoints[0], pixels[i-1][j-1].ypoints[1], 
                        pixels[i-1][j-1].ypoints[2] + (int)ySlope * j, pixels[i-1][j-1].ypoints[3] + (int)ySlope * j};
                    int color = texture.getRGB(i, j);
                    g.setColor(new Color(color));
                    g.fillPolygon(pixels[i][j]);
                }
            }*/
            //g.drawImage(texture, polygon.getBounds().x, polygon.getBounds().y,polygon.getBounds().width,polygon.getBounds().height, null);
            //g2d.setTransform(backup);
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
