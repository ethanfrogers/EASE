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
    
    public GameControlPanel(GameDisplayPanel view, GameEnsemble model){
        model.addObserver(view);
        this.model = model;
        this.view = view;
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        btn_Iterate.setText("Iterate");
        btn_Iterate.setActionCommand("next");
        btn_Iterate.addActionListener(this);
        panel.add(btn_Iterate);
        
        this.add(panel);
        
        
    }
    
    public JButton getDefaultButton() {
        return btn_Iterate;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Iterate button pressed.");
        Object source = ae.getSource();
        String cmd = ae.getActionCommand();
        
        if("next".equals(cmd)){
            model.iterate();
        }
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
