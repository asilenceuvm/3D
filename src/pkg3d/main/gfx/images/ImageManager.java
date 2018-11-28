package pkg3d.main.gfx.images;

import java.awt.image.BufferedImage;

/**
 *
 * @author asile
 */
public class ImageManager {
    
    private BufferedImage error;
    private BufferedImage heart;
    private BufferedImage bullet;
    private BufferedImage deagle;
    private BufferedImage ak;
    private BufferedImage awp;
    private BufferedImage scope;
    private BufferedImage sun;
    private BufferedImage tile1, tile2, tile3, tile4;
    private BufferedImage highlight;
    private BufferedImage startButton, startButtonActive;
    private BufferedImage quitButton, quitButtonActive;
    private BufferedImage optionButton, optionButtonActive;
    private BufferedImage resumeButton, resumeButtonActive;
    private BufferedImage returnButton, returnButtonActive;
    private BufferedImage background;
    
    public ImageManager(){
        error = ImageLoader.loadImage("error.png");
        heart = ImageLoader.loadImage("heart.png");
        bullet = ImageLoader.loadImage("bullet.png");
        deagle = ImageLoader.loadImage("deagle.png");
        ak = ImageLoader.loadImage("ak.png");
        awp = ImageLoader.loadImage("awp.png");
        scope = ImageLoader.loadImage("scope.png");
        sun = ImageLoader.loadImage("sun.png");
        tile1 = ImageLoader.loadImage("tile1.png");
        tile2 = ImageLoader.loadImage("tile2.png");
        tile3 = ImageLoader.loadImage("tile3.png");
        tile4 = ImageLoader.loadImage("tile4.png");
        highlight = ImageLoader.loadImage("highlight.png");
        startButton = ImageLoader.loadImage("playButton.png");
        startButtonActive = ImageLoader.loadImage("playButtonActive.png");
        quitButton = ImageLoader.loadImage("quitButton.png");
        quitButtonActive = ImageLoader.loadImage("quitButtonActive.png");
        optionButton = ImageLoader.loadImage("optionButton.png");
        optionButtonActive = ImageLoader.loadImage("optionButtonActive.png");
        returnButton = ImageLoader.loadImage("returnButton.png");
        returnButtonActive = ImageLoader.loadImage("returnButtonActive.png");
        resumeButton = ImageLoader.loadImage("resumeButton.png");
        resumeButtonActive = ImageLoader.loadImage("resumeButtonActive.png");
        background = ImageLoader.loadImage("background.png");
    }
    
    public BufferedImage getImage(String image){
        BufferedImage returnImage = error;
        if(image.equals("heart")){
            returnImage = heart;
        }
        else if(image.equals("bullet")){
            returnImage = bullet;
        } 
        else if(image.equals("deagle")){
            returnImage = deagle;
        }
        else if(image.equals("ak")){
            returnImage = ak;
        }
        else if(image.equals("awp")){
            returnImage = awp;
        }
        else if(image.equals("scope")){
            returnImage = scope;
        }
        else if(image.equals("sun")){
            returnImage = sun;
        }
        else if(image.equals("tile1")){
            returnImage = tile1;
        }
        else if(image.equals("tile2")){
            returnImage = tile2;
        }
        else if(image.equals("tile3")){
            returnImage = tile3;
        }
        else if(image.equals("tile4")){
            returnImage = tile4;
        }
        else if(image.equals("highlight")){
            returnImage = highlight;
        }
        else if(image.equals("startButton")){
            returnImage = startButton;
        }
        else if(image.equals("startButtonActive")){
            returnImage = startButtonActive;
        }
        else if(image.equals("quitButton")){
            returnImage = quitButton;
        }
        else if(image.equals("quitButtonActive")){
            returnImage = quitButtonActive;
        }
        else if(image.equals("optionButton")){
            returnImage = optionButton;
        }
        else if(image.equals("optionButtonActive")){
            returnImage = optionButtonActive;
        }
        else if(image.equals("background")){
            returnImage = background;
        }
        else if(image.equals("resumeButton")){
            returnImage = resumeButton;
        }
        else if(image.equals("resumeButtonActive")){
            returnImage = resumeButtonActive;
        }
        else if(image.equals("returnButton")){
            returnImage = returnButton;
        }
        else if(image.equals("returnButtonActive")){
            returnImage = returnButtonActive;
        }
        return returnImage;
    }

}
