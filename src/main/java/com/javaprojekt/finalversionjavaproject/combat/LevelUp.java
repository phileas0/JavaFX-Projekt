package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.entity.Player;

public class LevelUp {
    private Player player;
    public LevelUp(Player player) {
        this.player = player;
    }

    public void applyLevelUp() {
        // Raise stats
        player.setMaxHealth();
        player.setDamage();
        player.setEnergy();
        player.setEnergyRecovery();
        player.setMaxStimpaks();
        player.setHealing();

        // Take rest exp into next bar
        int help = player.exp - player.expToNextLevel;
        player.exp = 0;
        player.exp += help;
        player.expToNextLevel += 5; // Increase threshold for next level

        player.setLevelUp();
    }
}
