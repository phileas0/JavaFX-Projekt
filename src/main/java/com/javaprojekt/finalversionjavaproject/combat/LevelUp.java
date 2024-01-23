package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.entity.Player;

import javax.swing.*;

public class LevelUp {
    private Player player;

    public LevelUp(Player player) {
        this.player = player;
    }

    public void applyLevelUp() {// Apply level up
        // Standard stat increase
        player.setMaxHealth();
        player.setDamage();
        player.setEnergy();
        player.setEnergyRecovery();
        player.setMaxStimpaks();
        player.setHealing();
        player.currentHealth = player.maxHealth;
        player.currentStimpaks = player.maxStimpaks;

        // Take rest exp into next bar
        int overflowExp = player.exp - player.expToNextLevel;
        player.exp = overflowExp;
        player.expToNextLevel += 5; // Increase threshold for next level

        player.setLevelUp();
    }

}