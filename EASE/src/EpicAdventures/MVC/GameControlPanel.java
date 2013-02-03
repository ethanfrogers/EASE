/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures.MVC;

import MVCFramework.AbstractControlPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author efrogers_it
 */
public class GameControlPanel extends AbstractControlPanel implements ActionListener, ChangeListener{
    
    GameDisplayPanel view;
    GameEnsemble model;
    JButton btn_Iterate = new JButton();
    JButton btn_Left = new JButton();
    JButton btn_Right = new JButton();
    
    
    public GameControlPanel(GameDisplayPanel view, GameEnsemble model){
        model.addObserver(view);
        this.model = model;
        this.view = view;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        
        btn_Iterate.setText("Iterate");
        btn_Iterate.setActionCommand("next");
        btn_Iterate.addActionListener(this);
        
        
        btn_Left.setText("<-");
        btn_Left.setActionCommand("left");
        btn_Left.addActionListener(this);
        
        btn_Right.setText("->");
        btn_Right.setActionCommand("right");
        btn_Right.addActionListener(this);
        
        panel.add(btn_Left);
        panel.add(btn_Iterate);
        panel.add(btn_Right);
        
        
        
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
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
