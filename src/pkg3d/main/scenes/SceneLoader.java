package pkg3d.main.scenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg3d.main.Main;
import pkg3d.main.entities.CubeEnemy;
import pkg3d.main.entities.Entity;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.Plane;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 * @author asile
 * Loads the scene from an input file
 */
public class SceneLoader {
    
    private BufferedImage backgroundImage;
    private ArrayList<Shape> shapes = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    
    public SceneLoader(Main main, PolygonManager p, Camera c, String scene){
        String object;
        double x;
        double y;
        double z;
        double xWidth;
        double yHeight;
        double zDepth;
        String viewSide;
        String texture;
        try {
            File file = new File("res\\scenes\\" + scene + ".txt");
            Scanner sc = new Scanner(file);
            
            while (sc.hasNext()){
                object = sc.next();
                //enviornment
                //background
                if(object.equals("BG")){
                    texture = sc.next();
                    backgroundImage = main.getImageManager().getImage(texture);
                }
                //rectangular prism
                if(object.equals("RP")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    texture = sc.next();
                    shapes.add(new RectangularPrism(main, p, c, x, y, z ,xWidth, yHeight, zDepth, main.getImageManager().getImage(texture)));
                }
                //plane
                else if(object.equals("P")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    texture = sc.next();
                    viewSide = sc.next();
                    shapes.add(new Plane(main, p, c, x, y, z ,xWidth, yHeight, zDepth, main.getImageManager().getImage(texture), viewSide));
                }
                //entities
                //cube enemy
                else if(object.equals("CE")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    texture = sc.next();
                    RectangularPrism rp = (new RectangularPrism(main, p, c, x, y, z ,xWidth, yHeight, zDepth,  main.getImageManager().getImage(texture)));
                    shapes.add(rp);
                    entities.add(new CubeEnemy(main, rp));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addGround(Main main, PolygonManager p, Camera camera, int length, int width){
        for(int r = 0; r < width; r++){
            for(int c = 0; c < length; c++){
                shapes.add(new Plane(main, p, camera, r*16, c*16, 0, 16, 16, 0, main.getImageManager().getImage("tile1"), "+z"));
            }
        }
    }
    
    public BufferedImage getBackgroundImage(){
        return backgroundImage;
    }
    public ArrayList<Shape> getShapes(){
         return shapes;
    }
    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
