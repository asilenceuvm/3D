package pkg3d.main.states;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.gfx.ui.Button;

/**
 *
 * @author asile
 */
public class DeathState extends State{
    
    private Main main;
    
    private Button replayButton;
    private Button menuButton;
    private int buttonWidth = 300, buttonHeight = 100;
    
    private ArrayList<Button> buttons = new ArrayList<>();
    
    public DeathState(Main main){
        this.main = main;
        
        replayButton = new Button(main, main.getImageManager().getImage("playAgainButton"), main.getImageManager().getImage("playAgainButtonActive"),
                main.getWidth()-400, (buttonHeight / 2), buttonWidth, buttonHeight);
        menuButton = new Button(main, main.getImageManager().getImage("quitToMainButton"), main.getImageManager().getImage("quitToMainButtonActive"),
                main.getWidth()-300, (int)(main.getHeight() / 2.3) - (buttonHeight / 2), buttonWidth, buttonHeight);
        
        buttons.add(replayButton);
        buttons.add(menuButton);
    }
    
    @Override
    public void update() {
        buttons.forEach((b) -> {
            b.update();
        });
        checkButtons();
    }
    
    private void checkButtons() {
        if (replayButton.click()) {
            State.setCurState(main.getMainState());
        }
        if (menuButton.click()) {
            State.setCurState(main.getMenuState());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        
        buttons.forEach((b) -> {
            b.render(g);
        });
    }

    @Override
    public void reloadState() {
        main.getFrame().setCursor(Cursor.DEFAULT_CURSOR);
    }
    
    

}
