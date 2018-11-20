package pkg3d.main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author asile
 * handles basic key input
 */
public class KeyManager implements KeyListener{
    
    private boolean[] oldKeys, curKeys;
    
    private boolean wPressed, aPressed, sPressed, dPressed;
    private boolean oPressed;
    private boolean rPressed;
    
    public KeyManager(){
        oldKeys = new boolean[256];
        curKeys = new boolean[256];
    }
    
    public void update(){
        oldKeys = curKeys.clone();
    }
    
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
        
        //temp to get out of enviornment
        if(ke.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        curKeys[ke.getKeyCode()] = true;
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
        curKeys[ke.getKeyCode()] = false;
    }
    
    
    //getters
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
    
    public boolean getSpacePressed(){return curKeys[KeyEvent.VK_SPACE];}
    public boolean getSpaceTapped() {
        return curKeys[KeyEvent.VK_SPACE] && curKeys[KeyEvent.VK_SPACE] != oldKeys[KeyEvent.VK_SPACE];
    }

    public boolean getRTapped() {
        return curKeys[KeyEvent.VK_R] && curKeys[KeyEvent.VK_R] != oldKeys[KeyEvent.VK_R];
    }
}
