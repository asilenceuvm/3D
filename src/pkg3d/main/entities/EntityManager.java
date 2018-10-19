package pkg3d.main.entities;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author asile
 */
public class EntityManager {
    
    private ArrayList<Entity> entities;
    
    public EntityManager(){
        entities = new ArrayList<>();
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    public void update(){
        for(Entity e: entities){
            e.update();
        }
    }
    
    public void render(Graphics g){
        for(Entity e: entities){
            e.render(g);
        }
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
