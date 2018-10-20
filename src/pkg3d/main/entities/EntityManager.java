package pkg3d.main.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import pkg3d.main.gfx.object.PolygonManager;

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
    
    public void removeEntity(Entity e, PolygonManager p){
        //p.removeShape(e.getShape());
        //entities.remove(e);
        Iterator<Entity> ei = entities.iterator();
        while (ei.hasNext()) {
            Entity e2 = ei.next();
            if(e2 == e){
                p.removeShape(e2.getShape());
                ei.remove();
            }
        }
    }
    
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
    
    public void render(Graphics g){
        for(Entity e: entities){
            e.render(g);
        }
    }
    
    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
