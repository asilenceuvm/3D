package pkg3d.main.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import pkg3d.main.Main;
import pkg3d.main.entities.CubeEnemy;
import pkg3d.main.entities.Entity;
import pkg3d.main.entities.EntityManager;
import pkg3d.main.gfx.lighting.LightManager;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.PolygonObject;
import pkg3d.main.gfx.object.shapes.Shape;
import pkg3d.main.input.Controller;
import pkg3d.main.states.State;

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
    
    private int playTime = 61;
    private long lastTick, tickTimer = lastTick;
    
    private Random rand;
    private long lastSpawn, spawnTimer = lastSpawn, spawnCooldown=1000;
    
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
        sceneLoader.addRects(main, polygonManager, controller.getCamera(), 15, 8, 20, 20);
        sceneLoader.generateWalls(main, polygonManager, controller.getCamera());
        ArrayList<Shape> shapes = sceneLoader.getShapes();
        for(Shape s: shapes){
            polygonManager.addShape(s);
        }
        ArrayList<Entity> entities = sceneLoader.getEntities();
        for(Entity e: entities){
            entityManager.addEntity(e);
        }
        //entityManager.addEntity(new CubeEnemy(main, polygonManager, controller.getCamera(),  15,  15,  1));
        backgroundImage = sceneLoader.getBackgroundImage();
        
        rand = new Random();
    }
    
    //updates in game logic
    public void update(){
        controller.update();
        polygonManager.update(main.getWidth(), main.getHeight());
        lightManager.update(polygonManager);
        entityManager.update(polygonManager);
        checkEnd();
        addEnemy();
    }
    
    private void checkEnd(){
        tickTimer += System.currentTimeMillis() - lastTick;
        lastTick = System.currentTimeMillis();
        if(tickTimer > 1000){
            playTime--;
            tickTimer=0;
        }
        if(playTime <= 0){
            State.setCurState(main.getDeathState());
        }
    }
    
    private void addEnemy(){
        spawnTimer += System.currentTimeMillis() - lastSpawn;
        lastSpawn = System.currentTimeMillis();
        if(spawnTimer > spawnCooldown){
            double x = rand.nextDouble() * 8 * 16;
            double y = rand.nextDouble() * 8 * 16;
            entityManager.addEntity(new CubeEnemy(main, polygonManager, controller.getCamera(), x, y, 0));
            spawnTimer=0;
        }
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
        
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18)); 
        g.drawString("Time Left: " + Integer.toString(playTime), 20, 20);
        g.drawString("Score: " + Integer.toString(Main.score), 20 ,main.getHeight()-20);
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
    
    public boolean intersection(double x, double y, double length, double width, double z){
        return polygonManager.checkIntersect(x, y, length, width, z);
    }
    
    public void setTime(int playTime){
        this.playTime = playTime;
    }
}
