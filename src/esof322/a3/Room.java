/*
* ESOF322, Project 2
* Nicolas Moore, Dominik Pruss, Philip Wipf, James Soddy
*/
package esof322.a3;

/**  Adventure Game  Program Code
     Copyright (c) 1999 James M. Bieman

     To compile: javac AdventureGame.java
     To run:     java AdventureGame

     The main routine is AdventureGame.main
     
     Update August 2010: refactored Vector contents into ArrayList<Item> contents.
      This gets rid of the use of obsolete Vector and makes it type safe.
				    
**/

// class Room


import java.util.ArrayList;
import java.util.ListIterator;

/* Class to represent a room in Adventure Game
 *
 * Added an instance variable to display an optional message upon entering
 *  a room.
 */
public class Room implements CaveSite {

  private String description;
  
  // String to contain a message which should be displayed upon entering room
  private String entryMessage;

  private CaveSite[] side = new CaveSite[6];

  private ArrayList<Item> contents = new ArrayList<Item>();

  Room() {
    side[0] = new Wall();
    side[1] = new Wall();
    side[2] = new Wall();
    side[3] = new Wall();
    side[4] = new Wall();
    side[5] = new Wall();
    entryMessage = "";
    }

  public void setEntryMessage(String d){
    entryMessage = d;
  }
  
  public void setDesc(String d){
    description = d;
    }

  public void setSide(int direction, CaveSite m){
   side[direction] = m;
   }

  public void addItem(Item theItem){
   contents.add(theItem); 
   }

  public void removeItem(Item theItem){
   contents.remove(theItem);
   }

  public boolean roomEmpty(){
	 return contents.isEmpty();
  }

  public Item[] getRoomContents(){
   Item[] contentsArray = new Item[contents.size()];
   contentsArray = contents.toArray(contentsArray);
   return contentsArray;
  }


  public String enter(Player p) {
   p.setLoc(this);
   return entryMessage;
  }

  // Converted from void to String method
  //  This allows door/wall and room entry messages to be printed by
  //  the calling method rather than the room object, thus
  //  preserving model/view separation
  public String exit(int direction, Player p){
   return side[direction].enter(p);
  }

  public String getDesc(){
   ListIterator<Item> roomContents = contents.listIterator(); 
   String contentString = "";
   while(roomContents.hasNext())
     contentString = 
	contentString + (roomContents.next()).getDesc() + " ";

     return description + '\n' + '\n' +
     "Room Contents: " + contentString + '\n';
   }

}

