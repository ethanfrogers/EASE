/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;

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
    JButton btn_Iterate = new JButton();
    JButton btn_Left = new JButton();
    JButton btn_Right = new JButton();
    JLabel lbl_Health = new JLabel();
    JProgressBar prog_HealthBar = new JProgressBar(JProgressBar.HORIZONTAL,0,100);
    int healthStatus = 0;
    
    public GameControlPanel(GameDisplayPanel view, GameEnsemble model){
        model.addObserver(view);
        model.addObserver(this);
        this.model = model;
        this.view = view;
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.addKeyListener(this);
        
        btn_Iterate.setText("Iterate");
        btn_Iterate.setActionCommand("next");
        btn_Iterate.addActionListener(this);
        
        
        btn_Left.setText("<-");
        btn_Left.setActionCommand("left");
        btn_Left.addActionListener(this);
        
        btn_Right.setText("->");
        btn_Right.setActionCommand("right");
        btn_Right.addActionListener(this);
        
        lbl_Health.setText("Nexus Health: ");
        
        prog_HealthBar.setValue(healthStatus);
        prog_HealthBar.setStringPainted(true);
        
        panel.add(btn_Left);
        panel.add(btn_Iterate);
        panel.add(btn_Right);
        panel.add(lbl_Health);
        panel.add(prog_HealthBar);
        
        
        
        this.add(panel);
        
        
    }
    
    public JButton getDefaultButton() {
        return btn_Iterate;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        String cmd = ae.getActionCommand();
        
        if("next".equals(cmd)){
            model.iterate();
        }
        else if("left".equals(cmd) || "right".equals(cmd)){
            model.moveFriendly(cmd);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("I TYPED A KEY!");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
        System.out.println("I PRESSED A KEY!");
        
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
            healthStatus -= 5;
            prog_HealthBar.setValue(healthStatus);
        }
        else if (o1 instanceof Integer){
            healthStatus = (Integer)o1;
            prog_HealthBar.setValue(healthStatus);
        }
    }

    

    
    
    
}
