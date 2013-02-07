/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;

import EpicAdventures.Elements.Bullet;
import EpicAdventures.Elements.Enemy;
import EpicAdventures.Elements.EnemyBomb;
import EpicAdventures.Elements.Friendly;
import MVCFramework.AbstractDisplayPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;


/**
 *
 * @author efrogers_it
 */
public class GameDisplayPanel extends AbstractDisplayPanel implements Observer {
    
    private static int ROWS = 20;
    private static int COLS = ROWS;
    private final GameEnsemble model;
    private BufferedImage image;
    private int[] iArray = {0,0,0,255};
    
    
    public GameDisplayPanel(final GameEnsemble model){
        this.model = model;
        this.setPreferredSize(new Dimension(700,600));
        this.addComponentListener(new ComponentAdapter() {
            @Override
            // Handle resize of window. Changes the model's walls.
            public void componentResized(ComponentEvent e) {
            	
            	System.out.println("resized");
                Component c = (Component) e.getSource();
                model.setWalls(COLS, ROWS,
                    c.getWidth() - COLS, c.getHeight() - ROWS);
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g){
        if(image == null) initImage();
        if(model.getModel().isEmpty())model.init();
        
        long start = System.currentTimeMillis();
        
        //g.setColor(Color.LIGHT_GRAY);
        Image b = null;
        try{
             b =  ImageIO.read(new File(GameDisplayPanel.class.getResource("8bitbg.png").getPath()));
        }
        catch(IOException e){
            System.err.println("Cannot read input file");
        }
        int w = getWidth();
        int h = getHeight();
        g.drawImage(b, 0, 0, this);
        //g.fillRect(0, 0, w, h);
        
        Graphics2D g2D = (Graphics2D)g;
        
        for(Object o : model.getModel()){
            Shape s = null;//model.getShape(o);
            Image i = null;
            if(!model.getEnemyState()){
                    g2D.drawString("Player 1 wins.", 700/2, 600/2);
                }
            
            if(o instanceof Enemy){
                Enemy e = (Enemy) o;
                if(!(e.getHealth() <= 0)){
                    i = e.getImg();
                    g2D.drawImage(i, (int)e.getX(), (int)e.getY(), this);
                }
                else
                    g2D.drawString("Player 1 wins.", 700/2, 600/2);
                
                
            }
            else if(o instanceof Friendly){
                Friendly f = (Friendly)o;
                if(!(f.getHealth() <= 0)){
                    i = f.getImg();
                    g2D.drawImage(i, (int)f.getX(), (int)f.getY(), this);
                }
                else
                    g2D.drawString("Aleins wins.", 700/2, 600/2);
            }
            else if(o instanceof Bullet){
                Bullet bul = (Bullet)o;
                i = bul.getImg();
                g2D.drawImage(i, (int)bul.getX(), (int)bul.getY(), this);
            }
            else if(o instanceof EnemyBomb){
                EnemyBomb bul = (EnemyBomb)o;
                i = bul.getImg();
                g2D.drawImage(i, (int)bul.getX(), (int)bul.getY(), this);
            }
            
            
        }
        
        this.setPaintTime(System.currentTimeMillis()-start); 
        
    }
    
    private void initImage() {
        image = (BufferedImage) createImage(COLS, ROWS);
    }

    @Override
    public void update(Observable o, Object o1) {
        if("changed".equals(o1)){
            this.repaint();
            
        }
    }
    
    
}
