/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCFramework;

import javax.swing.JPanel;

/**
 *
 * @author efrogers_it
 */
public abstract class AbstractDisplayPanel extends JPanel {
    
    private boolean useGradient = true;
    private long paintTime;
    
    
    public void useGradient(boolean state){useGradient = state;}
    public long getPaintTime(){return paintTime;}
    
    
    
    
    
    
}
