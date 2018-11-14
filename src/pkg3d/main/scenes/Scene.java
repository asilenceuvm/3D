package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.entities.CubeEnemy;
import pkg3d.main.entities.Entity;
import pkg3d.main.entities.EntityManager;
import pkg3d.main.gfx.ImageLoader;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;
import pkg3d.main.input.Controller;

/**
 * @author asile
 * handles the main game enviornment
 */
public class Scene {
    
    private Main main;
    
    private PolygonManager polygonManager;
    private LightManager lightManager;
    private EntityManager entityManager;
    
    private Controller controller;
    
    //temp
    private Shape box, box2;
    
    //most of this stuff should be loaded in the scene file
    //will be changed later
    public Scene(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(controller.getCamera());
        
        //box = new RectangularPrism(polygonManager, controller.getCamera(),0, -8, 2, 1, 1, 1, ImageLoader.loadImage("texture4.png"));
        //box2 = new RectangularPrism(polygonManager, controller.getCamera(),3, -8, 2, 2, 2, 2, ImageLoader.loadImage("texture4.png"));
        
        //lighting
        lightManager = new LightManager(controller.getCamera());
        lightManager.addLightSource(0, 12, 2, .5);
        lightManager.update(polygonManager);
        
        //entities
        entityManager = new EntityManager();
        
        //Entity e1 = new CubeEnemy(main, (RectangularPrism)box);
        //e//ntityManager.addEntity(e1);
        //Entity e2 = new CubeEnemy(main, (RectangularPrism)box2);
        //entityManager.addEntity(e2);
        
        for(Entity e: entityManager.getEntities()){
            //polygonManager.addShape(e.getShape());
        }
        //polygonManager.addPolygon(new PolygonObject(controller.getCamera(), new double[]{0,1,1,0},
         //       new double[]{0,0,1,1}, 
         //       new double[]{0,0,0,0}, ImageLoader.loadImage("texture4.png")));
        //load scene from file
        SceneLoader sceneLoader = new SceneLoader();
        ArrayList<Shape> shapes = sceneLoader.loadFile(polygonManager, controller.getCamera());
        for(Shape s: shapes){
            polygonManager.addShape(s);
        }
    }
    
    //updates in game logic
    public void update(){
        controller.update();
        polygonManager.update(main.getWidth(), main.getHeight());
        lightManager.update(polygonManager);
        entityManager.update(polygonManager);
    }
    
    
    public void render(Graphics g){
        //background color
        g.clipRect(0, 0, main.getWidth(), main.getHeight());
        g.setColor(new Color(140, 180, 180));
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        //draws all polygons
        polygonManager.render(g, main.getWidth(), main.getHeight());
        //draws crosshair
        controller.render(g);
    }
    
}
