package pkg3d.main.states;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import pkg3d.main.Main;
import pkg3d.main.scenes.Scene;

/**
 * @author asile
 * runs the logic for the main playing state
 * will eventually have logic for pausing, etc.
 */
public class MainState extends State{
    
    private Main main;
    private Scene scene;
    
    public MainState(Main main){
        this.main = main;
        scene = new Scene(main);
        curScene=scene;
        invisibleMouse();
    }
    
    @Override
    public void update() {
        scene.update();
    }

    @Override
    public void render(Graphics g) {
        scene.render(g);
    }
    
    //sets mouse to invisble 
    private void invisibleMouse() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "InvisibleCursor");
        main.getFrame().setCursor(invisibleCursor);
    }
    
}
