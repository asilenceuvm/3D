package pkg3d.main.input;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.Utils;
import pkg3d.main.gfx.Vector;

/**
 * @author asile
 * class to handle higher level user input
 */
public class Controller {
    
    private Main main;
    private Camera camera;
    private Utils utils;    
    
    private boolean debugMode;
    
    public Controller(Main main){
        this.main = main;
        utils = new Utils();
        camera = new Camera(main, new double[]{5, 10, 4}, new double[]{-5, -3, 0}, main.getMouseManager());
    }
    
    //updates movement
    public void update(){
        boolean[] keys = new boolean[4];
        if (main.getKeyManager().getWPressed()) {
            keys[0] = true;
        }
        if (main.getKeyManager().getAPressed()) {
            keys[1] = true;
        }
        if (main.getKeyManager().getSPressed()) {
            keys[2] = true;
        }
        if (main.getKeyManager().getDPressed()) {
            keys[3] = true;
        }
        
        if (main.getKeyManager().getWPressed() == false) {
            keys[0] = false;
        }
        if (main.getKeyManager().getAPressed() == false) {
            keys[1] = false;
        }
        if (main.getKeyManager().getSPressed() == false) {
            keys[2] = false;
        }
        if (main.getKeyManager().getDPressed() == false) {
            keys[3] = false;
        }
        
        move(keys, 1);
        
        //temp code
        debugMode = (main.getKeyManager().getOPressed());
        
        camera.update();
    }
    
    //main method to determine movement
    public void move(boolean[] keys, double speed) {
        //helper variables
        Vector newViewVector = new Vector(camera.getViewPosition()[0] - camera.getPosition()[0], 
                camera.getViewPosition()[1] - camera.getPosition()[1], camera.getViewPosition()[2] - camera.getPosition()[2]);
        double deltaX = 0, deltaY = 0, deltaZ = 0;
        Vector verticalVector = new Vector(0, 0, 1);
        Vector horizontalVector = Vector.crossProduct(newViewVector, verticalVector);
        
        //free movement in debug mode
        if(debugMode){
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
        } else { //going to be used for regular movement like collision
            if (keys[0]) {
                deltaX += newViewVector.getX();
                deltaY += newViewVector.getY();
            }

            if (keys[2]) {
                deltaX -= newViewVector.getX();
                deltaY -= newViewVector.getY();
            }

            if (keys[1]) {
                deltaX += horizontalVector.getX();
                deltaY += horizontalVector.getY();
            }

            if (keys[3]) {
                deltaX -= horizontalVector.getX();
                deltaY -= horizontalVector.getY();
            }
            
        }
        Vector moveVector = new Vector(deltaX, deltaY, deltaZ);
        camera.moveTo(camera.getPosition()[0] + moveVector.getX() * speed, camera.getPosition()[1] + moveVector.getY() * speed, camera.getPosition()[2] + moveVector.getZ() * speed);
    }
    
    //draws cross hair
    public void render(Graphics g){
        utils.drawMouseAim(g, main.getWidth(), main.getHeight(), 4);
    }
    
    //getters & setters
    public Camera getCamera(){
        return camera;
    }
}
