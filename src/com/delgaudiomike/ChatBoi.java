package com.delgaudiomike;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.event.WindowEvent;
import java.lang.Math;


public class ChatBoi extends JFrame implements KeyListener{

    //Declare variables to make JPanel, JTextAreas, and Scroll Window
    JPanel panel = new JPanel();
    JTextArea dialogText = new JTextArea(20,50);
    JTextArea inputText = new JTextArea(1,50);
    //dialogText.setLineWrap(true);

    JScrollPane scrollWindow = new JScrollPane(
            dialogText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    boolean introduction = false;
    String userName = "";

    /**
     * Overrides event listener method for what key is pressed.
     * Handles when the user hits enter and error correction
     * @param e is derived from KeyEvent and is a letter/symbol/int/fn on keyboard
     */
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("Enter was pressed");
            inputText.setEditable(false); //disable further input


            if(inputText.getText().strip() == ""){
                pushText("--> ChatBoi: You cannot enter an empty string. Please try again.\n");
                inputText.setText(""); //clear the screen
            } else {
                String grabInput = inputText.getText(); //grab the input
                inputText.setText(""); //clear the screen

                //Grabbing the users name on initial bootup.
                if(introduction == false){
                    pushText("--> You: " + grabInput + "\n");
                    grabInput = grabInput.substring(0,1).toUpperCase() + grabInput.substring(1).toLowerCase();  //uppercase first letter
                    pushText("--> ChatBoi: Nice to meet you, " + grabInput + "!\n");
                    userName = grabInput;
                    introduction = true;
                } else {
                    //If the user has already input their name, just push their text to screen.
                    pushText("--> " + userName + ": " + grabInput + "\n");
                }
            }


        }
    }

    /**
     * Overrides event listener method for what key is released.
     * Handles after when the user hits enter
     * Re-enables ability to type in text box.
     * @param e is derived from KeyEvent and is a letter/symbol/int/fn on keyboard
     */
    @Override
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            inputText.setEditable(true);
        }
    }

    /**
     * Not in use
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){
    }

    /**
     * Constructor for ChatBoi, called upon startup
     * Displays title of UI Box, resizability, size, color, and adding
     * the panel components together.
     */
    public ChatBoi(){
        super("ChatBoi");

        //Could make a UI function??
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/3,screenSize.height/3);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialogText.setEditable(false);
        inputText.addKeyListener(this);

        //Set line wraps
        dialogText.setLineWrap(true);
        inputText.setLineWrap(true);

        //Format fonts/colors
        Font dialogFont = new Font("Courier New", Font.BOLD, 14);
        dialogText.setFont(dialogFont);
        //dialogText.setForeground(Color.BLUE);

        //Mesh together the panel
        panel.add(scrollWindow);
        panel.add(inputText);
        panel.setBackground(new Color(163, 38, 56));
        add(panel);

        setVisible(true);

        //Startup text
        pushText("--> ChatBoi: Hi there! I'm ChatBoi, your personal small talk robot. Who am I talking to??\n");
    }

    /**
     * Push Text to Screen using the JTextArea
     * @param s input provided to display text on screen
     */
    private void pushText(String s){
        dialogText.setText(dialogText.getText() + s);
    }


    void WindowClosing(WindowEvent e){
        System.out.println("Closing ChatBoi");
    }


    /**
     * Start of program, calls upon constructor to
     * make a single instance of ChatBoi
     * @param args command line (unused here)
     */
    public static void main(String[] args) {
        System.out.println("Starting ChatBoi!");
        new ChatBoi();
    }


}
