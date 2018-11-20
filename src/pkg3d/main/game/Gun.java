package pkg3d.main.game;

/**
 *
 * @author asile
 */
public class Gun {
    
    private int bullets;
    private int maxBullets;
    private int damage;
    private long shotCooldown;
    private long recoil;
    private double zoom;
    private boolean scope;
    
    public Gun(int maxBullets, int damage, long shotCooldown, long recoil, double zoom, boolean scope){
        this.maxBullets = maxBullets;
        this.damage = damage;
        this.shotCooldown = shotCooldown;
        this.recoil = recoil;
        this.zoom = zoom;
        this.scope = scope;
        bullets = maxBullets;
    }
    
    public int getBullets(){
        return bullets;
    }
    public void setBullets(int bullets){
        this.bullets = bullets;
    }
    public int getMaxBullets(){
        return maxBullets;
    }
    public int getDamage(){
        return damage;
    }
    public long getShotCooldown(){
        return shotCooldown;
    }
    public long getRecoil(){
        return recoil;
    }
    public double getZoom(){
        return zoom;
    }
    public boolean getScope(){
        return scope;
    }
}
