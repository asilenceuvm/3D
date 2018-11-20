package pkg3d.main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import pkg3d.main.gfx.Display;
import pkg3d.main.gfx.images.ImageManager;
import pkg3d.main.input.KeyManager;
import pkg3d.main.input.MouseManager;
import pkg3d.main.states.MainState;
import pkg3d.main.states.State;

/**
 * @author asile
 * Main class, handles raw input
 */
public class Main extends Loop{
    
    private Display display;
    private BufferStrategy bs;
    private Graphics g;
    private int width, height;
    
    //player input
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private State mainState;
    
    private ImageManager imageManager;
    
    @Override
    public void startup() {
        //create window
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
        
        //load images
        imageManager = new ImageManager();
        
        //input adding
        keyManager = new KeyManager();
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addKeyListener(keyManager);
        
        mouseManager = new MouseManager(width, height);
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        //set up state system
        mainState = new MainState(this);
        State.setCurState(mainState);
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    @Override
    public void update() {
        //calls the current state to update
        State.getCurState().update();
        keyManager.update();
    }
    
    @Override
    public void render() {
        //set up graphics
        bs = display.getCanvas().getBufferStrategy();
	if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
	}
	g = bs.getDrawGraphics();
	//Clear Screen
	g.clearRect(0, 0, width, height);
        
        //draw current state
        State.getCurState().render(g);
        
	bs.show();
	g.dispose();
    }
    
    //getters 
    public State getState(){
        return State.getCurState();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
    }
    
    public MouseManager getMouseManager(){
        return mouseManager;
    }
    
    public ImageManager getImageManager(){
        return imageManager;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public JFrame getFrame(){
        return display.getFrame();
    }
}
