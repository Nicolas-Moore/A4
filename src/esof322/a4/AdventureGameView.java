/*
 * ESOF322, Project 2
 * Nicolas Moore, Dominik Pruss, Philip Wipf, James Soddy
 */
package esof322.a4;

import javax.swing.*;

import BreezySwing.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdventureGameView extends GBFrame {

    /**
     *
     */
    private AdventureGameFactory f = null;
    private static final long serialVersionUID = 1L;
    private FactoryProducer fp = new FactoryProducer();

// Window objects --------------------------------------
    JLabel welcomeLabel
            = addLabel("Welcome to the Adventure Game "
                    + "(inspired by an old game called the Colossal Cave Adventure)."
                    + " Java implementation Copyright (c) 1999-2012 by James M. Bieman",
                    1, 1, 5, 1);

    JLabel viewLable = addLabel("Your View: ", 2, 1, 1, 1);
    JTextArea viewArea = addTextArea("Start", 3, 1, 4, 3);

    JLabel carryingLable = addLabel("You are carying: ", 6, 1, 1, 1);
    JTextArea carryingArea = addTextArea("Nothing", 7, 1, 4, 3);

    JLabel separator1 = addLabel("-----------------------------------------------------------------", 10, 1, 4, 1);

    JLabel choiceLabel = addLabel("Choose a direction, pick-up, or drop an item", 11, 1, 5, 1);

    JButton grabButton = addButton("Grab an item", 12, 5, 1, 1);
    JButton dropButton = addButton("Drop an item", 13, 5, 1, 1);

    JButton saveButton = addButton("Save", 14, 5, 1, 1);    //added button for saving a game
    JButton loadButton = addButton("Load", 15, 5, 1, 1);    //added button for loading a game

    JButton teleportButton;     // added button to use teleporter item if possesed

    JButton northButton = addButton("North", 12, 2, 1, 1);
    JButton southButton = addButton("South", 14, 2, 1, 1);
    JButton eastButton = addButton("East", 13, 3, 1, 1);
    JButton westButton = addButton("West", 13, 1, 1, 1);
    JButton upButton = addButton("Up", 12, 3, 1, 1);
    JButton downButton = addButton("Down", 14, 3, 1, 1);

    AdventureGameModelFacade model;

    // Constructor-----------------------------------------------
    public AdventureGameView() {
        // get what the user would like to do (what lvl to play)
        getSelection();

        // if they went with lvl 1 difficulty add the teleport button 
        if (f.getClass() == Lvl1Factory.class) {
            teleportButton = addButton("Teleport", 12, 1, 1, 1);
        }
        setTitle("Adventure Game");
        model = new AdventureGameModelFacade(f);
        viewArea.setEditable(false);
        carryingArea.setEditable(false);
        displayCurrentInfo("");

    }

    // buttonClicked method--------------------------------------
    // Modified method to precess messages from the movement
    //  buttons
    public void buttonClicked(JButton buttonObj) {
        String actionMessage = "";
        if (buttonObj == upButton) {
            actionMessage = model.goUp();
        } else if (buttonObj == downButton) {
            actionMessage = model.goDown();
        } else if (buttonObj == northButton) {
            actionMessage = model.goNorth();
        } else if (buttonObj == southButton) {
            actionMessage = model.goSouth();
        } else if (buttonObj == eastButton) {
            actionMessage = model.goEast();
        } else if (buttonObj == westButton) {
            actionMessage = model.goWest();

            //added logic for what to do if save button is clicked
        } else if (buttonObj == saveButton) {
            try {
                model.saveGame();
            } catch (IOException ex) {
                Logger.getLogger(AdventureGameView.class.getName()).log(Level.SEVERE, null, ex);
            }
            //added logic for what to do if load button is clicked
        } else if (buttonObj == loadButton) {
            model.loadGame();
        } //added logic for what to do if teleport button is clicked
        else if (buttonObj == teleportButton) {
            model.teleport();

        } else if (buttonObj == grabButton) {

            model.grab();
        } else if (buttonObj == dropButton) {
            model.drop();
        }

        displayCurrentInfo(actionMessage);
    }

    // Private methods-------------------------------------------
    // Modified method to accept String argument. This will allow
    //  it to display a movement message from a wall, door or room
    private void displayCurrentInfo(String message) {
        viewArea.setText(message + model.getView());
        carryingArea.setText(model.getItems());
    }

    /*
     Method to get what lvl difficulty the user would like or if they want to load a saved game.
     */
    private void getSelection() {
        Object[] options = {"Level 0",
            "Level 1"};
        Component frame = null;
        int n = JOptionPane.showOptionDialog(frame,
                "Choose which difficulty level you would like to play",
                "Select difficulty",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        f = fp.getFactory(n);

    }

    public static void main(String[] args) {
        JFrame view = new AdventureGameView();
        view.setSize(800, 600); /* was 400, 250  */

        view.setVisible(true);
    }
}
