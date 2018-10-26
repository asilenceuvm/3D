package pkg3d.main.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import pkg3d.main.gfx.object.PolygonManager;

/**
 * @author asile
 * handles all entities
 */
public class EntityManager {
    
    //holds all entities in scene
    private ArrayList<Entity> entities;
    
    public EntityManager(){
        entities = new ArrayList<>();
    }
    
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    //creates an iterator to safely remove entities
    public void removeEntity(Entity e, PolygonManager p){
        Iterator<Entity> ei = entities.iterator();
        while (ei.hasNext()) {
            Entity e2 = ei.next();
            if(e2 == e){
                p.removeShape(e2.getShape());
                ei.remove();
            }
        }
    }
    
    //updates entities and checks for removal
    public void update(PolygonManager p){
        for(Entity e: entities){
            e.update();
        }
        Iterator<Entity> ei = entities.iterator();
        while (ei.hasNext()) {
            Entity e2 = ei.next();
            if(e2.getRemove()){
                p.removeShape(e2.getShape());
                ei.remove();
            }
        }
    }
    
    //draws entities
    public void render(Graphics g){
        for(Entity e: entities){
            e.render(g);
        }
    }
    
    //getters
    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
