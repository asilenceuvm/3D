package pkg3d.main.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import javafx.scene.shape.Circle;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.Utils;
import pkg3d.main.gfx.Vector;
import pkg3d.main.game.Gun;
import pkg3d.main.states.State;

/**
 * @author asile
 * class to handle higher level user input
 */
public class Controller {
    
    private Main main;
    private Camera camera;
    private Utils utils;    
    
    private boolean debugMode;
    
    private int health = 10;
    private boolean shooting=false;
    private long lastShot, shotTimer = lastShot;
    private long lastReload, reloadTimer = lastReload, reloadCooldown=2000;
    private boolean reloading=false;
    
    private Gun ak;
    private Gun deagle;
    private Gun awp;
    private int curGun;
    private Gun[] guns;
    
    public Controller(Main main){
        this.main = main;
        utils = new Utils();
        camera = new Camera(main, new double[]{5, 10, 4}, new double[]{-5, -3, 0}, main.getMouseManager());
        
        ak = new Gun(30, 50, 100, 10, 1, false);
        deagle = new Gun(10, 100, 500, 20, 1.5, false);
        awp = new Gun(5, 100, 1000, 50, 4, true);
        guns = new Gun[3];
        guns[0] = ak;
        guns[1] = deagle;
        guns[2] = awp;
        curGun = 0;
    }
    
    //updates movement
    public void update(){
        setMove();
        
        debugMode = (main.getKeyManager().getOPressed());
        
        camera.update();
        shoot();
        
        int move = main.getMouseManager().mouseWheelMove;
        curGun += move;
        while(curGun > guns.length-1){
            curGun -= guns.length;
        }
        if(curGun < 0){
            curGun = guns.length-1;
        }
        move=0;
        main.getMouseManager().mouseWheelMove = 0;
        
        if(main.getKeyManager().getRTapped()){
            reloading = true;
        }
        if(reloading){
            reloadTimer += System.currentTimeMillis() - lastReload;
            lastReload = System.currentTimeMillis();
            if (reloadTimer > reloadCooldown) {
                guns[curGun].setBullets(guns[curGun].getMaxBullets());
                reloading = false;
                reloadTimer=0;
            }
        }
        lastReload = System.currentTimeMillis();
    }
    
    public void setMove(){
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
    }
    
    public void shoot(){
        shotTimer += System.currentTimeMillis() - lastShot;
        lastShot = System.currentTimeMillis();
        if (shotTimer > guns[curGun].getShotCooldown()) {
            if (main.getMouseManager().getLeftPressed()) {
                if (guns[curGun].getBullets() > 0) {
                    guns[curGun].setBullets(guns[curGun].getBullets()-1);
                    shooting = true;
                    shotTimer=0;
                    camera.rotateMove(0, -guns[curGun].getRecoil());
                } else {
                    shooting = false;
                }
            } else {
                shooting = false;
            }
            if (health <= 0) {
                State.setCurState(main.getDeathState());
            }
        } else {
            shooting=false;
        }
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
            if(main.getKeyManager().getSpaceTapped() & onGround()){
                deltaZ += 10;
            }
            
        }
        Vector moveVector = new Vector(deltaX, deltaY, deltaZ);
        camera.moveTo(camera.getPosition()[0] + moveVector.getX() * speed, camera.getPosition()[1] + moveVector.getY() * speed, camera.getPosition()[2] + moveVector.getZ() * speed);
    }
    
    //draws cross hair
    public void render(Graphics g){
        utils.drawMouseAim(g, main.getWidth(), main.getHeight(), 4);
        for(int i = 0; i < health; i++){
            g.drawImage(main.getImageManager().getImage("heart"), (i*33), main.getHeight()-48, 32,32,null);
        }
        g.drawImage(main.getImageManager().getImage("bullet"), main.getWidth()-48, main.getHeight()-48,32,32, null);
        g.setColor(Color.gray);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
        g.drawString(Integer.toString(guns[curGun].getBullets()), main.getWidth()-64, main.getHeight()-32);
        if(curGun == 0){
            g.drawImage(main.getImageManager().getImage("ak"), (main.getWidth()/2) - 32, main.getHeight()-128, null);
        } else if(curGun == 1){
            g.drawImage(main.getImageManager().getImage("deagle"), (main.getWidth()/2) - 32, main.getHeight()-128, null);
        } else if(curGun == 2){
            g.drawImage(main.getImageManager().getImage("awp"), (main.getWidth()/2) - 32, main.getHeight()-128, null);
        }
        
        if(main.getMouseManager().getRightPressed()){
            camera.setZoom(Camera.DEFAULT_ZOOM * guns[curGun].getZoom());
            if(guns[curGun].getScope()){
                g.drawImage(main.getImageManager().getImage("scope"), 0, 0, main.getWidth(), main.getHeight(), null);
            }
        } else {
            camera.setZoom(Camera.DEFAULT_ZOOM);
        }
    }
    
    private boolean onGround(){
        return true;
    }
    
    //getters & setters
    public Camera getCamera(){
        return camera;
    }
    public boolean getShooting(){
        return shooting;
    }
}

