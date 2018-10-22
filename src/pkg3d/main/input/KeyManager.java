package pkg3d.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author asile
 */
public class KeyManager implements KeyListener{
    
    private boolean wPressed, aPressed, sPressed, dPressed;
    private boolean oPressed;
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_W){
            wPressed = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_A){
            aPressed = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_S){
            sPressed = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_D){
            dPressed = true;
        }
        if(ke.getKeyCode() == KeyEvent.VK_O){
            oPressed = !oPressed;
        }
        
        //temp
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_W){
            wPressed = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_A){
            aPressed = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_S){
            sPressed = false;
        }
        if(ke.getKeyCode() == KeyEvent.VK_D){
            dPressed = false;
        }
    }
    
    public boolean getWPressed(){
        return wPressed;
    }
    public boolean getAPressed(){
        return aPressed;
    }
    public boolean getSPressed(){
        return sPressed;
    }
    public boolean getDPressed(){
        return dPressed;
    }
    public boolean getOPressed(){
        return oPressed;
    }
}
