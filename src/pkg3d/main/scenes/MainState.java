package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.Cube;
import pkg3d.main.gfx.object.shapes.Shape;
import pkg3d.main.input.Controller;

/**
 *
 * @author asile
 */
public class MainState extends State{
    
    private Main main;
    
    private PolygonManager polygonManager;
    private Controller controller;
    
    private Shape cube;
    private Shape cube2;
    
    public MainState(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(controller.getCamera());
        
        cube = new Cube(controller.getCamera(), 0, 0, 0, 4, Color.magenta);
        cube2 = new Cube(controller.getCamera(),4,0,0,4, Color.red);
        
        polygonManager.addShape(cube);
        polygonManager.addShape(cube2);
        
        //polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{0, 4, 4, 0},  new double[]{0, 0, 6, 6}, Color.WHITE);
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
