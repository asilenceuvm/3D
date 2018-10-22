package pkg3d.main.states;

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
import pkg3d.main.scenes.Scene;
import pkg3d.main.scenes.SceneLoader;

/**
 *
 * @author asile
 */
public class MainState extends State{
    
    private Scene scene;
    
    public MainState(Main main){
        scene = new Scene(main);
    }
    
    @Override
    public void update() {
        scene.update();
    }

    @Override
    public void render(Graphics g) {
        scene.render(g);
    }
    
}
