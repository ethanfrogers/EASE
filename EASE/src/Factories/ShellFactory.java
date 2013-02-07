/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Factories;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author efrogers_it
 */
public class ShellFactory {
    
    protected static ActionListener listener = null;
    
    public static JFrame createShell(ActionListener a){
        listener = a;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout(2,2));
        
        
        JLabel lbl_Choose = new JLabel();
        lbl_Choose.setText("Which game would you like to play?");
        
        JButton btn_Game1 = new JButton();
        JButton btn_Game2 = new JButton();
        
        btn_Game1.setText("KineticModel");
        btn_Game1.setActionCommand("game1");
        btn_Game1.addActionListener(listener);
        
        btn_Game2.setText("Epic Adventures in Software Engineering");
        btn_Game2.setActionCommand("game2");
        btn_Game2.addActionListener(listener);
        
        panel.add(lbl_Choose,BorderLayout.NORTH);
        panel.add(btn_Game1, BorderLayout.WEST);
        panel.add(btn_Game2,BorderLayout.EAST);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        
        return frame;
    }
}
