package pkg3d.main.gfx.object;

/**
 *
 * @author asile
 */
public abstract class Shape {
    
    protected PolygonObject[] polys;
    
    public abstract void update();
    public abstract void render();
    
    public PolygonObject[] getPolys(){
        return polys;
    }
}
