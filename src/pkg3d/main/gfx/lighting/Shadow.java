package pkg3d.main.gfx.lighting;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonObject;

/**
 *
 * @author asile
 */
public class Shadow {
    
    private PolygonObject shadow;
    
    public Shadow(Camera camera, double[] x, double[] y, double[] z){
        //shadow = new PolygonObject(camera, x, y, z, new Color(.2f,.3f,.4f,.4f));
        //shadow.setOpaque(false);
    }
    
    public PolygonObject getShadow(){
        return null;
    }
    
    public void render(Graphics g){
        //shadow.render(g);
    }
    
}
