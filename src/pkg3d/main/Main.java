package pkg3d.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.Display;
import pkg3d.main.gfx.objects.PolygonManager;
import pkg3d.main.input.KeyManager;
import pkg3d.main.input.MouseManager;

/**
 *
 * @author asile
 */
public class Main extends Loop{
    
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private int width, height;
    
    private KeyManager keyManager;
    private MouseManager mouseManager;
    private PolygonManager polygonManager;
    
    private Camera camera;
    
    @Override
    public void startup() {
        width = 1080;
        height = 720;
        display = new Display("3d", width, height);
        
        //set up closing operation
        display.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stop();
            }
        });
        
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        
        mouseManager = new MouseManager(width, height);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        camera = new Camera(new double[]{-5, 0, 10}, new double[]{-5, -3, 0}, width, height, mouseManager);
        
        polygonManager = new PolygonManager(camera);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2},  new double[]{0, 0, 0, 0}, Color.red);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 2, 2},  new double[]{3, 3, 3, 3}, Color.red);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{0, 0, 0, 0},  new double[]{0, 0, 3, 3}, Color.green);
        polygonManager.addPolygon(new double[]{0, 2, 2, 0}, new double[]{2, 2, 2, 2},  new double[]{0, 0, 3, 3}, Color.orange);
        polygonManager.addPolygon(new double[]{0, 0, 0, 0}, new double[]{0, 2, 2, 0},  new double[]{0, 0, 3, 3}, Color.blue);
        polygonManager.addPolygon(new double[]{2, 2, 2, 2}, new double[]{0, 2, 2, 0},  new double[]{0, 0, 3, 3}, Color.cyan);
        polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{0, 4, 4, 0},  new double[]{0, 0, 6, 6}, Color.DARK_GRAY);
        
        //polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{1, 500, 500, 1},  new double[]{0, 0, .5, .5}, Color.black);
        //polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{-100, 1, 1, -100},  new double[]{0, 0, .5, .5}, Color.black);
        //polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{1, 500, 500, 1},  new double[]{0, 110, 110, 0}, Color.black);
        //polygonManager.addPolygon(new double[]{-2, -2, -2, -2}, new double[]{-100, 1, 1, -100},  new double[]{110, 0, 0, 110}, Color.black);
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    @Override
    public void update() {
        camera.update();
        polygonManager.update(width, height);
        movement();
    }
    
    public void movement(){
        boolean[] keys = new boolean[4];
        if(keyManager.getWPressed()){
            keys[0] = true;
        }
        if(keyManager.getAPressed()){
            keys[1] = true;
        }
        if(keyManager.getSPressed()){
            keys[2] = true;
        }
        if(keyManager.getDPressed()){
            keys[3] = true;
        }
        
        if(keyManager.getWPressed() == false){
            keys[0] = false;
        }
        if(keyManager.getAPressed() == false){
            keys[1] = false;
        }
        if(keyManager.getSPressed() == false){
            keys[2] = false;
        }
        if(keyManager.getDPressed() == false){
            keys[3] = false;
        }
        
        camera.move(keys, .1);
    }
    
    @Override
    public void render() {
        bs = display.getCanvas().getBufferStrategy();
	if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
	}
	g = bs.getDrawGraphics();
	//Clear Screen
	g.clearRect(0, 0, width, height);
        
        g.clipRect(0, 0, width, height);
        g.setColor(new Color(140, 180, 180));
        g.fillRect(0, 0, width, height);
        
        polygonManager.render(g);
        
	bs.show();
	g.dispose();
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
