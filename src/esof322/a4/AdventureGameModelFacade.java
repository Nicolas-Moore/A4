/*
 * ESOF322, Project 2
 * Nicolas Moore, Dominik Pruss, Philip Wipf, James Soddy
 */
 
package esof322.a4;

import javax.swing.*;

public class AdventureGameModelFacade {

    // some private fields to reference current location,
    // its description, what I'm carrying, etc.
    //
    // These methods and fields are left as exercises.
    Player thePlayer;
    //Adventure theCave;
    Room startRm;

    /* 
     * Method to initialize the game and interface
     * 
     * Implemented method
     */
    
    // made facade take a factory object as well to know what Lvl of adventure to create
    AdventureGameModelFacade(AdventureGameFactory f) { 
        thePlayer = new Player();
        //theCave = new Adventure();
        startRm = f.createLvl();
        thePlayer.setRoom(startRm);
    }

    /*
     * Method to process a user 'up' input
     * 
     * Implemented and changed to String method
     */
    public String goUp() {
        return thePlayer.go(4);
    }

 /*
     * Method to process a user 'down' input
     * 
     * Implemented and changed to String method
     */
    public String goDown() {
        return thePlayer.go(5);
    }

 /*
     * Method to process a user 'north' input
     * 
     * Implemented and changed to String method
     */
    public String goNorth() {
        return thePlayer.go(0);
    }

 /*
     * Method to process a user 'south' input
     * 
     * IImplemented and changed to String method
     */
    public String goSouth() {
        return thePlayer.go(1);
    }

 /*
     * Method to process a user 'east' input
     * 
     * Implemented and changed to String method
     */
    public String goEast() {
        return thePlayer.go(2);
    }

 /*
     * Method to process a user 'west' input
     * 
     * Implemented and changed to String method
     */
    public String goWest() {
        return thePlayer.go(3);
    }

 /*
     * Method to process a user 'grab' input
     * 
     * Implemented method
     */
    public void grab() {
        Item[] roomContents = thePlayer.getLoc().getRoomContents();
        
        
        if (roomContents.length == 0) { // if there is nothing in the room.
            final JFrame noItem = new JFrame();
            JOptionPane.showMessageDialog(noItem, "There is nothing to pick up.");
            return;
        } 
        // if there is something in the room:
            if (thePlayer.handsFull()) { // if your hands are full
                final JFrame fullHands = new JFrame();
                JOptionPane.showMessageDialog(fullHands, "You cannot pick anything up, your hands are full.");
                return;
            }
            // your hands are not full and there is something to pick up
                final JFrame pickItem = new JFrame();
                Object[] choices = new Object[roomContents.length];
        
                // Get room contents
                for (int i = 0; i < roomContents.length; i++) {
                    choices[i] = "Pickup: " + roomContents[i].getDesc();
                }
                
                // Offer user choice of items and get input
                int n = JOptionPane.showOptionDialog(pickItem, "Which Item would you like to pick up?", // text prompt
                        "Item Choice Window", // window name
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        choices,// starting option
                        choices[choices.length - 1]// ending option
                );
                thePlayer.pickUp(roomContents[n]);//grab the item, duh
    }

    public void drop() {
        if (thePlayer.handsEmpty()) { // if there is nothing to drop.
            final JFrame noItem = new JFrame();
            JOptionPane.showMessageDialog(noItem, "You have nothing to drop.");
            return; // Nothing to see here, move along
        } 
        // If we have something to drop
            
            Item[] playerContents = thePlayer.getMyThings();
            final JFrame dropItem = new JFrame();
            Object[] choices = new Object[playerContents.length];
            
            // Get inventory contents
            for (int i = 0; i < playerContents.length; i++) {
                    choices[i] = "Drop: " + playerContents[i].getDesc();
                }
            
            // Offer user item options to drop and get their choice
            int n = JOptionPane.showOptionDialog(dropItem, "Which Item would you like to drop?", // text prompt
                    "Item Choice Window", // window name
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,// starting option
                    choices[choices.length - 1]// ending option
            );
            thePlayer.drop(n + 1); // Drop it like it's hot
    }

    /* Method to retrieve the room description
     *
     * Implemented this method.
     */
    public String getView() {
        return thePlayer.look();
    }

    /* Method to retrieve the list of player's items
     *
     * Implemented this method.
     */
    public String getItems() {
        return thePlayer.showMyThings();
    }

 // Surely you will need other methods to deal with
    // picking up and dropping things.
    // Surely not.
}
