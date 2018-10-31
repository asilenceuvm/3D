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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Display display = (Display) o;

        if (frame != null ? !frame.getSize().equals(display.frame.getSize()) : display.frame != null) return false;
        return canvas != null ? canvas.getSize().equals(display.canvas.getSize()) : display.canvas == null;
    }

    @Override
    public int hashCode() {
        int result = frame != null ? frame.hashCode() : 0;
        result = 31 * result + (canvas != null ? canvas.hashCode() : 0);
        return result;
    }
}
