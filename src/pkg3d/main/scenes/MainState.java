package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import pkg3d.main.Main;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
import pkg3d.main.gfx.object.shapes.Pyramid;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;
import pkg3d.main.input.Controller;

/**
 *
 * @author asile
 */
public class MainState extends State{
    
    private Main main;
    
    private PolygonManager polygonManager;
    private LightManager lightManager;
    
    private Controller controller;
    
    private Shape cube, cube2;
    private Shape box;
    private Shape pyramid;
    
    public MainState(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(controller.getCamera());
        
        cube = new RectangularPrism(controller.getCamera(), 0, 0, 0, 4,4,4, Color.lightGray);
        cube2 = new RectangularPrism(controller.getCamera(), -18, -16, -18, 36,4,36, Color.cyan);
        box = new RectangularPrism(controller.getCamera(), 1, -3, 2, 1, 1, 1, Color.white);
        //pyramid = new Pyramid(controller.getCamera(), 4, 4, 4.1, 4,4, Color.lightGray);
        
        polygonManager.addShape(cube);
        polygonManager.addShape(cube2);
        polygonManager.addShape(box);
        
        PolygonObject p = new PolygonObject(controller.getCamera(), new double[]{5,5,9,9}, new double[]{5, 9, 9, 5}, 
                new double[]{5,5,0,0},  new Color(.2f,.3f,.4f,.4f));
        p.setOpaque(false);
        //polygonManager.addPolygon(p);
        //polygonManager.addShape(pyramid);
        
        lightManager = new LightManager();
        lightManager.addLightSource(1, -3, 2, .5);
        
    }
    
    @Override
    public void update() {
        controller.update();
        polygonManager.update(main.getWidth(), main.getHeight());
        lightManager.update(polygonManager);
    }

    @Override
    public void render(Graphics g) {
        polygonManager.render(g);
    }
    
}
