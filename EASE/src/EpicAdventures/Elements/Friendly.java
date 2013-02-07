/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.Elements;

import java.awt.Color;
import MVCFramework.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author efrogers_it
 */
public class Friendly extends AbstractGameObject {
    
    public Friendly(){
        //File f = new File("C:/Users/efrogers_it/Dropbox/CS/4322/PROJ3/EASE/src/EpicAdventures/Elements/tank.png");
        File f = new File(Friendly.class.getResource("tank.png").getPath());
        System.out.println(f.getAbsolutePath());
        
       
       //System.out.println(f.getPath());
       try{
            this.setImg(ImageIO.read(f));
       }
       catch(IOException ex){
           ex.printStackTrace();
       }
    }
}
