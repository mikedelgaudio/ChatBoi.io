package com.delgaudiomike;
//Swing Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

//AWT imports
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

//Lang Imports
import java.lang.Math;


public class ChatBoi extends JFrame implements KeyListener{

    //Declare variables to make JPanel, JTextAreas, and Scroll Window
    JPanel panel = new JPanel();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    JTextArea dialogText = new JTextArea(30,65);
    JTextArea inputText = new JTextArea(1,40);
   // JLabel instructUser = new JLabel()
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
            inputText.setEditable(false); //disable further input


            if(inputText.getText().strip() == ""){
                pushText("ChatBoi: You cannot enter an empty string. Please try again.");
                inputText.setText(""); //clear the screen
            } else {
                String grabInput = inputText.getText(); //grab the input
                inputText.setText(""); //clear the screen

                //Grabbing the users name on initial bootup.
                if(introduction == false){
                    pushText("You: " + grabInput);
                    grabInput = grabInput.substring(0,1).toUpperCase() + grabInput.substring(1).toLowerCase();  //uppercase first letter
                    pushText("ChatBoi: Nice to meet you, " + grabInput + "!");
                    userName = grabInput;
                    introduction = true;
                } else {
                    //If the user has already input their name, just push their text to screen.
                    pushText( userName + ": " + grabInput);
                    processUserInput(grabInput);
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
    private ChatBoi(){
        super("ChatBoi");

        //Could make a UI function??
        //setSize(screenSize.width/3,screenSize.height/3);
        setSize(600, 600);
        setResizable(true);
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
/*        panel.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Frame closing");
            }
        });*/

        //Startup text
        pushText("ChatBoi: Hi there! I'm ChatBoi, your personal small talk robot. Who am I talking to??");
    }

    /**
     * Push Text to Screen using the JTextArea
     * @param s input provided to display text on screen
     */
    private void pushText(String s){
        dialogText.setText(dialogText.getText() + "--> " + s + "\n");
    }

    /**
     * Takes user input and decides what ChatBoi's response will be.
     * @param userInput is grabInput from when the Enter key is pressed
     */
    private void processUserInput(String userInput){
        pushText("ChatBoi: " + giveResponse( grabWords(userInput) ) );
    }

    /**
     * Provides a phrase for ChatBoi to respond with to user. Performs the main functionality.
     * @param givenKeyword keyword passed in found from other helper funcs
     * @return response for ChatBoi to say in processUserInput()
     */
    private String giveResponse(String givenKeyword){
        String response = "";
        boolean resultFound = false;

        if(resultFound == false){
            response += "default text randomizer here.";
        }
        return response;
    }

    /**
     * HELPER - Provides func processUserInput() with the necessary keyword for ChatBoi to respond to.
     * @param userInput
     * @return keyword for ChatBoi to use
     */
    private String grabWords(String userInput){
        //TODO
        String keyword = "";

        String wordFound = "";
        for (int i = 0; i < userInput.length(); i++){
            char c = userInput.charAt(i);
            if(c != ' '){
                wordFound += c;
            } else {
                //Word is complete, process to see if its a keyword
                keyword = searchForKeyword(wordFound);
                if(keyword != ""){
                    return keyword;
                }
            }
        }

        //make sure to have else where the keyword may be nil therefore ChatBoi will just say a default response
        return keyword;

    }

    /**
     * HELPER - Takes word found from grabWords() and decides if it is a known keyword.
     * @param wordFound is provided from
     * @return keyword for grabWords()
     */
    private String searchForKeyword(String wordFound){
        System.out.println("Word passed in searchForKeyword() --> " + wordFound);

        String keyword = "";

        System.out.println("Word found in searchForKeyword() --> " + keyword);
        return wordFound;
    }



    /**
     * Start of program, calls upon constructor to
     * make a single instance of ChatBoi
     * @param args command line (unused here)
     */
    public static void main(String[] args) {
        System.out.println("Starting ChatBoi!");
        SwingUtilities.invokeLater(() -> new ChatBoi());
    }


}
