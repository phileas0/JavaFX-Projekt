package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Enemy;

public class EnemySetter {
    GamePanel gamePanel;

    public EnemySetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

   public void setEnemies() {
        // Example of setting an enemy at a specific location
        Enemy enemy1 = new Enemy();
        enemy1.x = 7 * gamePanel.tileSize; // X position of the enemy
        enemy1.y = 5 * gamePanel.tileSize;  // Y position of the enemy
        gamePanel.listOfEnemies.add(enemy1);
    }
}
