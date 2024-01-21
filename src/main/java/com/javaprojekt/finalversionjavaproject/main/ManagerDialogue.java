package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.combat.TextField;

import java.awt.*;

public class ManagerDialogue {
    GamePanel gamePanel;
    TextField textField;
    int dialogueCounter = 0;
    boolean ismonologue = false;


    public ManagerDialogue(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.textField = gamePanel.textField;
    }
    public void drawDialogue(){
        if(dialogueCounter==0){
            textField.addMessage("You: \"Who are you selling these robots to\"?");
            textField.addMessage("Manager: \"Well I don't know him personally.\"");
            textField.addMessage("Manager: \"I sell them to a guy who lives across the street.\"");
            textField.addMessage("Manager: \"You should be careful if you meet him.\"");
            textField.addMessage("Manager: \"He is one of the most dangerous people in this city.\"");
            textField.addMessage("You: \"Think about what you're doing to society dude.\"");
            dialogueCounter++;
        }


    }

    public void drawMonologue() {
        if(dialogueCounter==1){
            textField.addMessage("You: \"I have to find out who is behind this.\"");
            textField.addMessage("You: \"He has to be held accountable!\"");
            dialogueCounter++;
            ismonologue = true;
        }
    }
}
