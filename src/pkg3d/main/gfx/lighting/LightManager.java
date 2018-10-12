package pkg3d.main.gfx.lighting;

import java.util.ArrayList;
import pkg3d.main.gfx.object.PolygonManager;

/**
 *
 * @author asile
 */
public class LightManager {
    
    private ArrayList<LightSource> lightSources;
    
    public LightManager(){
        lightSources = new ArrayList<>();
    }
    
    public void addLightSource(double x, double y, double z, double brightness){
        lightSources.add(new LightSource(x, y, z, brightness));
    }
    
    public void update(PolygonManager polygonManager){
        for(int i = 0; i < lightSources.size(); i++){
            polygonManager.calcLighting(lightSources.get(i).getX(), lightSources.get(i).getY(), lightSources.get(i).getZ());
        }
    }
}
