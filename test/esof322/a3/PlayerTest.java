/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esof322.a3;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nicolas
 */
public class PlayerTest {

    private final Player p;
    private final Room r1;
    private final Room r2;
    private final Room r3;
    private final Room r4;
    private final Item key;
    private final Item goldbar;
    
   

    public PlayerTest() {
        
        // create a player
        p = new Player();

        // create some rooms
        r1 = new Room();
        r2 = new Room();
        r3 = new Room();
        r4 = new Room();

        // add descriptions for the rooms
        r1.setEntryMessage("You are in r1.");
        r2.setEntryMessage("You are in r2.");
        r3.setEntryMessage("You are in r3.");
        r4.setEntryMessage("You are in r4.");
        
        // connect the rooms in a square shape 
        r1.setSide(1, r2);
        r1.setSide(2, r4);
        r2.setSide(2, r3);
        r2.setSide(4, r1);
        r3.setSide(3, r2);
        r3.setSide(4, r4);
        r4.setSide(1, r3);
        r4.setSide(3, r1);
        
        key = new Item();
        goldbar = new Item();
    }

    /**
     * Test of go method, of class Player.
     */
    @Test
    public void testGo() {
        PlayerTest t1 = new PlayerTest();                   // create test instance
        t1.p.setLoc(t1.r1);                                 // set location to r1     
        assertTrue(t1.p.go(1).equals("You are in r2."));    // make sure proper message is output
        assertFalse(t1.p.go(1).equals("You are in r3."));   // make sure proper message is output
    }

    /**
     * Test of pickUp method, of class Player.
     */
    @Test
    public void testPickUp() {
        PlayerTest t2 = new PlayerTest();                   // create test instance
        t2.p.setLoc(t2.r1);                                 // set location to r1
        t2.r1.addItem(key);                                 // add items to room
        t2.r1.addItem(key);
        t2.r1.addItem(goldbar);
        t2.p.pickUp(key);                                   // pickup items
        t2.p.pickUp(goldbar);
        assertArrayEquals(new Item[]{key,goldbar},t2.p.getMyThings());     // make sure array is correct after picking up items
        t2.p.pickUp(key);                                   // try to pickup another item
        assertArrayEquals(new Item[]{key,goldbar},t2.p.getMyThings());     // make sure extra item didn't get put in array
        t2.p.drop(1);                                       // drop the key
        t2.r1.addItem(goldbar);                             // add item that is not in the room
        assertArrayEquals(new Item[]{goldbar},t2.p.getMyThings());         // make sure item didn't get put in array
        //expected, act
    }

    /**
     * Test of drop method, of class Player.
     */
    @Test
    public void testDrop() {
        PlayerTest t3 = new PlayerTest();                   // create test instance
        t3.p.setLoc(t3.r1);                                 // set location to r1
        t3.r1.addItem(key);                                 // add items to room
        t3.r1.addItem(goldbar);
        t3.p.pickUp(key);                                   // pickup items
        t3.p.pickUp(goldbar);
        t3.p.drop(1);                                       // drop the key
        assertArrayEquals(new Item[]{goldbar},t3.p.getMyThings()); // make sure array is correct after dropping items
        t3.p.pickUp(key);                                   // pickup key again
        t3.p.drop(2);                                       // drop key again to test switch statement
        assertArrayEquals(new Item[]{goldbar},t3.p.getMyThings()); // make sure array is correct after dropping items
        t3.p.drop(1);                                       // get itemCount to 0
        t3.p.drop(1);                                       // take itemNum > itemCount branch/ drop item not carried
        assertArrayEquals(new Item[]{},t3.p.getMyThings()); // make sure array is empty
        t3.p.drop(0);                                       // take itemNum < 0 branch
    }

}
