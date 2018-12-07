package pkg3d.main.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.Utils;
import pkg3d.main.gfx.Vector;
import pkg3d.main.game.Gun;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
import pkg3d.main.states.State;

/**
 * @author asile
 * class to handle higher level user input
 */
public class Controller {
    
    private Main main;
    private Camera camera;
    private Utils utils;    
    private PolygonManager polygonManager;
    
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
    
    private PolygonObject polyOver;
    private double deltaX, deltaY, deltaZ;
    private double oldZ;
    
    private double xBound = 2;
    private double boundWidth=1;
    private double yBound = 2;
    private double boundHeight=1;
    
    public Controller(Main main){
        this.main = main;
        utils = new Utils();
        camera = new Camera(main, new double[]{5, 10, 4}, new double[]{-5, -3, 0}, main.getMouseManager());
        polygonManager = new PolygonManager(main, camera);
        
        ak = new Gun(30, 50, 100, 10, 1, false);
        deagle = new Gun(10, 100, 500, 20, 1.5, false);
        awp = new Gun(5, 100, 1000, 50, 4, true);
        guns = new Gun[3];
        guns[0] = ak;
        guns[1] = deagle;
        guns[2] = awp;
        curGun = 0;
        
        oldZ = camera.getPosition()[2];
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
        
        move(keys, .5);
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
        deltaX = 0;
        deltaY = 0;
        //deltaZ = 0;
        Vector verticalVector = new Vector(0, 0, 1);
        Vector horizontalVector = Vector.crossProduct(newViewVector, verticalVector);
        
        polyOver = main.getMainState().getCurScene().polyOver((int)camera.getPosition()[0],
                (int)camera.getPosition()[1], (int)camera.getPosition()[2]);
        
        double x; 
        double y;
        double xMove;
        double yMove;
        //free movement in debug mode
        if(debugMode){
            deltaZ=0;
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
            x = deltaX;
            y = deltaY;
            xMove = deltaX;
            yMove = deltaY;
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
            /*
            if(!onGround()){
                deltaZ-=.01;
                deltaX/=5;
                deltaY/=5;
            }
            if(onGround()){
                deltaZ = 0;
                if(main.getKeyManager().getSpaceTapped()){
                    deltaZ=.25;
                }
                if(main.getKeyManager().getShiftPressed()){
                    camera.moveTo(camera.getPosition()[0], camera.getPosition()[1] , oldZ -1);
                    speed *= .5;
                } else {
                    camera.moveTo(camera.getPosition()[0], camera.getPosition()[1], oldZ);
                    oldZ = camera.getPosition()[2];
                    speed *= 2;
                }
            }
            */
            
            if(deltaZ <= 0){
                double tempZ = camera.getPosition()[2] + deltaZ + 4;
                if(!onGround()){
                    
                }
            }
            
            x = camera.getPosition()[0];
            y = camera.getPosition()[1];
            xMove=0;
            yMove=0;
            double xMod = 1;
            double yMod = 1;
            if (deltaX >= 0) { //Moving right
                double tempX = x + deltaX + 2;
                if (!collisionXY(tempX, y)) {
                    xMove += deltaX;
                } else {
                    xMove = 0;
                    yMod = .01;
                }
            }
            if (deltaX <= 0) { //Moving right
                double tempX = x + deltaX - 2;
                if (!collisionXY(tempX, y)) {
                    xMove += deltaX;
                } else {
                    xMove = 0;
                    yMod = .01;
                }
            }
            if (deltaY >= 0) { //forward
                double tempY = y + deltaY + 2;
                if (!collisionXY(x, tempY)) {
                    yMove += deltaY;
                } else {
                    yMove = 0;
                    xMod = .01;
                }
            }
            else if (deltaY <= 0) { //forward
                double tempY = y + deltaY - 2;
                if (!collisionXY(x, tempY)) {
                    yMove += deltaY;
                } else {
                    yMove = 0;
                    xMod = .01;
                }
            }
            xMove *= xMod;
            yMove *= yMod;
        }
        
        Vector moveVector = new Vector(xMove, yMove, deltaZ);
        camera.moveTo(camera.getPosition()[0] + moveVector.getX() * speed, camera.getPosition()[1] + moveVector.getY() * speed, camera.getPosition()[2] + moveVector.getZ() * speed);
    }
    
    private boolean collisionXY(double x, double y){
        return main.getMainState().getCurScene().intersection(x, y, boundWidth, boundHeight, camera.getPosition()[2]);
    }
    
    private boolean onGround(){
        if(polyOver != null){
            if(polyOver.getZ()[0] + 5 > camera.getPosition()[2] && polyOver.getViewSide().equals("+z")){
                return true;
            }
        }
        return false;
    }
    
    //draws cross hair
    public void render(Graphics g){
        utils.drawMouseAim(g, main.getWidth(), main.getHeight(), 4);
        /*
        for(int i = 0; i < health; i++){
            g.drawImage(main.getImageManager().getImage("heart"), (i*33), main.getHeight()-48, 32,32,null);
        }*/
        drawWeaponStuff(g);
    }
    
    private void drawWeaponStuff(Graphics g){
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
    
    
    
    //getters & setters
    public Camera getCamera(){
        return camera;
    }
    public boolean getShooting(){
        return shooting;
    }
}

