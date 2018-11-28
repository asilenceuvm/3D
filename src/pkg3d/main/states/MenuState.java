package pkg3d.main.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import pkg3d.main.Main;
import pkg3d.main.gfx.Camera;
import pkg3d.main.gfx.object.PolygonManager;
import pkg3d.main.gfx.object.shapes.Shape;
import pkg3d.main.gfx.ui.Button;
import pkg3d.main.scenes.SceneLoader;

/**
 *
 * @author asile
 */
public class MenuState extends State {
    private Main main;
    private PolygonManager polygonManager;
    private Camera camera;
    
    private Button startButton;
    private Button optionButton;
    private Button closeButton;
    
    private int buttonWidth = 300, buttonHeight = 100;

    private ArrayList<Button> buttons = new ArrayList<>();

    public MenuState(Main main) {
        this.main = main;

        startButton = new Button(main, main.getImageManager().getImage("startButton"), main.getImageManager().getImage("startButtonActive"),
                main.getWidth()-300, (main.getHeight() / 2) - (buttonHeight / 2), buttonWidth, buttonHeight);
        optionButton = new Button(main, main.getImageManager().getImage("optionButton"), main.getImageManager().getImage("optionButtonActive"),
                main.getWidth()-370, (int)(main.getHeight() / 1.5) - (buttonHeight / 2), buttonWidth, buttonHeight);
        closeButton = new Button(main, main.getImageManager().getImage("quitButton"), main.getImageManager().getImage("quitButtonActive"),
                main.getWidth()-400, (int)(main.getHeight() / 1.2) - (buttonHeight / 2), buttonWidth, buttonHeight);

        buttons.add(startButton);
        buttons.add(optionButton);
        buttons.add(closeButton);
        
        camera = new Camera(main, new double[]{51, 10, 14}, new double[]{51, 30, 50}, main.getMouseManager());
        
        camera.update();
        camera.rotateFree(30, -3);
        polygonManager = new PolygonManager(main, camera);
        
        SceneLoader sceneLoader = new SceneLoader(main, polygonManager, camera, "scene4");
        //sceneLoader.addGround(main, polygonManager, camera, 8, 8);
        ArrayList<Shape> shapes = sceneLoader.getShapes();
        for(Shape s: shapes){
            polygonManager.addShape(s);
        }
        camera.setViewPosition(new double[]{15,15,15});
    }

    @Override
    public void update() {
        camera.setViewPosition(new double[]{15,15,15});
        buttons.forEach((b) -> {
            b.update();
        });
        checkButtons();
        polygonManager.update(main.getWidth(), main.getHeight());
        camera.update(.1,-.1);
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
        polygonManager.render(g, main.getWidth(), main.getHeight());
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
