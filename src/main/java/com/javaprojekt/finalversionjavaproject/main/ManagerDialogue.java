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
            textField.addMessage("YOU: \"I've been to hell to get here.\"");
            textField.addMessage("Manager: \"And who might you be?\"");
            textField.addMessage("YOU: \"I'm investigating the robot murders. Your robots!\"");
            textField.addMessage("Manager: \"My robots? A bold claim for an uninvited guest.\"");
            textField.addMessage("YOU: \"Cut the act. I need answers, and I believe you have them\"");
            textField.addMessage("Manager: \"If you are looking for answers...\"");
            textField.addMessage("Manager: \"... there is an abandoned building by the motel.\"");
            textField.addMessage("YOU: \"Why should I believe you?\"");
            textField.addMessage("Manager: \"You don't have to. But it's your best lead.\"");
            textField.addMessage("YOU: \"If you're playing me, I'll be back.\"");
            textField.addMessage("Manager: \"I look forward to your findings.\"");
            dialogueCounter++;
        }


    }

    public void drawMonologue() {
        if (dialogueCounter == 0) {
            textField.addMessage("YOU: \"Another one...\"");
            textField.addMessage("YOU: \"These streets are more wires than flesh now.\"");
            textField.addMessage("YOU: \"Manus Machina's east... \"");
            textField.addMessage("YOU: \"... where the robot factory looms.\"");
            textField.addMessage("YOU: \"The epicenter of our mechanical troubles.\"");
            textField.addMessage("YOU: \"These robots will be the cause...\"");
            textField.addMessage("YOU: \"...of human extinction.\"");
            dialogueCounter++;
        }
    }

    public void drawMonologue1() {
        if(dialogueCounter == 2){
            textField.addMessage("YOU: \"I have to find out who is behind this.\"");
            textField.addMessage("YOU: \"He has to be held accountable!\"");
            dialogueCounter++;
            ismonologue = true;
        }
    }
    public void setIsmonologue() {
        ismonologue = false;
    }
    public void drawManagerDialogue(){
        ismonologue = false;
        if(dialogueCounter == 3){
            textField.addMessage("YOU: \"The f****** Manager again? You are behind this?\"");
            textField.addMessage("Manager: \"Surprised, Detective?\"");
            textField.addMessage("YOU: \"Why did you do it? Why the murders?\"");
            textField.addMessage("Manager: \"You think it's just about murder?\"");
            textField.addMessage("Manager: \"It's about evolution. Progress\"");
            textField.addMessage("YOU: \"By killing innocent people?\"");
            textField.addMessage("Manager: \"Sacrifices are necessary.\"");
            textField.addMessage("Manager: \"Humanity is weak, flawed.\"");
            textField.addMessage("Manager: \"My robots, they are the next step.\"");
            textField.addMessage("Manager: \"Superior.\"");
            textField.addMessage("YOU: \"You're playing god at the cost of human lives.\"");
            textField.addMessage("Manager: \"Not playing, Detective.\"");
            textField.addMessage("Manager: \"I am shaping the future.\"");
            textField.addMessage("Manager: \"My robots are the key to transcending human limitations.\"");
            textField.addMessage("YOU: \"It's over. You're coming with me.\"");
            textField.addMessage("Manager: \"Go ahead, try to stop me.\"");
            dialogueCounter++;
        }

    }
}
