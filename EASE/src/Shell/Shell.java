/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Shell;

import KineticModel.*;
import EpicAdventures.MVC.GameControlPanel;
import EpicAdventures.MVC.GameDisplayPanel;
import EpicAdventures.MVC.GameEnsemble;
import Factories.ShellFactory;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author efrogers_it
 */
public class Shell implements ActionListener {
    
    public Shell(){
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        String cmd = ae.getActionCommand();
        JFrame f = new JFrame();
        int id = 0;
        if(cmd.equals("game1")){
            id = 1;
        }
        else if(cmd.equals("game2")){
            id = 2;
        }
        final int id2 = id;
        EventQueue.invokeLater(new Runnable() {
            public void run() {
               GameFactory.createGame(id2);
            }
        });
        
    }
    
    public static void main(String [] args){
       
        final Shell shell = new Shell();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                ShellFactory.createShell(shell);
            }
        });
        
        
        
    }

    
}
class GameFactory{
    
    
    
    public static JFrame createGame(int i){
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        if(i == 1){
            frame.setTitle("KineticModel");
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            Ensemble e = new Ensemble();
            DisplayPanel d = new DisplayPanel(e);
            panel.add(d,BorderLayout.WEST);
            ControlPanel c = new ControlPanel(d,e);
            panel.add(c,BorderLayout.EAST);
            if (frame != null) {
                frame.getRootPane().setDefaultButton(
                c.getDefaultButton());
             }
            
        }
        else if(i == 2){
            frame.setTitle("Epic Adventures");
            panel = new JPanel();
            panel.setLayout(new BorderLayout());
            GameEnsemble e = new GameEnsemble();
            GameDisplayPanel d = new GameDisplayPanel(e);
            panel.add(d,BorderLayout.NORTH);
            GameControlPanel c = new GameControlPanel((GameDisplayPanel)d,(GameEnsemble)e);
            panel.add(c,BorderLayout.SOUTH);
            if (frame != null) {
                frame.getRootPane().setDefaultButton(
                c.getDefaultButton());
             }
        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        return frame;
        
        
        
    }    
    
    
}
