package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.main.GameUtils;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Combat {
    private Player player;
    private Enemy enemy;
    private KeyHandler keyHandler;
    private TextField textField;

    //Track the State of Combat
    private boolean isPlayerTurn;
    public boolean playerDead = false;
    public boolean enemyDead = false;
    public int currentPlayerHealth;
    private int currentEnergy;
    private int currentStimpaks;
    private boolean shieldUp = false;
    private boolean trojanSent = false;
    private boolean eaglesEyeActivated = false;
    public boolean scanned = false;
    public boolean finalBossDead = false;
    Random random = new Random();

    public Combat(Player player, Enemy enemy, KeyHandler keyHandler, TextField textField) {
        this.player = player;
        this.enemy = enemy;
        this.keyHandler = keyHandler;
        this.textField = textField;
        if (player != null) {
            currentPlayerHealth = player.currentHealth;
            currentEnergy = player.getEnergy();
            currentStimpaks = player.getMaxStimpaks();
        }
        isPlayerTurn = true;
    }

    public void processTurn() {
        //Debugging
        if (player == null) {
            System.out.println("player is null in processTurn()");
            return;
        }
        // Check if combat has ended
        if (playerDead || enemyDead) {
            enemy.setInCombat(false);
            return;
        }
        if (isPlayerTurn) {
            handlePlayerTurn();
        } else {
            handleEnemyTurn();
        }
        // Check for end of combat conditions after each turn
        checkCombatEndConditions();
    }

    private void handlePlayerTurn() {
        // Check for player's action based on key inputs
        if (keyHandler.pressed1) {
            shoot(player.getDamage());
            isPlayerTurn = false; // End player's turn
        } else if (keyHandler.pressed2) {
            shield(70); //  Shields the player from the next attack
            isPlayerTurn = true;
        } else if (keyHandler.pressed3) {
            repair(); // Repair player
            isPlayerTurn = true;
        } else if (keyHandler.pressed4) {
            eaglesEye(80); // Activate garanteed shoot
            isPlayerTurn = true;
        } else if (keyHandler.pressed5) {
            sendTrojan(100); // Weakens the enemy and blocks their view a little
            isPlayerTurn = true;
        } else if (keyHandler.pressed6) {
            scan(50); // Lets you see the enemies HP
            isPlayerTurn = true;
        }
        // Reset keyHandler flags after processing
        resetKeyHandlerFlags();
    }

    private void handleEnemyTurn() {
        takeDamage(enemy.getDamage());
        currentEnergy += player.getEnergyRecovery();
        if (currentEnergy >= player.getEnergy()) {
            currentEnergy = player.getEnergy();
        }
        isPlayerTurn = true; // Switch back to player's turn
    }

    private void checkCombatEndConditions() {
        if (currentPlayerHealth <= 0) {
            GameUtils.sleep(240);
            playerDead = true;
            // Handle player's defeat
        }
        if (enemy.getHealth() <= 0) {
            GameUtils.sleep(240);
            if (enemy.skinNr == 7) {
                finalBossDead = true;
            }
            enemyDead = true;
            player.currentHealth = currentPlayerHealth;
            player.setExp(enemy.getGivesExp());
            enemy.markForRemoval = true;
        }
        // Handle enemy defeat
    }



    private void shoot(int damage) {
        player.setAlternateImage(player.shoot, 60);
        if (attackHits() || eaglesEyeActivated) {
            int finalDamage = damage;
            if (trojanSent) {
                finalDamage *= 1.5;
            }
            if (isCriticalHit()) {
                finalDamage *= 1.5;
                textField.addMessage("Critical Hit!");
            }
            enemy.takeDamage(finalDamage);
            textField.addMessage("Shot enemy with " + finalDamage + " damage.");
        } else {
            textField.addMessage("Your gun jammed!");
        }
    }

    private void shield(int energyCost) {
        if (energyCost <= currentEnergy) {
            currentEnergy -= energyCost;
            shieldUp = true;
            textField.addMessage("SHIELD UP");
        } else textField.addMessage("Not enough energy");
    }

    private void repair() {
        if (currentStimpaks > 0) {
            currentPlayerHealth += player.getHealing(); //  Healing
            if(currentPlayerHealth > player.getMaxHealth()) {
                currentPlayerHealth = player.getMaxHealth();
            }
            --currentStimpaks; // Reduces the count of stimpaks
            textField.addMessage("Repaired! Player Health: " + currentPlayerHealth);
            textField.addMessage("Stimpaks remaining: " + currentStimpaks);
        } else textField.addMessage("No stimpaks remaining");
    }

    private void eaglesEye(int energyCost) {
        if (energyCost <= currentEnergy) {
            currentEnergy -= energyCost;
            eaglesEyeActivated = true;
            textField.addMessage("EAGLES EYE ACTIVATED");
        } else textField.addMessage("Not enough energy");
    }
    private void sendTrojan(int energyCost) {
        if (energyCost <= currentEnergy) {
            currentEnergy -= energyCost;
            trojanSent = true;
            textField.addMessage("TROJAN SENT");
        } else textField.addMessage("Not enough energy");
    }
    private void scan(int energyCost) {
        if (!scanned) {
            if (energyCost <= currentEnergy) {
                currentEnergy -= energyCost;
                GameUtils.sleep(360);
                scanned = true;
                textField.addMessage("SCANNING.");
                textField.addMessage("SCANNING. .");
                textField.addMessage("SCANNING. . .");
                textField.addMessage("ENEMY SCANNED");
            } else textField.addMessage("Not enough energy");
        } else textField.addMessage("Already scanned this enemy!");
    }

    private void takeDamage(int damage) { // Enemy attack
        if (trojanSent) {
            damage /= 1.2;
            trojanSent = false;
        }
        if (shieldUp) {
            textField.addMessage("Shield deflected " + damage + " damage.");
            shieldUp = false;
        } else if (enemyAttackHits()) {
            currentPlayerHealth -= damage;
            textField.addMessage("Enemy damaged you! Lost " + damage + " HP.");
        } else {
            textField.addMessage("Enemy attack missed!");
        }
    }

    private boolean attackHits() {
        double hitProbability = 0.75; // 75% chance to hit
        return random.nextDouble() < hitProbability;
    }

    private boolean isCriticalHit() {
        double critChance = 0.20; // 20% chance for a critical hit
        return random.nextDouble() < critChance;
    }

    private boolean enemyAttackHits() {
        double hitProbability = enemy.hitPropability; // chance to hit
        return random.nextDouble() < hitProbability;
    }

    private void resetKeyHandlerFlags() {
        keyHandler.pressed1 = false;
        keyHandler.pressed2 = false;
        keyHandler.pressed3 = false;
    }
    public int getCurrentStimpaks() {
        return currentStimpaks;
    }
    public int getCurrentEnergy() {
        return currentEnergy;
    }
    public void drawTextField(Graphics2D g2, int x, int y, int width, int height) {
        BufferedImage textFieldImage;
        try {
            textFieldImage = ImageIO.read(getClass().getResourceAsStream("/res/combat/textfield.png"));
            g2.drawImage(textFieldImage, x, y, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
