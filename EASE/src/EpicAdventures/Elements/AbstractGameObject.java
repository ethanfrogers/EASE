/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

import MVCFramework.Vector;
import java.awt.Color;
import java.awt.Image;

/**
 *
 * @author efrogers_it
 */
public class AbstractGameObject extends Object {
    
    protected double height = 0;
    protected double width = 0;
    protected int health = 0;
    protected Color color = new Color(0,true);
    protected Vector position = new Vector();
    protected Image img = null;

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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
   
    
    
    
}
