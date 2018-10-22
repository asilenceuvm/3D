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
import pkg3d.main.gfx.Display;
import pkg3d.main.input.KeyManager;
import pkg3d.main.input.MouseManager;
import pkg3d.main.states.MainState;
import pkg3d.main.states.State;

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
    
    private State mainState;
    
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
        display.getFrame().addMouseListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
        mainState = new MainState(this);
        State.setCurState(mainState);
        
        invisibleMouse();
    }

    @Override
    public void shutdown() {
        System.exit(0);
    }

    @Override
    public void update() {
        State.getCurState().update();
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
        
        State.getCurState().render(g);
        
	bs.show();
	g.dispose();
    }
    private void invisibleMouse() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
        Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, new Point(0, 0), "InvisibleCursor");
        display.getFrame().setCursor(invisibleCursor);
    }
    
    public State getState(){
        return State.getCurState();
    }
    
    public KeyManager getKeyManager(){
        return keyManager;
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
