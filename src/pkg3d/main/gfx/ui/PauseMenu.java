package pkg3d.main.gfx.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.states.State;

/**
 *
 * @author asile
 */
public class PauseMenu {
    private Main main;
    private Color background = new Color(0,0,0,50);
    
    private ArrayList<Button> buttons = new ArrayList<>();
    private Button mainMenu;
    private Button unPause;
    
    private int buttonWidth=300,buttonHeight=100;
    
    private boolean override=true;
    
    public PauseMenu(Main main){
        this.main = main;
        
        int middle = (main.getWidth()/2) - (buttonWidth/2);
        
        mainMenu = new Button(main, main.getImageManager().getImage("returnButtonActive"), main.getImageManager().getImage("returnButton"), 
                middle, (main.getHeight()/2) - (buttonHeight/2),buttonWidth, buttonHeight);
        unPause = new Button(main, main.getImageManager().getImage("resumeButtonActive"), main.getImageManager().getImage("resumeButton"), 
                middle, (main.getHeight()/4) - (buttonHeight/2),buttonWidth, buttonHeight);
        
        buttons.add(mainMenu);
        buttons.add(unPause);
    }
    
    public void update(){
        buttons.forEach((b) -> {
            b.update();
        });
        
        checkClicks();
    }
    
    private void checkClicks(){
        if(mainMenu.click()){
            State.setCurState(main.getMenuState());
        }
        if(unPause.click()){
            override=true;
        }
    }
    
    public void render(Graphics g){
        g.setColor(background);
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        
        buttons.forEach((b) -> {
            b.render(g);
        });
    }
    
    public boolean checkPaused(){
        if(override){
            override=false;
            main.getKeyManager().escapePressed=false;
            return false;
        }
        if(main.getKeyManager().escapePressed){
            return true;
        }
        return false;
    }
}
