/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author efrogers_it
 */
public class Enemy extends AbstractGameObject{
    
    public Enemy(){
       File f = new File(Friendly.class.getResource("alien.png").getPath());
       try{
            this.setImg(ImageIO.read(f));
       }
       catch(IOException ex){
           ex.printStackTrace();
       }
    }
    
    
    
}
