package pkg3d.main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.gfx.ui.Button;

/**
 *
 * @author asile
 */
public class MenuState extends State {
    private Main main;
    
    private Button startButton;
    private Button optionButton;
    private Button closeButton;
    
    private int buttonWidth = 300, buttonHeight = 100;

    private ArrayList<Button> buttons = new ArrayList<>();

    public MenuState(Main main) {
        this.main = main;

        startButton = new Button(main, main.getImageManager().getImage("startButton"), main.getImageManager().getImage("startButtonActive"),
                main.getWidth()-400, (buttonHeight / 2), buttonWidth, buttonHeight);
        optionButton = new Button(main, main.getImageManager().getImage("optionButton"), main.getImageManager().getImage("optionButtonActive"),
                main.getWidth()-370, (int)(main.getHeight() / 3.5) - (buttonHeight / 2), buttonWidth, buttonHeight);
        closeButton = new Button(main, main.getImageManager().getImage("quitButton"), main.getImageManager().getImage("quitButtonActive"),
                main.getWidth()-300, (int)(main.getHeight() / 2.3) - (buttonHeight / 2), buttonWidth, buttonHeight);

        buttons.add(startButton);
        buttons.add(optionButton);
        buttons.add(closeButton);
        
    }

    @Override
    public void update() {
        buttons.forEach((b) -> {
            b.update();
        });
        checkButtons();
    }

    /**
     * update button clicking
     */
    private void checkButtons() {
        if (startButton.click()) {
            State.setCurState(main.getMainState());
        }
        if(optionButton.click()){
            
        }
        if (closeButton.click()) {
            main.shutdown();
        }
    }

    @Override
    public void render(Graphics g) {
        g.clipRect(0, 0, main.getWidth(), main.getHeight());
        g.setColor(new Color(20, 20, 60));
        g.fillRect(0, 0, main.getWidth(), main.getHeight());
        g.drawImage(main.getImageManager().getImage("background"), 0, 0, main.getWidth(), main.getHeight(), null);
        buttons.forEach((b) -> {
            b.render(g);
        });
    }

    @Override
    public void reloadState() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            System.out.println("lol thats not good");
        }
    }

}
