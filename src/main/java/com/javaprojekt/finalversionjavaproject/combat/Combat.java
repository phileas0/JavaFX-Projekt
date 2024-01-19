package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;

public class Combat {
    private Player player;
    private Enemy enemy;
    private KeyHandler keyHandler;

    //Track the State of Combat
    private boolean isPlayerTurn;
    public boolean playerDead = false;
    public boolean enemyDead = false;
    public int currentPlayerHealth;
    private int currentEnergy;
    private int currentStimpaks;

    public Combat(Player player, Enemy enemy, KeyHandler keyHandler) {
        this.player = player;
        this.enemy = enemy;
        this.keyHandler = keyHandler;
        if (player != null) {
            currentPlayerHealth = player.getMaxHealth();
            currentEnergy = player.getEnergy();
            currentStimpaks = player.getMaxStimpaks();
        }
        isPlayerTurn = true;
    }

    public void processTurn() {
        //Debugging
        if (player == null) {
            System.out.println("payer is null in processTurn()");
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
            hack(75); //  Energy cost
            isPlayerTurn = true;
        } else if (keyHandler.pressed3) {
            repair(); // Repair player
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
            playerDead = true;
            // Handle player's defeat
        }
        if (enemy.getHealth() <= 0) {
            enemyDead = true;
            player.setExp(enemy.getGivesExp());
            enemy.markForRemoval = true;
            // Handle enemy defeat
        }
    }

    private void shoot(int damage) {
        enemy.takeDamage(damage);
        System.out.println("Shot enemy with " + damage + " damage");
        System.out.println("Enemy health down to " + enemy.health);
    }

    private void hack(int energyCost) {
        if (energyCost < currentEnergy) {
            currentEnergy -= energyCost;
            System.out.println("Used " + energyCost + " energy. Energy remaining: " + currentEnergy);
        } else System.out.println("Not enough energy");
    }

    private void repair() {
        if (currentStimpaks > 0) {
            currentPlayerHealth += player.getHealing(); //  Healing
            if(currentPlayerHealth > player.getMaxHealth()) {
                currentPlayerHealth = player.getMaxHealth();
            }
            --currentStimpaks; // Reduces the count of stimpaks
            System.out.println("Repaired! Player Health: " + currentPlayerHealth);
            System.out.println("Stimpaks remaining: " + currentStimpaks);
        } else System.out.println("No stimpaks remaining");
    }

    private void takeDamage(int damage) {
        currentPlayerHealth -= damage;
        System.out.println("Enemy deals " + damage + "damage");
        System.out.println("Player Health:  " + currentPlayerHealth);
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
}
