/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Shell;

import KineticModel.*;
import EpicAdventures.MVC.GameControlPanel;
import EpicAdventures.MVC.GameDisplayPanel;
import EpicAdventures.MVC.GameEnsemble;
import MVCFramework.AbstractControlPanel;
import MVCFramework.AbstractDisplayPanel;
import MVCFramework.AbstractEnsemble;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author efrogers_it
 */
public class Shell {
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        
        System.out.append("What game would you like to play?\n"
                + "1)   KineticModel\n"
                + "2)   Epic Adventures In Software Engineering");
        final int choice = input.nextInt();
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameFactory.createGame(choice);
            }
        });
        
        
        
    }
    
}


class GameFactory{
    
    static AbstractEnsemble e;
    static AbstractDisplayPanel d;
    static AbstractControlPanel c;
    
    public static JFrame createGame(int i){
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(i == 1){
            frame.setTitle("KineticModel");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            e = new Ensemble();
            d = new DisplayPanel((Ensemble)e);
            panel.add(d,BorderLayout.WEST);
            c = new ControlPanel((DisplayPanel)d,(Ensemble)e);
            panel.add(c,BorderLayout.EAST);
            if (frame != null) {
                frame.getRootPane().setDefaultButton(
                c.getDefaultButton());
             }
            
        }
        else if(i == 2){
            frame.setTitle("Epic Adventures");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            e = new GameEnsemble();
            d = new GameDisplayPanel((GameEnsemble)e);
            panel.add(d,BorderLayout.NORTH);
            c = new GameControlPanel((GameDisplayPanel)d,(GameEnsemble)e);
            panel.add(c,BorderLayout.SOUTH);
            if (frame != null) {
                frame.getRootPane().setDefaultButton(
                c.getDefaultButton());
             }
        }
        
        frame.pack();
        frame.setVisible(true);
        return frame;
        
        
        
    }    
    
    
}
