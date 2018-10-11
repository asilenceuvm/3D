package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;

/**
 *
 * @author asile
 */
public class MainState extends State{
    
    private Main main;
    
    private PolygonManager polygonManager;
    private Camera camera;
    
    public MainState(Main main){
        this.main = main;
        
        camera = new Camera(new double[]{-5, 0, 10}, new double[]{-5, -3, 0}, main.getWidth(), main.getHeight(), main.getMouseManager());
        
        polygonManager = new PolygonManager(camera);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2},  new double[]{0, 0, 0, 0}, Color.red);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2},  new double[]{3, 3, 3, 3}, Color.red);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 0, 0},  new double[]{0, 0, 3, 3}, Color.green);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{2, 2, 2, 2},  new double[]{0, 0, 3, 3}, Color.orange);
        polygonManager.addPolygon(new double[]{0, 0, 0, 0}, new double[]{0, 2, 2, 0},  new double[]{0, 0, 3, 3}, Color.blue);
        polygonManager.addPolygon(new double[]{2, 2, 2, 2}, new double[]{0, 2, 2, 0},  new double[]{0, 0, 3, 3}, Color.cyan);
        polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{0, 4, 4, 0},  new double[]{0, 0, 6, 6}, Color.WHITE);
    }
    
    @Override
    public void update() {
        camera.update();
        polygonManager.update(main.getWidth(), main.getHeight());
        movement();
    }
    public void movement(){
        boolean[] keys = new boolean[4];
        if(main.getKeyManager().getWPressed()){
            keys[0] = true;
        }
        if(main.getKeyManager().getAPressed()){
            keys[1] = true;
        }
        if(main.getKeyManager().getSPressed()){
            keys[2] = true;
        }
        if(main.getKeyManager().getDPressed()){
            keys[3] = true;
        }
        
        if(main.getKeyManager().getWPressed() == false){
            keys[0] = false;
        }
        if(main.getKeyManager().getAPressed() == false){
            keys[1] = false;
        }
        if(main.getKeyManager().getSPressed() == false){
            keys[2] = false;
        }
        if(main.getKeyManager().getDPressed() == false){
            keys[3] = false;
        }
        
        camera.move(keys, .5);
    }

    @Override
    public void render(Graphics g) {
        polygonManager.render(g);
    }
    
}
