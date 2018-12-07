package pkg3d.main.entities;

import java.awt.Graphics;
import java.util.Random;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.RectangularPrism;

/**
 * @author asile
 * basic enemy for testing purposes
 */
public class CubeEnemy extends Entity{
    
    private double xChange, yChange;
    private Random rand;
    private long lastChange, changeTimer = lastChange, changeCooldown=500;
    
    public CubeEnemy(Main main, PolygonManager polygonManager, Camera camera, double x, double y, double z){
        super(main, polygonManager, camera);
        s = new RectangularPrism(main, polygonManager, camera, x, y, z, 2, 2, 4, 
                main.getImageManager().getImage("cubeEnemy"), main.getImageManager().getImage("cubeEnemy2"));
        polygonManager.addShape(s);
        rand = new Random();
        //gives it a random direction to move
        xChange = rand.nextDouble()/20;
        yChange = rand.nextDouble()/20;
    }

    @Override
    public void update() {
        
        move(xChange,yChange,0);
        checkRemove();
        changeTimer += System.currentTimeMillis() - lastChange;
        lastChange = System.currentTimeMillis();
        if(changeTimer > changeCooldown){
            if(rand.nextBoolean()){
                xChange =  rand.nextDouble()/5;
                yChange =  rand.nextDouble()/5;
            } else {
                xChange =  -rand.nextDouble()/5;
                yChange =  -rand.nextDouble()/5;
            }
            changeTimer=0;
        }
    }

    @Override
    public void render(Graphics g) {
    }
}
