
/*
* ESOF322, Project 2
* Nicolas Moore, Dominik Pruss, Philip Wipf, James Soddy
*/
package esof322.a3;

import java.util.ArrayList;

/**
 * Adventure Game Program Code Copyright (c) 1999 James M. Bieman
 *
 * To compile: javac AdventureGame.java To run: java AdventureGame
 *
 * The main routine is AdventureGame.main
 * 
 * Changed myItems to an arraylist
 */
public class Player {

    private Room myLoc;

    private ArrayList<Item> myItems = new ArrayList<Item>(); 

    private int itemCount = 0;

    public void setRoom(Room r) {
        myLoc = r;
    }

    public String look() {
        return myLoc.getDesc();
    }
    
    // Convert from void to String method
    //  This allows door/wall and room entry messages
    //  to be printed by the calling method rather than the
    //  room object, preserving model/view separation
    public String go(int direction) {
        return myLoc.exit(direction, this);
    }
    
    // Changed to work with arraylist
    public void pickUp(Item i) {
        if (itemCount < 2) {
            myItems.add(i);
            itemCount++;
            myLoc.removeItem(i);
        }
    }

    /* Method to check if player has a particular item
     *
     * Added some brackets
     */
    public boolean haveItem(Item itemToFind) {
        for (int n = 0; n < itemCount; n++) {
            if (myItems.get(n) == itemToFind) {
                return true;
            }
        }
        return false;
    }

    // Changed to work with an arraylist
    public void drop(int itemNum) {
        if (itemNum > 0 & itemNum <= itemCount) {
            switch (itemNum) {
                case 1: {
                    myLoc.addItem(myItems.get(0));
                    myItems.remove(0);
                    itemCount--;
                    break;
                }
                case 2: {
                    myLoc.addItem(myItems.get(1));
                    myItems.remove(1);
                    itemCount--;
                    break;
                }
            }
        }
    }

    public void setLoc(Room r) {
        myLoc = r;
    }

    public Room getLoc() {
        return myLoc;
    }

    public String showMyThings() {
        String outString = "";
        if (itemCount == 0) {
            outString = "You have no items.";
        } else {
            for (int n = 0; n < itemCount; n++) {
                outString = outString + Integer.toString(n + 1) + ": "
                        + myItems.get(n).getDesc() + " ";
            }
        }
        return outString;
    }

    /* Method to return an array of the items held by player
     *
     * Implemented method
     */
    public Item[] getMyThings() {
        Item[] myThings = new Item[myItems.size()];
        myThings = myItems.toArray(myThings);
        return myThings;
    }

    public boolean handsFull() {
        return itemCount == 2;
    }

    public boolean handsEmpty() {
        return itemCount == 0;
    }

    public int numItemsCarried() {
        return itemCount;
    }

}
