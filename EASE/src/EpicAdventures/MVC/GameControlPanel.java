/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;

import EpicAdventures.Elements.HealthPacket;
import MVCFramework.AbstractControlPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author efrogers_it
 */
public class GameControlPanel extends AbstractControlPanel implements ActionListener, ChangeListener, Observer, KeyListener{
    
    GameDisplayPanel view;
    GameEnsemble model;
    
    
    JLabel lbl_Health = new JLabel();
    JLabel lbl_friendHealth = new JLabel();
    
    JProgressBar prog_EnemyHealthBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
    JProgressBar prog_FriendlyHealthBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
    
    int enemyHealthStatus = 0;
    int friendlyHealthStatus = 0;
    
    public GameControlPanel(GameDisplayPanel view, GameEnsemble model){
        model.addObserver(view);
        model.addObserver(this);
        this.model = model;
        this.view = view;
        addKeyListener(this);
        this.setFocusable(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        
        lbl_Health.setText("Nexus HP: ");
        lbl_friendHealth.setText("HP:");
        
        prog_EnemyHealthBar.setValue(enemyHealthStatus);
        prog_EnemyHealthBar.setStringPainted(true);
        
        prog_FriendlyHealthBar.setValue(friendlyHealthStatus);
        prog_FriendlyHealthBar.setStringPainted(true);
        
        panel.add(lbl_friendHealth);
        panel.add(prog_FriendlyHealthBar);            
        panel.add(lbl_Health);
        panel.add(prog_EnemyHealthBar);
        
        this.add(panel);
        
        
    }
    
    public JButton getDefaultButton() {
        return new JButton();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        String cmd = ae.getActionCommand();
        
        if("next".equals(cmd)){
            model.iterate();
        }
        else if("left".equals(cmd) || "right".equals(cmd)){
            model.moveObject(cmd,"friendly");
        }
        else if("space".equals(cmd)){
            model.bulletFired("friendly");
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        String cmd = "";
        
        
        if(keyCode == KeyEvent.VK_LEFT){
            cmd = "left";
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            cmd = "right";
        }
        else if(keyCode == KeyEvent.VK_SPACE){
            cmd = "space";
        }
        
        actionPerformed(new ActionEvent(this,0,cmd));
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    

    @Override
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Observable o, Object o1) {
        if("changed".equals(o1)){
            prog_EnemyHealthBar.setValue(enemyHealthStatus);
        }
        else if (o1 instanceof HealthPacket){
            HealthPacket h = (HealthPacket)o1;
            if("enemy".equals(h.getId())){
                enemyHealthStatus = h.getHealth();
                prog_EnemyHealthBar.setValue(enemyHealthStatus);
            }
            else if("friendly".equals(h.getId())){
                friendlyHealthStatus = h.getHealth();
                prog_FriendlyHealthBar.setValue(friendlyHealthStatus);
            }
        }
    }

    

    
    
    
}
