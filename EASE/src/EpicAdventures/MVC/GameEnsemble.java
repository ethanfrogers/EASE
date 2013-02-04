/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;
import EpicAdventures.Elements.Enemy;
import EpicAdventures.Elements.Friendly;
import MVCFramework.AbstractEnsemble;
import MVCFramework.Vector;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
/**
 *
 * @author efrogers_it
 */
public class GameEnsemble extends AbstractEnsemble {

    private int iterations = 0;
    private int objectLimit = 2;
    private int objectsOnModel = 0;
    private Vector enemyPosition = new Vector(top, left);
    private Vector friendlyPosition = new Vector(bottom,right);
    //private Rectangle2D enemyShape = new Rectangle2D.Double(enemyPosition.x,enemyPosition.y, 100, 100);
    //private Rectangle2D friendlyShape = new Rectangle2D.Double(friendlyPosition.x,friendlyPosition.y,50,50);
    private Rectangle2D enemyShape = new Rectangle2D.Double();
    private Rectangle2D friendlyShape = new Rectangle2D.Double();
    String state = "changed";
    
    
    public GameEnsemble(){
        super();
       
    }
    
    
    @Override
    public void iterate() {
        System.out.println("Model iterate called.");
        for(Object o1 : this.model){
            Enemy e;
            Friendly f;
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
        }
        setChanged();
        notifyObservers(state);
    }
    
    public void moveFriendly(Object o1){
        for(Object o : this.model){
            Friendly f;
            if (o instanceof Friendly){
                f = (Friendly) o;
                if("left".equals(o1)){
                    friendlyPosition.set(friendlyPosition.x - 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
                else if("right".equals(o1)){
                    friendlyPosition.set(friendlyPosition.x + 50, friendlyPosition.y);
                    f.setPosition(friendlyPosition);
                }
            }
        }
        setChanged();
        notifyObservers(state);
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
            e.setHealth(86);
            setChanged();
            notifyObservers(e.getHealth());
            model.add(e);
            objectsOnModel++;
        }
        else if(objectsOnModel == 1){
            Friendly f = new Friendly();
            f.setPosition(friendlyPosition);
            model.add(f);
            objectsOnModel++;
        }
    }

    public ArrayList<Object> getModel() {
        return model;
    }
    
    
    
    
    
    
    
}
