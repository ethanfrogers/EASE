/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

/**
 *
 * @author efrogers_it
 */
public class HealthPacket extends Object {
    
    int health = 0;
    String id = "";

    public HealthPacket() {
    }
    
    public HealthPacket(int h, String id){
        health = h;
        this.id = id;
    }
    

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
    
    
}
