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
public class DoorTest {

    private final Player p;
    private final Room outside;
    private final Room inside;
    private final Key myKey;

    public DoorTest() {
        p = new Player();
        outside = new Room();
        inside = new Room();
        myKey = new Key();
    }

    /**
     * Test of enter method, of class Door.
     */
    @Test
    public void testEnter() {

        // Create instances of DoorTest to test different conditions
        DoorTest t1 = new DoorTest();
        DoorTest t2 = new DoorTest();
        DoorTest t3 = new DoorTest();
        DoorTest t4 = new DoorTest();

        // Create instances of Door to test different conditions
        Door d1 = new Door(t1.outside, t1.inside, t1.myKey);
        Door d2 = new Door(t2.outside, t2.inside, t2.myKey);
        Door d3 = new Door(t3.outside, t3.inside, t3.myKey);
        Door d4 = new Door(t4.outside, t4.inside, t4.myKey);

        t1.p.setRoom(t1.outside);   // set location for t1 to test inner if condition
        t1.p.pickUp(t1.myKey);      // pickup key for door d1

        assertTrue(d1.enter(t1.p).equals("Your key works! The door creaks open,\n"
                + "and slams behind you after you pass through.\n\n"));     // assert that the proper message is returned for entering

        t2.p.setRoom(inside);       // set location for t2 to test else if condition
        t2.p.pickUp(t2.myKey);      // pickup key for door d2

        assertTrue(d2.enter(t2.p).equals("Your key works! The door creaks open,\n"
                + "and slams behind you after you pass through.\n\n"));     // assert that the proper message is returned for leaving 
        
        
        t3.p.setRoom(outside);      // set location for t3 to test else condition
                                    // dont pickup a key
        
        assertTrue(d3.enter(t3.p).equals("You don't have the key for this door!\n"
               + "Sorry.\n\n"));    // assert that the proper message is returned for not having a key
        
        t4.p.setRoom(inside);       // set location for t4 
        t4.p.pickUp(t2.myKey);      // pickup key for door d2, the wrong key
        
        assertTrue(d4.enter(t4.p).equals("You don't have the key for this door!\n"
               + "Sorry.\n\n"));    // assert that the proper message is returned for not having the right key

    }

}
