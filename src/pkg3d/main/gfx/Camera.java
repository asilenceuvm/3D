package pkg3d.main.gfx;

import java.awt.Polygon;
import pkg3d.main.Main;
import pkg3d.main.gfx.object.PlaneObject;
import pkg3d.main.input.MouseManager;

/**
 * @author asile
 * handles most of the 3d graphics
 */
public class Camera {
    
    private Main main;
    private MouseManager mouseManager;
    private Utils utils;
    
    //position of camera and where it is looking
    private double[] position;
    private double[] viewPosition;

    //helper variables used for math
    private Vector viewVector;
    private Vector directionVector;
    private Vector planeVector1, planeVector2;
    private Vector drawVector1, drawVector2;
    private PlaneObject p;
    private double[] calcFocusPos;
    
    //parametric variable t
    private double t;
    
    //angle camera is looking
    private double verticalLook = -0.8, horizontalLook = 0;
    //camera zoom
    public static final double DEFAULT_ZOOM=1000;
    private double zoom = 1000;
    //speed camera rotates at
    private double horizontalRotationSpeed = 1100, verticalRotationSpeed = 2200;
    
    private Polygon screenPoly;
    
    public Camera(Main main, double[] position, double[] viewPosition, MouseManager mouseManager) {
        this.main = main;
        this.position = position;
        this.viewPosition = viewPosition;
        
        this.mouseManager = mouseManager;
        utils = new Utils();
        
        screenPoly = new Polygon();
        screenPoly.addPoint(0, 0);
        screenPoly.addPoint(0, main.getHeight());
        screenPoly.addPoint(main.getWidth(), main.getHeight());
        screenPoly.addPoint(main.getWidth(), 0);
    }

    
    
    //movement of camera rotation, ex moving mouse
    public void rotateMove(double difX, double difY) {
        difY *= 6 - Math.abs(verticalLook) * 5;
        verticalLook -= difY / verticalRotationSpeed;
        horizontalLook += difX / horizontalRotationSpeed;

        if (verticalLook > 0.999) {
            verticalLook = 0.999;
        }

        if (verticalLook < -0.999) {
            verticalLook = -0.999;
        }
        updateView();
        utils.centerMouse(main.getWidth(), main.getHeight());
    }
    
    public void rotateFree(double difX, double difY){
        difY *= 6 - Math.abs(verticalLook) * 5;
        verticalLook -= difY / verticalRotationSpeed;
        horizontalLook += difX / horizontalRotationSpeed;

        if (verticalLook > 0.999) {
            verticalLook = 0.999;
        }

        if (verticalLook < -0.999) {
            verticalLook = -0.999;
        }
        updateView();
    }
    
    //helper method to move camera
    public void moveTo(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
        updateView();
    }
    
    //updates the view position 
    private void updateView() {
        double r = Math.sqrt(1 - (verticalLook * verticalLook));
        viewPosition[0] = position[0] + r * Math.cos(horizontalLook);
        viewPosition[1] = position[1] + r * Math.sin(horizontalLook);
        viewPosition[2] = position[2] + verticalLook;
    }
    
    //calculates the drawing for the polygons
    public double[] calculatePositionP(double x, double y, double z) {
        double[] projP = getProj(position, viewPosition, x, y, z, p);
        double[] drawP = getDrawP(projP[0], projP[1], projP[2]);

        return drawP;
    }
    
    //gets the intersection of the vector and the given plane by using parametric equations
    private double[] getProj(double[] ViewFrom, double[] ViewTo, double x, double y, double z, PlaneObject p) {
        Vector viewToPoint = new Vector(x - ViewFrom[0], y - ViewFrom[1], z - ViewFrom[2]);

        t = (p.getNewVector().getX() * p.getPoints()[0] + p.getNewVector().getY() * p.getPoints()[1] + p.getNewVector().getZ() * p.getPoints()[2]
                - (p.getNewVector().getX() * ViewFrom[0] + p.getNewVector().getY() * ViewFrom[1] + p.getNewVector().getZ() * ViewFrom[2]))
                / (p.getNewVector().getX() * viewToPoint.getX() + p.getNewVector().getY() * viewToPoint.getY() + p.getNewVector().getZ() * viewToPoint.getZ());
        x = ViewFrom[0] + viewToPoint.getX() * t;
        y = ViewFrom[1] + viewToPoint.getY() * t;
        z = ViewFrom[2] + viewToPoint.getZ() * t;
        return new double[]{x, y, z};
    }

