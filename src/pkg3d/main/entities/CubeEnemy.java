package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
/**
 *
 * @author asile
 */
public class CubeEnemy extends Entity{
    
    public CubeEnemy(Main main, RectangularPrism rp, double x, double y, double z){
        super(main, rp, x, y, z);
    }

    @Override
    public void update() {
        move(.01,0,0);
        checkRemove(main.getWidth(), main.getHeight());
    }

    @Override
    public void render(Graphics g) {
    }
}
