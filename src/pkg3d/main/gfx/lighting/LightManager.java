package pkg3d.main.gfx.lighting;

import java.util.ArrayList;

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
}
