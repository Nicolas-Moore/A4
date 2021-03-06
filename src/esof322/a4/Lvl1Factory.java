/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esof322.a4;

/**
 *
 * @author Nick
 */
public class Lvl1Factory implements AdventureGameFactory, java.io.Serializable {

    private Room entrance;

    @Override
    public Room createLvl() {
        // The outside: 
        Room outside = new Room();
        outside.setDesc(
                "You are standing outside of a dark damp cave.\n"
                + "You can hear sounds coming from inside.\n"
                + "You feel the presence of a strong object pulling you in (outside).");

        // Room 1:
        Room r1 = new Room();
        r1.setDesc(
                "As you enter the cave the light from outside begins to fade.\n"
                + "There is a narrow, dark passage to the east (r1).");

        // Connect the outside to Room 1:
        outside.setSide(5, r1);
        r1.setSide(4, outside);
        entrance = outside;

        // Room 2:
        Room r2 = new Room();
        r2.setDesc(
                "You are in a gloomy room and the floor feels like it is moving.\n"
                + "You look down and see thousands of bugs on the floor.\n"
                + "There is a dim light to the west, and a narrow\n"
                + "dark hole to the east only about 18 inches high (r2).");

        // Room 3:
        Room r3 = new Room();
        r3.setDesc("You manage to squeez through the hole.\n"
                + "You can feel the pull of the object getting stronger.\n"
                + "There is a slippery narrow passage to the east,\n"
                + " the way you came in to the west,\n"
                + "and a deep hole that appears to have no bottom\n"
                + "in the middle of the room (r3).");

        // Connect Rooms 1, 2, & 3:
        r1.setSide(2, r2);
        r2.setSide(3, r1);
        r2.setSide(2, r3);
        r3.setSide(3, r2);

        // Room 4:
        Room r4 = new Room();
        r4.setDesc("There are bats all over the ceiling, more than\n"
                + "likely where the noise is coming from.\n"
                + "There is a tunnel to the north, and a very\n"
                + "dark hole to the south. (r4).");

        // Room 5:
        Room r5 = new Room();
        r5.setDesc("There is a dim light from above and the shrieks\n"
                + "are clearly coming from a passageway to the east (r5).");

        // Room 6:
        Room r6 = new Room();
        r6.setDesc("There is hardly any light in this room\n"
                + "but you can barely make out a gleam of light coming\n"
                + "from the corner of the room. (r6).");

        // Room 7:
        Room r7 = new Room();
        r7.setEntryMessage("You slide your way in, "
                + "barely keeping your footing.\n\n");
        r7.setDesc("This room is very damp. There are puddles on the floor\n"
                + "and a steady dripping from above\n"
                + "the pull of the object feels weaker. (r7).");

        // Connect rooms 3, 4, 5, 6, & 7.
        r3.setSide(2, r4);
        r3.setSide(5, r5);
        r4.setSide(3, r3);
        r4.setSide(5, r7);
        r5.setSide(4, r3);
        r5.setSide(2, r6);
        r6.setSide(3, r5);
        r7.setSide(4, r4);

        // Room 8:
        Room r8 = new Room();
        r8.setDesc("A horse scampers past you, or is it a unicorn?\n"
                +"You ask yourself what a horse is doing in here.\n"
                + "A narrow passage runs to the east and a narrower one\n"
                + "runs to the west, the pull of the object is very strong (r8).");

        // Room 9:
        Room r9 = new Room();
        Teleporter teleporter = new Teleporter();
        teleporter.setDesc("A strange gadget");
        r9.addItem(teleporter);
        r9.setDesc("The room is very dark, you can barely see anything.\n"
                + "There doesn't seem to be any exit aside from where\n"
                + "you came in, the pull of the object is overwhelming (r9).");

        // Room 10:
        Room r10 = new Room();
        r10.setDesc("It looks like someone has been here.\n"
                + "There is a pile of candy wrappers on the floor,\n"
                + "perhaps it was the horse. \n"
                + "Wait, there is a trap door on the floor,\n"
                + "but it is locked (r10).");

        // Room 11:
        Room r11 = new Room();
        r11.setDesc("This room is very dark. You can just barely see (r11).");
        Treasure theTreasure = new Treasure();
        theTreasure.setDesc("A bag filled with gold bars.");
        r11.addItem(theTreasure);

        // Lets connect them:
        r4.setSide(0, r8);
        r8.setSide(1, r4);
        r8.setSide(3, r9);
        r8.setSide(2, r10);
        r9.setSide(2, r8);
        r10.setSide(3, r8);

        // Create a key and put it in r6:
        Key theKey = new Key();
        theKey.setDesc("A shiny gold key.");
        r6.addItem(theKey);

        // We add a door between r10 and r11: 
        Door theDoor = new Door(r10, r11, theKey);
        r10.setSide(5, theDoor);
        r11.setSide(4, theDoor);

        // Now return the entrance:
        entrance = outside;
        return entrance;
    }

}
