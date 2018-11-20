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
    
    public ImageManager(){
        error = ImageLoader.loadImage("error.png");
        grass = ImageLoader.loadImage("grass.png");
        stone = ImageLoader.loadImage("stone.png");
        dirt = ImageLoader.loadImage("dirt.png");
        water = ImageLoader.loadImage("water.png");
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
        return returnImage;
    }

}
