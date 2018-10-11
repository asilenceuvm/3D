package pkg3d.main.input;

import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;

/**
 *
 * @author asile
 */
public class Controller {
    
    private Main main;
    private Camera camera;
    
    public Controller(Main main){
        this.main = main;
        
        camera = new Camera(new double[]{-5, 0, 10}, new double[]{-5, -3, 0}, main.getWidth(), main.getHeight(), main.getMouseManager());
    }
    
    public void update(){
        
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
        camera.update();
    }
    
    public Camera getCamera(){
        return camera;
    }
}
