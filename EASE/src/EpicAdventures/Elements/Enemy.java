/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

import MVCFramework.Vector;
import java.awt.Color;

/**
 *
 * @author efrogers_it
 */
public class Enemy extends Object {
    
     private double height = 0;
    private double width = 0;
    private Color color = new Color(0,true);
    private Vector position = new Vector();
    
    public Enemy(){
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    
    public Vector getPosition(){
        return new Vector(position);
    }
    
    public Vector getPosition(Vector p){
        position.x = p.x;
        position.y = p.y;
        return position;
    }
    
    public double getX(){
        return position.x;
    }
    
    public double getY(){
        return position.y;
    }
    
    public void setPosition(Vector p){
        position.x = p.x;
        position.y = p.y;
    }
    
    public void setPosition(double x, double y){
        position.x = x;
        position.y = y;
    }
    
}
