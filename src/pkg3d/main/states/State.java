package pkg3d.main.states;

import java.awt.Graphics;
import pkg3d.main.scenes.Scene;

/**
 * @author asile
 * state system for the game
 * eg. main state, main menu state , etc.
 */
public abstract class State {
    
    private static State curState;
    protected Scene curScene;
    
    public static void setCurState(State state){
        curState = state;
    }
    
    public static State getCurState(){
        return curState;
    }
    
    public Scene getCurScene(){
        return curScene;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
}
