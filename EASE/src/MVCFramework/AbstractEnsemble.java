/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCFramework;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author efrogers_it
 */
public abstract class AbstractEnsemble extends Observable {
    
    //dimensions
    public int left = 0;
    public int top = 0;
    public int right = 500;
    public int bottom = 500;
    
    //Model
    public ArrayList<Object> model;
    
    
    public AbstractEnsemble(){
        model = new ArrayList<Object>();
    }
    
    public void setWalls(int l, int t, int r, int b) {
        left = l;
        top = t;
        right = r;
        bottom = b;
    }
    
    protected abstract void iterate();
    public abstract Shape getShape(Object o1);
    public abstract void init();
    
}
