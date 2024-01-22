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
        if(dialogueCounter == 1){
            textField.addMessage("You: \"Who are you selling these robots to?\"");
            textField.addMessage("Manager: \"Well I don't know him personally.\"");
            textField.addMessage("Manager: \"I sell them to a guy who lives across the street.\"");
            textField.addMessage("Manager: \"You should be careful if you meet him.\"");
            textField.addMessage("Manager: \"He is one of the most dangerous people in this city.\"");
            textField.addMessage("You: \"You're the one who makes all of this possible for him.\"");
            textField.addMessage("You: \"Think about what you're doing to society dude.\"");
            dialogueCounter++;
        }


    }

    public void drawMonologue() {
        if (dialogueCounter == 0) {
            textField.addMessage("You: \"Another one...\"");
            textField.addMessage("You: \"these streets are more wires than flesh now.\"");
            textField.addMessage("You: \"Manus Machina... where the robot factory looms.\"");
            textField.addMessage("You: \"The epicenter of our mechanical troubles.\"");
            textField.addMessage("You: \"These robots will be the cause...\"");
            textField.addMessage("You: \"...of human extinction.\"");
            dialogueCounter++;
        }
    }

    public void drawMonologue1() {
        if(dialogueCounter == 2){
            textField.addMessage("You: \"I have to find out who is behind this.\"");
            textField.addMessage("You: \"He has to be held accountable!\"");
            dialogueCounter++;
            ismonologue = true;
        }
    }
    public void drawManagerDialogue(){
        ismonologue = false;
        if(dialogueCounter == 3){
            textField.addMessage("You: \"It was you the whole time?\"");
            textField.addMessage("Manager: \"Ahh... You finally made it\"");
            textField.addMessage("You: \"Why are you doing this? Isn't your company one of..\"");
            textField.addMessage("You: \".. the biggest in the world?\"");
            textField.addMessage("Manager: \"Yes you are correct. You are probably wondering..\"");
            textField.addMessage("Manager: \"..why my Robots are out there murdering people.\"");
            textField.addMessage("You: \"I actually don't care!\"");
            textField.addMessage("Manager: \"Well... they are collecting people's debt\"");
            textField.addMessage("Manager: \" Otherwise I would have gone bankrupt within a week\"");
            textField.addMessage("You: \"THAT'S NO EXCUSE!\"");
            dialogueCounter++;
        }

    }
}