    //calculates the drawing plane
    private double[] getDrawP(double x, double y, double z) {
        double drawX = drawVector2.getX() * x + drawVector2.getY() * y + drawVector2.getZ() * z;
        double drawY = drawVector1.getX() * x + drawVector1.getY() * y + drawVector1.getZ() * z;
        return new double[]{drawX, drawY};
    }
    
    //return the vector of the rotation of the camera
    private Vector getRotationVector() {
        double dx = Math.abs(position[0] - viewPosition[0]);
        double dy = Math.abs(position[1] - viewPosition[1]);
        double xRot, yRot;
        xRot = dy / (dx + dy);
        yRot = dx / (dx + dy);

        if (position[1] > viewPosition[1]) {
            xRot = -xRot;
        }
        if (position[0] < viewPosition[0]) {
            yRot = -yRot;
        }
        Vector v = new Vector(xRot, yRot, 0);
        return v;
    }
    
    //called every frame to recalculate focus position and call the rotation for mouse movement
    public void update() {
        viewVector = new Vector(viewPosition[0] - position[0], viewPosition[1] - position[1], viewPosition[2] - position[2]);
        directionVector = new Vector(1, 1, 1);
        planeVector1 = Vector.crossProduct(viewVector, directionVector);
        planeVector2 = Vector.crossProduct(viewVector, planeVector1);
        p = new PlaneObject(planeVector1, planeVector2, viewPosition);
        
        Vector rotationVector = getRotationVector();
        drawVector1 = Vector.crossProduct(viewVector, rotationVector);
        drawVector2 = Vector.crossProduct(viewVector, drawVector1);

        calcFocusPos = calculatePositionP(viewPosition[0], viewPosition[1], viewPosition[2]);
        calcFocusPos[0] *= zoom;
        calcFocusPos[1] *= zoom;
        
        rotateMove(mouseManager.getDeltaX() + 9, mouseManager.getDeltaY() + 38);
        //9 and 38 account for the mouse size vs the actual half point on the screen
    }
    
    public void update(double x, double y){
        viewVector = new Vector(viewPosition[0] - position[0], viewPosition[1] - position[1], viewPosition[2] - position[2]);
        directionVector = new Vector(1, 1, 1);
        planeVector1 = Vector.crossProduct(viewVector, directionVector);
        planeVector2 = Vector.crossProduct(viewVector, planeVector1);
        p = new PlaneObject(planeVector1, planeVector2, viewPosition);
        
        Vector rotationVector = getRotationVector();
        drawVector1 = Vector.crossProduct(viewVector, rotationVector);
        drawVector2 = Vector.crossProduct(viewVector, drawVector1);

        calcFocusPos = calculatePositionP(viewPosition[0], viewPosition[1], viewPosition[2]);
        calcFocusPos[0] *= zoom;
        calcFocusPos[1] *= zoom;
        
        rotateFree(x, y);
    }
    
    //getters & setters
    public double[] getPosition() {
        return position;
    }
    public void setPosition(double[] position){
        this.position = position;
    }
    
    public double[] getViewPosition() {
        return viewPosition;
    }
    public void setViewPosition(double[] viewPosition){
        this.viewPosition = viewPosition;
    }
    public double getT() {
        return t;
    }

    public double[] getFocusPos() {
        return calcFocusPos;
    }

    public double getZoom() {
        return zoom;
    }
    public void setZoom(double zoom){
        this.zoom = zoom;
    }
    
    public Polygon getScreenPoly(){
        return screenPoly;
    }
}
