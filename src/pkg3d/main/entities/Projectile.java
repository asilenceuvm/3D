package pkg3d.main.entities;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 *
 * @author asile
 */
public class Projectile extends Entity{

    public Projectile(Main main, PolygonManager polygonManager, Camera camera, double x, double y, double z) {
        super(main, polygonManager, camera);
        s = new RectangularPrism(main, polygonManager, camera, x, y, z, 1, 1, 1, main.getImageManager().getImage("tile4"));
        polygonManager.addShape(s);
    }
    
    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
