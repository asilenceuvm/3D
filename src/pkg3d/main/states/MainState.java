package pkg3d.main.states;

import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.scenes.Scene;

/**
 * @author asile
 * runs the logic for the main playing state
 * will eventually have logic for pausing, etc.
 */
public class MainState extends State{
    
    private Scene scene;
    
    public MainState(Main main){
        scene = new Scene(main);
    }
    
    @Override
    public void update() {
        scene.update();
    }

    @Override
    public void render(Graphics g) {
        scene.render(g);
    }
    
}
