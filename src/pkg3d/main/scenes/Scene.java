package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.entities.Entity;
import pkg3d.main.entities.EntityManager;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
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
    
    public Scene(Main main){
        this.main = main;
        
        controller = new Controller(main);
        
        polygonManager = new PolygonManager(main, controller.getCamera());
        
        //lighting
        lightManager = new LightManager(controller.getCamera());
        lightManager.addLightSource(0, 12, 2, .5);
        lightManager.update(polygonManager);
        
        //entities
        entityManager = new EntityManager();
        
        //load scene from file
        SceneLoader sceneLoader = new SceneLoader(main, polygonManager, controller.getCamera());
        ArrayList<Shape> shapes = sceneLoader.getShapes();
        for(Shape s: shapes){
            polygonManager.addShape(s);
        }
        ArrayList<Entity> entities = sceneLoader.getEntities();
        for(Entity e: entities){
            entityManager.addEntity(e);
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
        g.setColor(new Color(140, 200, 220));
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        //draws all polygons
        polygonManager.render(g, main.getWidth(), main.getHeight());
        
        //draws crosshair
        controller.render(g);
    }
    
    public Controller getController(){
        return controller;
    }
    
}
