package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import pkg3d.main.Main;
import pkg3d.main.entities.CubeEnemy;
import pkg3d.main.entities.Entity;
import pkg3d.main.entities.EntityManager;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
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
    private EntityManager entityManager;
    
    private Controller controller;
    
    private Shape cube, cube2, cube3;
    private Shape box, box2;
    private Shape pyramid;
    
    public MainState(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(controller.getCamera());
        
        cube = new RectangularPrism(polygonManager, controller.getCamera(), 0, 0, 0, 4,4,4, Color.lightGray);
        cube2 = new RectangularPrism(polygonManager, controller.getCamera(), -18, -28, -18, 36,4,36, Color.cyan);
        cube3 = new RectangularPrism(polygonManager, controller.getCamera(), -4, -4, -26, 36,36,4, Color.cyan);
        box = new RectangularPrism(polygonManager, controller.getCamera(),0, -8, 2, 1, 1, 1, Color.white);
        box2 = new RectangularPrism(polygonManager, controller.getCamera(),3, -8, 2, 2, 2, 2, Color.white);
        //pyramid = new Pyramid(controller.getCamera(), 4, 4, 4.1, 4,4, Color.lightGray);
        
        polygonManager.addShape(cube);
        polygonManager.addShape(cube2);
        
        //polygonManager.addShape(cube3);
        //polygonManager.addShape(box);
        
        PolygonObject p = new PolygonObject(controller.getCamera(), new double[]{5,5,9,9}, new double[]{5, 9, 9, 5}, 
                new double[]{5,5,0,0},  new Color(.2f,.3f,.4f,.4f));
        p.setOpaque(false);
        //polygonManager.addPolygon(p);
        //polygonManager.addShape(pyramid);
        
        //System.out.println(polygonManager.getShapes().get(0).getPlanes()[0].getIntersection(new Vector(0,0,1), new double[]{-5,-5,-5})[2]);
        
        lightManager = new LightManager(controller.getCamera());
        lightManager.addLightSource(0, 12, 2, .5);
        lightManager.update(polygonManager);
        
        entityManager = new EntityManager();
        
        Entity e1 = new CubeEnemy(main, (RectangularPrism)box, 1 ,1, 1);
        entityManager.addEntity(e1);
        Entity e2 = new CubeEnemy(main, (RectangularPrism)box2, 1 ,1, 1);
        entityManager.addEntity(e2);
        
        for(Entity e: entityManager.getEntities()){
            polygonManager.addShape(e.getShape());
        }
    }
    
    @Override
    public void update() {
        controller.update();
        polygonManager.update(main.getWidth(), main.getHeight());
        lightManager.update(polygonManager);
        entityManager.update(polygonManager);
    }

    @Override
    public void render(Graphics g) {
        polygonManager.render(g);
        controller.render(g);
    }
    
}
