/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

//import EpicAdventures.Elements.AbstractGameObject;
import EpicAdventures.Elements.*;
import MVCFramework.Vector;

/**
 *
 * @author Ethan
 */
public class GameObjectsFactory {
    
    
    
    public GameObjectsFactory(){
        
    }
    
    
    public static AbstractGameObject createGameObject(String id,Vector position){
        AbstractGameObject o = null;
        if(id.equals("enemy")){
            o = new Enemy();            
            o.setHealth(100);
        }
        
        else if(id.equals("friendly")){
            o = new Friendly();            
            o.setHealth(100);
        }
        
        else if(id.equals("bullet")){
            o = new Bullet();
        }
        
        else if(id.equals("enemyBomb")){
            o = new EnemyBomb();           
        }
        
        else{
            System.out.println("No Object specified");
        }
        
        o.setPosition(position);
        
        return o;
        
    }
    
}
