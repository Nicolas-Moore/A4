package esof322.a4;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
				    
**/

// class Wall



public class Wall implements CaveSite ,java.io.Serializable {

 public String enter(Player p)
 {
    return "Ouch! That hurts!\n\n";
 }

}

