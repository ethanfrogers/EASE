/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EpicAdventures;

import EpicAdventures.MVC.GameControlPanel;
import EpicAdventures.MVC.GameDisplayPanel;
import EpicAdventures.MVC.GameEnsemble;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author efrogers_it
 */
public class EpicAdventuresInSoftware extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EpicAdventuresInSoftware();
            }
        });
    }
    
    public EpicAdventuresInSoftware(){
        this.setTitle("EPIC ADVENTURES IN SOFTWARE!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(createEASE(this));
        this.pack();
        this.setVisible(true);
    }
    
    public static JPanel createEASE(JFrame frame){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        GameEnsemble model = new GameEnsemble();
        GameDisplayPanel view = new GameDisplayPanel(model);
        panel.add(view,BorderLayout.NORTH);
        GameControlPanel controls = new GameControlPanel(view,model);
        panel.add(controls,BorderLayout.SOUTH);
        if (frame != null) {
            frame.getRootPane().setDefaultButton(
                controls.getDefaultButton());
        }
        return panel;
    }
    
    
}
