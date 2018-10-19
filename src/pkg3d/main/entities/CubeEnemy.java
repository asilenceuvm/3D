package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
/**
 *
 * @author asile
 */
public class CubeEnemy extends Entity{
    
    public CubeEnemy(RectangularPrism rp, double x, double y, double z){
        super(rp, x, y, z);
    }

    @Override
    public void update() {
        move(.01,0,0);
    }

    @Override
    public void render(Graphics g) {
    }
}
