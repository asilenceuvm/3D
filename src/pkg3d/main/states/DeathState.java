package pkg3d.main.states;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author asile
 */
public class DeathState extends State{
    
    
    
    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 10, 10);
    }

    @Override
    public void reloadState() {
    }
    
    

}
