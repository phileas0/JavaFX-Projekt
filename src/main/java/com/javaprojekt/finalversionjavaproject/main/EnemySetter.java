package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Enemy;

import java.util.ArrayList;

public class EnemySetter {
    GamePanel gamePanel;


    public EnemySetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setEnemies(int mapNumber) {
        if(mapNumber == 0) {
            setEnemiesForMap0();
        } else if(mapNumber == 1) {
            setEnemiesForMap1();
        } else if(mapNumber == 2) {
            setEnemiesForMap2();
        } else if (mapNumber == 3) {
            setEnemiesForMap3();
        }
        System.out.println("Setting enemies for Map " + gamePanel.currentMap);
        // ... (dein bestehender Code)

        // Debugging-Ausgabe
        for (ArrayList<Enemy> enemies : gamePanel.getListOfEnemies()) {
            System.out.println("Number of enemies on current map: " + enemies.size());
        }
    }

    private void setEnemiesForMap3() {// Neue Enemies auf Map 3
        Enemy newEnemy1Map3 = new Enemy(10, 2, 0.85, 8, 0, 0);
        newEnemy1Map3.x = 13 * gamePanel.tileSize;
        newEnemy1Map3.y = 8 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy1Map3);

        // Füge hier weitere Enemies für Map 3 hinzu

        Enemy newEnemy2Map3 = new Enemy(10, 2, 0.85, 8, 0, 0);
        newEnemy2Map3.x = 4 * gamePanel.tileSize;
        newEnemy2Map3.y = 10 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy2Map3);

        // Füge hier weitere Enemies für Map 3 hinzu
    }

    private void setEnemiesForMap2() {// Neue Enemies auf Map 2
            Enemy newEnemy1Map2 = new Enemy(10, 2, 0.85, 10, 0, 4);
            newEnemy1Map2.x = 15 * gamePanel.tileSize;
            newEnemy1Map2.y = 7 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy1Map2);

            // Füge hier weitere Enemies für Map 2 hinzu

            Enemy newEnemy2Map2 = new Enemy(10, 2, 0.85, 10, 0, 3);
            newEnemy2Map2.x = 5 * gamePanel.tileSize;
            newEnemy2Map2.y = 7 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy2Map2);

            // Füge hier weitere Enemies für Map 2 hinzu

    }

    private void setEnemiesForMap1() {
        // Spezieller Enemy1 nur auf Map 1
            Enemy specialEnemy1Map1 = new Enemy(10, 2, 0.85, 10, 0, 1);
            specialEnemy1Map1.x = 3 * gamePanel.tileSize;
            specialEnemy1Map1.y = 2 * gamePanel.tileSize;
            addEnemyToCurrentMap(specialEnemy1Map1);

            // Spezieller Enemy2 nur auf Map 1
            Enemy specialEnemy2Map1 = new Enemy(10, 2, 0.85, 10, 0, 1);
            specialEnemy2Map1.x = 6 * gamePanel.tileSize;
            specialEnemy2Map1.y = 4 * gamePanel.tileSize;
            addEnemyToCurrentMap(specialEnemy2Map1);

    }

    private void setEnemiesForMap0() {
        // Beispiel: Enemy1 auf Map 0
        Enemy enemy1Map0 = new Enemy(20, 4, 0.85, 8, 0, 2);
        enemy1Map0.x = 10 * gamePanel.tileSize;
        enemy1Map0.y = 2 * gamePanel.tileSize;
        addEnemyToCurrentMap(enemy1Map0);

        // Beispiel: Enemy2 auf Map 0
        Enemy enemy2Map0 = new Enemy(20, 4, 0.85, 8, 0, 0);
        enemy2Map0.x = 10 * gamePanel.tileSize;
        enemy2Map0.y = 4 * gamePanel.tileSize;
        addEnemyToCurrentMap(enemy2Map0);
    }


    private void addEnemyToCurrentMap(Enemy enemy) {
        if (gamePanel.currentMap < gamePanel.maxMap) {
            gamePanel.getListOfEnemies().get(gamePanel.currentMap).add(enemy);
        }
    }
}
