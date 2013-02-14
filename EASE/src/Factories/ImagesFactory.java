/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import EpicAdventures.Elements.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author efrogers_it
 */
public class ImagesFactory {
    
    
    public static BufferedImage createImage(AbstractGameObject o){
        
        File f = null; String path = ""; BufferedImage b = null;
        if(o instanceof Enemy){
            path = Enemy.class.getResource("alien.png").getPath();
        }
        else if(o instanceof Friendly){
            path = Friendly.class.getResource("tank.png").getPath();
        }
        else if(o instanceof Bullet){
            path = Bullet.class.getResource("bullet.png").getPath();
        }
        else if(o instanceof EnemyBomb){
            path = EnemyBomb.class.getResource("enemyBomb.png").getPath();
        }
        
        f = new File(path);
        try{
            b = ImageIO.read(f);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
        return b;
    }
}
