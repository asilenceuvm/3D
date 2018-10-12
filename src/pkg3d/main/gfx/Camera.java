package pkg3d.main.gfx;

import pkg3d.main.gfx.object.Plane;
import pkg3d.main.input.MouseManager;

/**
 *
 * @author asile
 */
public class Camera {
    
    private MouseManager mouseManager;
    private Utils utils;
    
    private int width,height;
    
    
    private double[] position;
    private double[] viewPosition;

    private Vector viewVector;
    private Vector directionVector;
    private Vector planeVector1, planeVector2;
    private Vector drawVector1, drawVector2;
    private Plane p;

    private double verticalLook = -0.8, horizontalLook = 0;
    private double zoom = 1000;
    private double horizontalRotationSpeed = 900, verticalRotationSpeed = 2200;

    private double[] calcFocusPos;

    private double t;

    public Camera(double[] position, double[] viewPosition, int width, int height, MouseManager mouseManager) {
        this.position = position;
        this.viewPosition = viewPosition;
        this.width = width;
        this.height = height;
        
        this.mouseManager = mouseManager;
        utils = new Utils();
    }


    public void move(boolean[] keys, double speed) {
        Vector newViewVector = new Vector(viewPosition[0] - position[0], viewPosition[1] - position[1], viewPosition[2] - position[2]);
        double deltaX = 0, deltaY = 0, deltaZ = 0;
        Vector verticalVector = new Vector(0, 0, 1);
        Vector horizontalVector = Vector.crossProduct(newViewVector, verticalVector);
        //Vector horizontalVector = new Vector(0,0,0);
        if (keys[0]) {
            deltaX += newViewVector.getX();
            deltaY += newViewVector.getY();
            deltaZ += newViewVector.getZ();
        }

        if (keys[2]) {
            deltaX -= newViewVector.getX();
            deltaY -= newViewVector.getY();
            deltaZ -= newViewVector.getZ();
        }

        if (keys[1]) {
            deltaX += horizontalVector.getX();
            deltaY += horizontalVector.getY();
            deltaZ += horizontalVector.getZ();
        }

        if (keys[3]) {
            deltaX -= horizontalVector.getX();
            deltaY -= horizontalVector.getY();
            deltaZ -= horizontalVector.getZ();
        }
        
        Vector moveVector = new Vector(deltaX, deltaY, deltaZ);
        moveTo(position[0] + moveVector.getX() * speed, position[1] + moveVector.getY() * speed, position[2] + moveVector.getZ() * speed);
    }

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
        utils.centerMouse(width, height);
    }

    private void moveTo(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
        updateView();
    }

    private void updateView() {
        double r = Math.sqrt(1 - (verticalLook * verticalLook));
        viewPosition[0] = position[0] + r * Math.cos(horizontalLook);
        viewPosition[1] = position[1] + r * Math.sin(horizontalLook);
        viewPosition[2] = position[2] + verticalLook;
    }
    
    
    public double[] calculatePositionP(double x, double y, double z) {
        double[] projP = getProj(position, viewPosition, x, y, z, p);
        double[] drawP = getDrawP(projP[0], projP[1], projP[2]);

        return drawP;
    }

    private double[] getProj(double[] ViewFrom, double[] ViewTo, double x, double y, double z, Plane p) {
        Vector viewToPoint = new Vector(x - ViewFrom[0], y - ViewFrom[1], z - ViewFrom[2]);

        t = (p.getNewVector().getX() * p.getPoints()[0] + p.getNewVector().getY() * p.getPoints()[1] + p.getNewVector().getZ() * p.getPoints()[2]
                - (p.getNewVector().getX() * ViewFrom[0] + p.getNewVector().getY() * ViewFrom[1] + p.getNewVector().getZ() * ViewFrom[2]))
                / (p.getNewVector().getX() * viewToPoint.getX() + p.getNewVector().getY() * viewToPoint.getY() + p.getNewVector().getZ() * viewToPoint.getZ());
        x = ViewFrom[0] + viewToPoint.getX() * t;
        y = ViewFrom[1] + viewToPoint.getY() * t;
        z = ViewFrom[2] + viewToPoint.getZ() * t;
        return new double[]{x, y, z};
    }

    private double[] getDrawP(double x, double y, double z) {
        double drawX = drawVector2.getX() * x + drawVector2.getY() * y + drawVector2.getZ() * z;
        double drawY = drawVector1.getX() * x + drawVector1.getY() * y + drawVector1.getZ() * z;
        return new double[]{drawX, drawY};
    }
    
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

    public void update() {
        viewVector = new Vector(viewPosition[0] - position[0], viewPosition[1] - position[1], viewPosition[2] - position[2]);
        directionVector = new Vector(1, 1, 1);
        planeVector1 = Vector.crossProduct(viewVector, directionVector);
        planeVector2 = Vector.crossProduct(viewVector, planeVector1);
        p = new Plane(planeVector1, planeVector2, viewPosition);
        
        Vector rotationVector = getRotationVector();
        drawVector1 = Vector.crossProduct(viewVector, rotationVector);
        drawVector2 = Vector.crossProduct(viewVector, drawVector1);

        calcFocusPos = calculatePositionP(viewPosition[0], viewPosition[1], viewPosition[2]);
        calcFocusPos[0] *= zoom;
        calcFocusPos[1] *= zoom;
        
        rotateMove(mouseManager.getDeltaX()+9, mouseManager.getDeltaY() + 38);
    }
    
    
    
    public double[] getPosition() {
        return position;
    }
    
    public double[] getViewPosition() {
        return viewPosition;
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
}
