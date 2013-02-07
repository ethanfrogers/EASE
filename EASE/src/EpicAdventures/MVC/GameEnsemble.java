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
/**
 *
 * @author efrogers_it
 */
public class GameEnsemble extends AbstractEnsemble implements ActionListener {

    private int iterations = 0;
    private int objectLimit = 2;
    private int objectsOnModel = 0;
    int healthChange = 0;
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
    
    
    
    public GameEnsemble(){
        super();
        timer.start();
       
    }
    
    private int getRandom(){
       return 10 + (int)(Math.random() * ((50 - 10) + 1));
    }
    
    @Override
    public void iterate() {
        System.out.println("Model iterate called.");
        for(Object o1 : this.model){
            Enemy e;
            Friendly f;
            Bullet b;
            if(o1 instanceof Enemy){
                e = (Enemy)o1;
                enemyPosition = e.getPosition();
                enemyPosition.set(enemyPosition.x+100, enemyPosition.y);
                e.setPosition(enemyPosition);
            }
            else if(o1 instanceof Friendly){
                f = (Friendly)o1;
                friendlyPosition = f.getPosition();
                friendlyPosition.set(friendlyPosition.x-100, friendlyPosition.y);
                f.setPosition(friendlyPosition);
            }
            else if(o1 instanceof Bullet){
                b =(Bullet)o1;
                b.setPosition(b.getX(), b.getY()-10);
            }
        }
        setChanged();
        notifyObservers(state);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if(source == timer){
            if(bombDrop <= 500){
                bombDrop += getRandom();
            }
            else{
                if(enemyPresent){
                    bulletFired("enemy");
                    bombDrop = 0;
                }
                
            }
            for(Object o: this.model){
                if(o instanceof Bullet){
                    Bullet b = (Bullet)o;
                    if(b.getY()<=0){
                        model.remove(b);
                        break;
                    }
                    else{
                        if(checkBulletCollision(b,enemyPosition)){
                            System.out.println("Enemy Hit");
                            healthChange = 5;                         
                            int num = updateHealth(healthChange,new Enemy());
                            setChanged();
                            notifyObservers(new HealthPacket(num, "enemy"));
                            healthChange = 0;
                        }
                        
                        b.setPosition(b.getX(),b.getY()-20);
                        
                    }
                    
                }
                else if(o instanceof EnemyBomb){
                    EnemyBomb b = (EnemyBomb)o;
                    if(b.getY() >= 600){
                        model.remove(b);
                        break;
                    }
                    else{
                        if(checkBulletCollision(b,friendlyPosition)){
                            System.out.println("Friend Hit");
                            healthChange = 5;                          
                            int num = updateHealth(healthChange,new Friendly());
                            setChanged();
                            notifyObservers(new HealthPacket(num, "friendly"));
                            healthChange = 0;
                           
                        }
                        
                        b.setPosition(b.getX(),b.getY()+20);
                        
                    }
                    
                }
                else if(o instanceof Enemy){
                    Enemy e = (Enemy)o;
                    if(e.getHealth() < 0){
                        model.remove(e);
                        enemyPresent = false;
                        break;
                    }
                    enemyPosition = e.getPosition();
                    if(reverseEnemy == true){
                        enemyPosition.set(enemyPosition.x+5, enemyPosition.y);
                        if(enemyPosition.x >=700) reverseEnemy = false;
                        
                    }
                    else if(reverseEnemy == false){
                        enemyPosition.set(enemyPosition.x-5, enemyPosition.y);
                        if(enemyPosition.x <=0) reverseEnemy = true;
                    }

                    e.setPosition(enemyPosition);
                }
                
            }
        }
        
        setChanged();
        notifyObservers(state);
        
    }
    
    private Boolean checkBulletCollision(AbstractGameObject b, Vector e){
        Boolean strike;
        if(((b.getX() + 100) > e.x && (b.getX() - 100) < e.x) && ((b.getY() + 20) > e.y && (b.getY() - 20) < e.y)){
            return strike = true;
        }
        else{
            return strike = false;
        }
        
       
        
    }
    
    public void moveFriendly(Object o1){
        for(Object o : this.model){
            Friendly f;
            if (o instanceof Friendly){
                f = (Friendly) o;
                if("left".equals(o1) && friendlyPosition.x > 0){
                    friendlyPosition.set(friendlyPosition.x - 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
                else if("right".equals(o1) && friendlyPosition.x < 600){
                    friendlyPosition.set(friendlyPosition.x + 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
            }
        }
        setChanged();
        notifyObservers(state);
    }
    
    public void bulletFired(String ID){
        if("friendly".equals(ID)){
            Bullet b = new Bullet();
            b.setPosition(friendlyPosition.x, friendlyPosition.y);
            this.model.add(b);
            setChanged();
            notifyObservers(state);
        }
        else if("enemy".equals(ID)){
            EnemyBomb b = new EnemyBomb();
            b.setPosition(enemyPosition.x, enemyPosition.y);
            this.model.add(b);
            setChanged();
            notifyObservers(state);
        }
    }

    @Override
    public Shape getShape(Object o1) {
        Shape s = null;
        if(o1 instanceof Enemy){
           enemyShape.setFrame(enemyPosition.x, enemyPosition.y, 100, 100);
           s = enemyShape;
        }
        else if (o1 instanceof Friendly){
           friendlyShape.setFrame(friendlyPosition.x, friendlyPosition.y, 50, 50);
           s = friendlyShape;
        }
        return s;
    }
    
    public Color getElementColor(Object o1){
        Color c = null;
        if(o1 instanceof Enemy){
           c = Color.BLUE;
        }
        else if (o1 instanceof Friendly){
           c = Color.RED;
        }
        return c;
    }
    
    public void init(){
        this.model.clear();
        
        for(int i = 0; i < objectLimit ; i++){
            addElements();
        }
    }

    public void addElements() {
        
        
        if(model.size() == objectLimit) return;
        if(objectsOnModel == 0){
            Enemy e = new Enemy();
            e.setPosition(enemyPosition);
            e.setHealth(100);
            setChanged();
            notifyObservers(new HealthPacket(e.getHealth(),"enemy"));
            model.add(e);
            objectsOnModel++;
        }
        else if(objectsOnModel == 1){
            Friendly f = new Friendly();
            f.setPosition(friendlyPosition);
            f.setHealth(100);
            setChanged();
            notifyObservers(new HealthPacket(f.getHealth(),"friendly"));
            model.add(f);
            objectsOnModel++;
        }
    }

    public ArrayList<Object> getModel() {
        return model;
    }

    private int updateFriendlyHealth(int healthChange) {
        int curHealth = 0;
        for(Object o: this.model){
            if(o instanceof Friendly){
                Friendly f = (Friendly)o;
                f.setHealth(f.getHealth() - healthChange);
                curHealth = f.getHealth();
            }
        }
        return curHealth;
    }

    private int updateEnemyHealth(int healthChange) {
        int curHealth = 0;
        for(Object o: this.model){
            if(o instanceof Enemy){
                Enemy f = (Enemy)o;
                f.setHealth(f.getHealth() - healthChange);
                curHealth = f.getHealth();
            }
        }
        return curHealth;
    }
    private int updateHealth(int healthChange,AbstractGameObject o){
        int curHealth = 0;
        for(Object o1 : this.model){
            if(o1.getClass() == o.getClass()){
                AbstractGameObject test = (AbstractGameObject) o1;
                test.setHealth(test.getHealth() - healthChange);
                curHealth = test.getHealth();
            }
        }
        return curHealth;
    }

    
    
    
    
    
    
    
}
