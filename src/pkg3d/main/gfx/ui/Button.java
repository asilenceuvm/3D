package pkg3d.main.gfx.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pkg3d.main.Main;


public class Button {
    
    private Main main;
    private BufferedImage texture;
    private BufferedImage highlightTexture;
    private int x,y;
    private int width, height;
    
    private boolean highlighted=false;
    
    public Button(Main main, BufferedImage texture, BufferedImage highlightTexture, int x, int y, int width, int height){
        this.main = main;
        this.texture = texture;
        this.highlightTexture = highlightTexture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void update(){
        if(main.getMouseManager().getMouseX() > x && main.getMouseManager().getMouseX() < x + width){
            if(main.getMouseManager().getMouseY() > y && main.getMouseManager().getMouseY() < y + height){
                highlighted = true;
            } else {
                highlighted = false;
            }
        } else {
            highlighted = false;
        }
        
    }
    
    public void render(Graphics g){
        if (!highlighted) {
            g.drawImage(texture, (int) x, (int) y, width, height, null);
        } else {
            g.drawImage(highlightTexture, (int) x, (int) y, width, height, null);
        }
    }
    
    public boolean click(){
        if(highlighted){
            if(main.getMouseManager().getLeftPressed()){
                return true;
            }
        }
        return false;
    }
    
    //getters & setters
    public int getY(){return y;}
}
