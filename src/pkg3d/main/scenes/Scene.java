package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.entities.Entity;
import pkg3d.main.entities.EntityManager;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
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
    
    private BufferedImage backgroundImage;
    
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
        SceneLoader sceneLoader = new SceneLoader(main, polygonManager, controller.getCamera(), "scene3");
        sceneLoader.addGround(main, polygonManager, controller.getCamera(), 8, 8);
        ArrayList<Shape> shapes = sceneLoader.getShapes();
        for(Shape s: shapes){
            polygonManager.addShape(s);
        }
        ArrayList<Entity> entities = sceneLoader.getEntities();
        for(Entity e: entities){
            entityManager.addEntity(e);
        }
        backgroundImage = sceneLoader.getBackgroundImage();
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
        g.setColor(new Color(20, 20, 60));
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        if(backgroundImage != null){
            g.drawImage(backgroundImage, 0, 0, main.getWidth(), main.getHeight(), null);
        }
        //draws all polygons
        polygonManager.render(g, main.getWidth(), main.getHeight());
        
        //draws crosshair
        controller.render(g);
    }
    
    public Controller getController(){
        return controller;
    }
    
    public PolygonObject polyOver(int xPos, int yPos, int zPos){
        PolygonObject over = null;
        for(int i = 0; i < polygonManager.getDrawablePolygons().size(); i++){
            PolygonObject curPoly = polygonManager.getDrawablePolygons().get(i);
            int[] x = new int[curPoly.getX().length];
            int[] y = new int[curPoly.getY().length];
            for(int j = 0; j < curPoly.getX().length; j++){
                x[j] = (int)curPoly.getX()[j];
                y[j] = (int)curPoly.getY()[j];
            }
            Polygon p = new Polygon(x, y, x.length);
            if(p.contains(xPos, yPos) && curPoly.getZ()[0] < zPos){
                if(over != null){
                    if(curPoly.getZ()[0] >= over.getZ()[0]){
                        over = curPoly;
                    }
                } else {
                    over = curPoly;
                }
            }     
        }
        return over;
    }
}
