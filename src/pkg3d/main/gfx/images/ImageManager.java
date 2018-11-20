package pkg3d.main.gfx.images;

import java.awt.image.BufferedImage;

/**
 *
 * @author asile
 */
public class ImageManager {
    
    private BufferedImage error;
    private BufferedImage grass;
    private BufferedImage stone;
    private BufferedImage dirt;
    private BufferedImage water;
    private BufferedImage heart;
    private BufferedImage bullet;
    private BufferedImage deagle;
    private BufferedImage ak;
    private BufferedImage awp;
    private BufferedImage scope;
    
    public ImageManager(){
        error = ImageLoader.loadImage("error.png");
        grass = ImageLoader.loadImage("grass.png");
        stone = ImageLoader.loadImage("stone.png");
        dirt = ImageLoader.loadImage("dirt.png");
        water = ImageLoader.loadImage("water.png");
        heart = ImageLoader.loadImage("heart.png");
        bullet = ImageLoader.loadImage("bullet.png");
        deagle = ImageLoader.loadImage("deagle.png");
        ak = ImageLoader.loadImage("ak.png");
        awp = ImageLoader.loadImage("awp.png");
        scope = ImageLoader.loadImage("scope.png");
    }
    
    public BufferedImage getImage(String image){
        BufferedImage returnImage = error;
        if(image.equals("grass")){
            returnImage = grass;
        }
        else if(image.equals("stone")){
            returnImage = stone;
        }
        else if(image.equals("dirt")){
            returnImage = dirt;
        }
        else if(image.equals("water")){
            returnImage = water;
        } 
        else if(image.equals("heart")){
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
        return returnImage;
    }

}
