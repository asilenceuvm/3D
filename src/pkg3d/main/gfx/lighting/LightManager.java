package pkg3d.main.gfx.lighting;

import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.Plane;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 *
 * @author asile
 */
public class LightManager {
    
    private ArrayList<LightSource> lightSources;
    private ArrayList<Shadow> shadows;
    
    private Camera camera;
    
    public LightManager(Camera camera){
        lightSources = new ArrayList<>();
        shadows = new ArrayList<Shadow>();
        
        this.camera = camera;
    }
    
    public void addLightSource(double x, double y, double z, double brightness){
        lightSources.add(new LightSource(x, y, z, brightness));
    }
    /*
    public void update(PolygonManager polygonManager){
        for(int i = 0; i < lightSources.size(); i++){
            polygonManager.calcLighting(lightSources.get(i).getX(), lightSources.get(i).getY(), lightSources.get(i).getZ());
            for(Shape s: polygonManager.getShapes()){
                double[][] points = new double[lightSources.get(i).getRays(s).length][3];
                double[] x = new double[lightSources.get(i).getRays(s).length];
                double[] y = new double[lightSources.get(i).getRays(s).length];
                double[] z = new double[lightSources.get(i).getRays(s).length];
                for(int j = 0; j < lightSources.get(i).getRays(s).length; j++){
                    for(Plane p: s.getPlanes()){
                        points[j] = p.getIntersection(lightSources.get(i).getRays(s)[j], 
                             new double[]{lightSources.get(i).getX(), lightSources.get(i).getY(), lightSources.get(i).getZ()});
                        x[j] = points[j][0];
                        y[j] = points[j][1];
                        z[j] = points[j][2];
                        //System.out.println(x[j]);
                        //System.out.println(y[j]);
                        //System.out.println(z[j]);
                    }
                }
                for(int j = 0; j < x.length; j++){
                    shadows.add(new Shadow(camera, x, y, z));
                }
            }
            lightSources.get(i).setY(lightSources.get(i).getY() - .01);
        }
        
    }
    */
    public void update(PolygonManager polygonManager){
        for(int i = 0; i < lightSources.size(); i++){
            polygonManager.calcLighting(lightSources.get(i).getX(), lightSources.get(i).getY(), lightSources.get(i).getZ());
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < shadows.size(); i++){
            shadows.get(i).render(g);
        }
    }
    
    public ArrayList<Shadow> getShadows(){
        return shadows;
    }
    
}
