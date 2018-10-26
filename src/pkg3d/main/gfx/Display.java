package pkg3d.main.gfx;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @author asile
 * creates the window to display on
 */
public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    
    public Display(String title, int width, int height){
        frame = new JFrame(title);
        
        frame.setSize(width, height);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);
                
        frame.add(canvas);
        frame.pack();
    }
    
    //getters
    public JFrame getFrame(){
        return frame;
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
}
