package pkg3d.main.states;

import java.awt.Graphics;

/**
 * @author asile
 * state system for the game
 * eg. main state, main menu state , etc.
 */
public abstract class State {
    
    private static State curState;
    
    public static void setCurState(State state){
        curState = state;
    }
    
    public static State getCurState(){
        return curState;
    }
    
    public abstract void update();
    public abstract void render(Graphics g);
}
