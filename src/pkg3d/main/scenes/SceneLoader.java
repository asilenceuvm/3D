package pkg3d.main.scenes;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.RectangularPrism;
import pkg3d.main.gfx.object.shapes.Shape;

/**
 *
 * @author asile
 */
public class SceneLoader {
    
    
    public ArrayList<Shape> loadFile(PolygonManager p, Camera c){
        double x;
        double y;
        double z;
        double xWidth;
        double yHeight;
        double zDepth;
        ArrayList<Shape> shapes = new ArrayList<>();
        try {
            File file = new File("res\\scenes\\scene1.txt");
            Scanner sc = new Scanner(file);
            
            while (sc.hasNext()){ 
                if(sc.next().equals("RP")){
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                    z = sc.nextDouble();
                    xWidth = sc.nextDouble();
                    yHeight = sc.nextDouble();
                    zDepth = sc.nextDouble();
                    sc.next();
                    shapes.add(new RectangularPrism(p, c, x, y, z ,xWidth, yHeight, zDepth, Color.cyan));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SceneLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return shapes;
    }
}
