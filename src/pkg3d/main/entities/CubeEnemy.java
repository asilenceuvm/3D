package pkg3d.main.entities;

import java.awt.Graphics;
import java.util.Random;
import pkg3d.main.Main;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
/**
 *
 * @author asile
 */
public class CubeEnemy extends Entity{
    
    private double xChange, yChange;
    private Random rand;
    
    public CubeEnemy(Main main, RectangularPrism rp){
        super(main, rp);
        rand = new Random();
        xChange = rand.nextDouble()/20;
        yChange = rand.nextDouble()/20;
    }

    @Override
    public void update() {
        move(xChange,0,yChange);
        checkRemove(main.getWidth(), main.getHeight());
    }

    @Override
    public void render(Graphics g) {
    }
}
