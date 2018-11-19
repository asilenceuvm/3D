package pkg3d.main.scenes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.ImageLoader;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.Plane;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 * @author asile
 * Loads the scene from an input file
 */
public class SceneLoader {
    
    
    public ArrayList<Shape> loadFile(PolygonManager p, Camera c){
        String shape;
        double x;
        double y;
        double z;
        double xWidth;
        double yHeight;
        double zDepth;
        String viewSide;
        String texture;
        ArrayList<Shape> shapes = new ArrayList<>();
        try {
            File file = new File("res\\scenes\\scene1.txt");
            Scanner sc = new Scanner(file);
            
            while (sc.hasNext()){
                shape = sc.next();
                //rectangular prism
                if(shape.equals("RP")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    texture = sc.next();
                    shapes.add(new RectangularPrism(p, c, x, y, z ,xWidth, yHeight, zDepth, ImageLoader.loadImage(texture)));
                }
                //plane
                else if(shape.equals("P")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    texture = sc.next();
                    viewSide = sc.next();
                    shapes.add(new Plane(p, c, x, y, z ,xWidth, yHeight, zDepth, ImageLoader.loadImage(texture), viewSide));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shapes;
    }
}
