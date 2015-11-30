/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esof322.a4;

import esof322.a4.Item;
import esof322.a4.Player;
import esof322.a4.Room;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicolas
 */
public class RoomTest {
    //add, remove, enter, exit
    private final Room r1;
    private final Room r2;
    private final Player p;
    
    public RoomTest() {
        
        // create player
        p = new Player();
        
        // create some rooms
        r1 = new Room();
        r2 = new Room();
        
        // connect rooms
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        
        // set entryMessage
        r1.setEntryMessage("You are in r1.");
        r2.setEntryMessage("You are in r2.");
    }

    /**
     * Test of addItem method, of class Room.
     */
    @Test
    public void testAddItem() {
        RoomTest t1 = new RoomTest();               // create test instance
        Item key = new Item();                      // create some items
        Item goldbar = new Item();
        assertArrayEquals(t1.r1.getRoomContents(),new Item[]{});                // makes sure room contents is correct
        t1.r1.addItem(key);                         // add items to room
        t1.r1.addItem(goldbar);
        assertArrayEquals(t1.r1.getRoomContents(),new Item[]{key,goldbar});     // makes sure room contents is correct
    }

    /**
     * Test of removeItem method, of class Room.
     */
    @Test
    public void testRemoveItem() {
        RoomTest t2 = new RoomTest();               // create test instance
        Item key = new Item();                      // create some items
        Item goldbar = new Item();
        t2.r1.addItem(key);                         // add items to room
        t2.r1.addItem(goldbar);
        t2.r1.removeItem(key);                      // remove the key
        assertArrayEquals(new Item[]{goldbar},t2.r1.getRoomContents());     // makes sure room contents is correct
        t2.r1.removeItem(goldbar);                  // remove the goldbar
        assertArrayEquals(new Item[]{},t2.r1.getRoomContents());            // makes sure room contents is correct
        
    }
    /**
     * Test of enter method, of class Room.
     */
    @Test
    public void testEnter() {
        RoomTest t3 = new RoomTest();                           // create test instance
        t3.p.setLoc(t3.r1);                                     // set location
        assertTrue(t3.r1.enter(p).equals("You are in r1."));    // make sure proper message is output
        assertFalse(t3.r1.enter(p).equals("You are in r3."));   // make sure proper message is output
    }

    /**
     * Test of exit method, of class Room.
     */
    @Test
    public void testExit() {
        RoomTest t4 = new RoomTest();                             // create test instance
        t4.p.setLoc(t4.r1);                                       // set location
        assertTrue(t4.r1.exit(2, p).equals("You are in r2."));    // make sure proper message is output
        assertFalse(t4.r1.exit(5, p).equals("You are in r3."));   // make sure proper message is output
    }
    
}
