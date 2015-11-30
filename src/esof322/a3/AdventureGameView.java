/*
* ESOF322, Project 2
* Nicolas Moore, Dominik Pruss, Philip Wipf, James Soddy
*/

package esof322.a3;

import javax.swing.*;


import BreezySwing.*;

public class AdventureGameView extends GBFrame{

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// Window objects --------------------------------------
   JLabel welcomeLabel =
     addLabel("Welcome to the Adventure Game " + 
              "(inspired by an old game called the Colossal Cave Adventure)." +
            " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
	    1,1,5,1);
   
   JLabel viewLable = addLabel ("Your View: ",2,1,1,1);
   JTextArea viewArea = addTextArea("Start",3,1,4,3); 

   JLabel carryingLable = addLabel ("You are carying: ",6,1,1,1);
   JTextArea carryingArea = addTextArea("Nothing",7,1,4,3);

   JLabel separator1 = addLabel
   ("-----------------------------------------------------------------"
	 , 10,1,4,1);


   JLabel choiceLabel    = addLabel
      ("Choose a direction, pick-up, or drop an item" ,11,1,5,1);

   JButton grabButton = addButton ("Grab an item", 12, 5,1,1);
   JButton dropButton = addButton ("Drop an item", 13, 5,1,1);
   
   JButton northButton = addButton ("North", 12,2,1,1);
   JButton southButton = addButton ("South", 14,2,1,1);
   JButton eastButton = addButton ("East",   13,3,1,1);
   JButton westButton = addButton ("West",   13,1,1,1);
   JButton upButton = addButton ("Up", 12,3,1,1);
   JButton downButton = addButton ("Down", 14,3,1,1);

   AdventureGameModelFacade model;
   
   // Constructor-----------------------------------------------
        
   public AdventureGameView(){
      setTitle ("Adventure Game");
      model = new AdventureGameModelFacade();
      viewArea.setEditable (false);
      carryingArea.setEditable (false);
      displayCurrentInfo("");

      
   } 
   
   
   // buttonClicked method--------------------------------------

   // Modified method to precess messages from the movement
   //  buttons
   public void buttonClicked (JButton buttonObj){
      String actionMessage = "";
      if (buttonObj == upButton)
         actionMessage = model.goUp();

      else if (buttonObj == downButton)
	 actionMessage = model.goDown();

      else if (buttonObj == northButton)
	 actionMessage = model.goNorth();

      else if (buttonObj == southButton)
	 actionMessage = model.goSouth();

      else if (buttonObj == eastButton)
	 actionMessage = model.goEast();

      else if (buttonObj == westButton)
	 actionMessage = model.goWest();

      else if (buttonObj == grabButton){
     
	 model.grab();
}
      else if (buttonObj == dropButton)
	 model.drop();

      displayCurrentInfo(actionMessage);
  }
      
   
   // Private methods-------------------------------------------

   // Modified method to accept String argument. This will allow
   //  it to display a movement message from a wall, door or room
   private void displayCurrentInfo(String message){
	 viewArea.setText(message + model.getView());
	 carryingArea.setText(model.getItems());
	 }

  
   
   public static void main (String[] args){
      JFrame view = new AdventureGameView();
      view.setSize (800, 600); /* was 400, 250  */             
      view.setVisible(true);    
   }                    
}
