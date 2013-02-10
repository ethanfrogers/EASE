/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;
import EpicAdventures.Elements.AbstractGameObject;
import EpicAdventures.Elements.Bullet;
import EpicAdventures.Elements.Enemy;
import EpicAdventures.Elements.EnemyBomb;
import EpicAdventures.Elements.Friendly;
import EpicAdventures.Elements.HealthPacket;
import EpicAdventures.Elements.StrikeObject;
import MVCFramework.AbstractEnsemble;
import MVCFramework.Vector;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import Factories.GameObjectsFactory;
/**
 *
 * @author efrogers_it
 */
public class GameEnsemble extends AbstractEnsemble implements ActionListener {

    private int iterations = 0;
    private int objectLimit = 2;
    private int objectsOnModel = 0;
    //int healthChange = 0;
    private Vector enemyPosition = new Vector(top, left);
    private Vector friendlyPosition = new Vector(bottom+35,right+35);
    private Rectangle2D enemyShape = new Rectangle2D.Double();
    private Rectangle2D friendlyShape = new Rectangle2D.Double();
    Timer timer = new Timer(1000/50,this);
    String state = "changed";
    Boolean reverseEnemy = true;
    Boolean enemyPresent = true;
    Random rand = new Random();
    int bombDrop = 0;
    
    
    /**
     * Constuctor for the GameEnsemble. Uses the super implementation of the AbstractEnesmble class
     */
    public GameEnsemble(){
        super();
        timer.start();
       
    }
    
    /*
     * Method to generate random numbers for enemy bombs
     */
    private int getRandom(){
       return 10 + (int)(Math.random() * ((50 - 10) + 1));
    }
    
    /*
     * overridden method of the AbstractEnsemble's iterate method. Used to iterate the ensemble
     */
    @Override
    public void iterate() {
        ActionEvent e = new ActionEvent(timer, 1, "balls");
        actionPerformed(e);
    }
    
    /*
     * actionPerformed method to iterate the ensemble based on the timer firing. Used to move Strike
     * objects up/down the screen, and enemy movement
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source == timer){
            bombTime();
            for(Object o: this.model){
                if(o instanceof StrikeObject){
                    StrikeObject b = (StrikeObject)o;
                    if((b.getY() <= 0 && b instanceof Bullet) || (b.getY() >= 600 && b instanceof EnemyBomb)){
                        model.remove(o);
                        break;
                    }
                    else
                        strikeCheck((StrikeObject)o);
                }
                else if(o instanceof Enemy){
                    Enemy e = (Enemy)o;
                    if(e.getHealth() < 0){
                        model.remove(e);
                        enemyPresent = false;
                        break;
                    }
                    enemyPosition = e.getPosition();
                    moveObject(null,"enemy");
                    e.setPosition(enemyPosition);
                }
                
            }
        }
        
        setChanged();
        notifyObservers(state);
        
    }
    
    /*
     * method to time release of bombs from enemy
     */
    private void bombTime(){
        if(bombDrop <= 500){
                bombDrop += getRandom();
            }
            else{
                if(enemyPresent){
                    bulletFired("enemy");
                    bombDrop = 0;
                }
                
            }
    }
    
    /**
     * Method to check bullet Strike Object collision on enemy or friendly
     * @param b game object to check collision against
     * @param e position of game object to check
     * @return returns true if strike occurrs, false otherwise;
     */
    private Boolean checkBulletCollision(AbstractGameObject b, Vector e){
        Boolean strike;
        if(((b.getX() + 100) > e.x && (b.getX() - 100) < e.x) && ((b.getY() + 20) > e.y && (b.getY() - 20) < e.y)){
            return strike = true;
        }
        else{
            return strike = false;
        }
        
       
        
    }
    
