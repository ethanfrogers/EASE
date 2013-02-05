/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;

import EpicAdventures.Elements.Enemy;
import EpicAdventures.Elements.Friendly;
import MVCFramework.AbstractDisplayPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;


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
    
    protected void paintComponent(Graphics g){
        if(image == null) initImage();
        if(model.getModel().isEmpty())model.init();
        
        long start = System.currentTimeMillis();
        
        g.setColor(Color.LIGHT_GRAY);
        int w = getWidth();
        int h = getHeight();
        g.fillRect(0, 0, w, h);
        
        Graphics2D g2D = (Graphics2D)g;
        
        for(Object o : model.getModel()){
            Shape s = null;//model.getShape(o);
            if(o instanceof Enemy){
                Enemy e = (Enemy) o;
                s=model.getShape(e);
                
            }
            else if(o instanceof Friendly){
                Friendly f = (Friendly)o;
                s=model.getShape(f);
            }
            g2D.setPaint(model.getElementColor(o));
            g2D.fill(s);
            
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
