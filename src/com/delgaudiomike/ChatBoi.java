package com.delgaudiomike;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.event.WindowEvent;
import java.lang.Math;


public class ChatBoi extends JFrame implements KeyListener{

    JPanel panel = new JPanel();
    JTextArea dialogText = new JTextArea(20,50);
    JTextArea inputText = new JTextArea(1,50);
    JScrollPane scrollWindow = new JScrollPane(
            dialogText,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            System.out.println("Enter was pressed");
            inputText.setEditable(false); //disable further input

            String grabInput = inputText.getText(); //grab the input
            inputText.setText(""); //clear the screen
            pushText("--> Your Name:\t" + grabInput);


        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    /**
     * Constructor for ChatBoi, called upon startup
     * Displays title of UI Box, resizability, size, color, and adding
     * the panel components together.
     */
    public ChatBoi(){
        super("Chat Bot");
        setSize(600,400);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialogText.setEditable(false);
        inputText.addKeyListener(this);

        panel.add(scrollWindow);
        panel.add(inputText);
        panel.setBackground(new Color(255,200,0));
        add(panel);

        setVisible(true);
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


    public static void main(String[] args) {
        System.out.println("Starting ChatBoi!");
        new ChatBoi();

    }


}