    /**
     * Method to move objects across the sceen
     * @param o1 movement commands
     * @param id id for object type
     */
    public void moveObject(Object o1, String id){
        for(Object o : this.model){
            Friendly f;
            if (o instanceof Friendly && "friendly".equals(id)){
                f = (Friendly) o;
                if("left".equals(o1) && friendlyPosition.x > 0){
                    friendlyPosition.set(friendlyPosition.x - 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
                else if("right".equals(o1) && friendlyPosition.x < 600){
                    friendlyPosition.set(friendlyPosition.x + 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
                setChanged();
                notifyObservers(state);
            }
            else if(o instanceof Enemy && "enemy".equals(id)){
                if(reverseEnemy == true){
                        enemyPosition.set(enemyPosition.x+5, enemyPosition.y);
                        if(enemyPosition.x >=700) reverseEnemy = false;
                        
                    }
                    else if(reverseEnemy == false){
                        enemyPosition.set(enemyPosition.x-5, enemyPosition.y);
                        if(enemyPosition.x <=0) reverseEnemy = true;
                    }
            }
        }
        
    }
    
    /**
     * Method for updating health is strike occurrs
     * @param e striking object. used to locate each object
     */
    public void strikeCheck(StrikeObject e){
       if(e instanceof Bullet){
            Bullet b = (Bullet)e;
            
            if(checkBulletCollision(b,enemyPosition)){
                System.out.println("Enemy Hit");                  
                int num = updateHealth(5,new Enemy());
                setChanged();
                notifyObservers(new HealthPacket(num, "enemy"));
            }

            b.setPosition(b.getX(),b.getY()-20);
       }
       else if(e instanceof EnemyBomb){
            EnemyBomb b = (EnemyBomb)e;
   
            if(checkBulletCollision(b,friendlyPosition)){
                System.out.println("Friend Hit");
                int num = updateHealth(5,new Friendly());
                setChanged();
                notifyObservers(new HealthPacket(num, "friendly"));
            }

            b.setPosition(b.getX(),b.getY()+20);

       }
    
    }
    
    /**
     * Method for firing bullets. 
     * @param ID object type
     */
    public void bulletFired(String ID){
        String type =""; AbstractGameObject e = null; Vector pos = null;
        if("friendly".equals(ID)){
            type = "bullet";
            pos = friendlyPosition;
        }
        else if("enemy".equals(ID)){
            type = "enemyBomb";
            pos = enemyPosition;
        }
        e = GameObjectsFactory.createGameObject(type, pos);
        model.add(e);
        setChanged();
        notifyObservers(state);
        
    }
    
    /**
     * method for getting shape of an object. not called in this implementation
     * @param o1 requested object
     * @return 
     */
    @Override
    public Shape getShape(Object o1) {
        return null;
    }
    
    /**
     * init the model. 
     */
    public void init(){
        this.model.clear();
        addElements();
    }

    /**
     * called by init() adds elements to the model upon startup
     */
    public void addElements() {
        if(model.size() == objectLimit) return;
        for(int i = 0; i < 2 ; i++){
            AbstractGameObject e = null; String id = ""; Vector pos = null;
            if(i == 0){
                id = "enemy";
                pos = enemyPosition;
            }
            else if(i == 1){
                id = "friendly";
                pos = friendlyPosition;
            }
            e = GameObjectsFactory.createGameObject(id, pos);
            setChanged();
            notifyObservers(new HealthPacket(e.getHealth(),id));
            model.add(e);
            
        }
    }

    /**
     * accessor method for the model
     * @return model
     */
    public ArrayList<Object> getModel() {
        return model;
    }
    
    /**
     * check enemy state
     * @return true if enemy is present on model. false otherwise
     */
    public Boolean getEnemyState(){
        return enemyPresent;
    }

    /**
     * method for updating health of an object
     * @param  amount of health to deduct
     * @param o object to deduct
     * @return 
     */
    private int updateHealth(int amount,AbstractGameObject o){
        int curHealth = 0;
        for(Object o1 : this.model){
            if(o1.getClass() == o.getClass()){
                AbstractGameObject test = (AbstractGameObject) o1;
                test.setHealth(test.getHealth() - amount);
                curHealth = test.getHealth();
            }
        }
        return curHealth;
    }

    
    
    
    
    
    
    
}
