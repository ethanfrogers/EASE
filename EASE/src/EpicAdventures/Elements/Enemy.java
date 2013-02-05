/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author efrogers_it
 */
public class Enemy extends AbstractGameObject{
    
    public Enemy(){
       try{
            this.setImg(ImageIO.read(new File("alien")));
       }
       catch(IOException ioe){
           System.out.println("Cannot find image");
       }
    }
    
    
    
}
