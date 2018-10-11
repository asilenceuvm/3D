package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.input.Controller;

/**
 *
 * @author asile
 */
public class MainState extends State{
    
    private Main main;
    
    private PolygonManager polygonManager;
    private Controller controller;
    
    public MainState(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(controller.getCamera());
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
        controller.update();
        polygonManager.update(main.getWidth(), main.getHeight());
    }

    @Override
    public void render(Graphics g) {
        polygonManager.render(g);
    }
    
}
