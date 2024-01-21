package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Enemy;

import java.util.ArrayList;

public class EnemySetter {
    GamePanel gamePanel;
    Enemy enemy;


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
        } else if(mapNumber == 3) {
            setEnemiesForMap3();
        } else if (mapNumber == 7) {
            setEnemiesForMap6();
        } else if (mapNumber == 9) {
            setEnemiesForMap8();
        } else if (mapNumber == 10) {
            setEnemiesForMap9();
        } else if (mapNumber == 12) {
            setEnemiesForFinal();
        }

        System.out.println("Setting enemies for Map " + gamePanel.currentMap);
        // ... (dein bestehender Code)

        // Debugging-Ausgabe
        for (ArrayList<Enemy> enemies : gamePanel.getListOfEnemies()) {
            System.out.println("Number of enemies on current map: " + enemies.size());
        }
    }

    private void setEnemiesForFinal() {//setter for boss
        Enemy newEnemy1Map11 = new Enemy(10, 2, 0.85, 8, 7, 4);
        newEnemy1Map11.x = 4 * gamePanel.tileSize;
        newEnemy1Map11.y = 8 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy1Map11);
    }

    private void setEnemiesForMap9() {//setter for kitchen
        if(gamePanel.background.kitchencounter<2){
            Enemy newEnemy1Map9 = new Enemy(10, 2, 0.85, 8, 0, 2);
            newEnemy1Map9.x = 14 * gamePanel.tileSize;
            newEnemy1Map9.y = 6 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy1Map9);

            Enemy newEnemy2Map9 = new Enemy(10, 2, 0.85, 8, 0, 2);
            newEnemy2Map9.x = 4 * gamePanel.tileSize;
            newEnemy2Map9.y = 8 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy2Map9);
        }
    }

    private void setEnemiesForMap8() {//setter for meetingroom
        if(gamePanel.background.meetingcounter<2){
            Enemy newEnemy1Map8 = new Enemy(10, 2, 0.85, 8, 0, 2);
            newEnemy1Map8.x = 16 * gamePanel.tileSize;
            newEnemy1Map8.y = 9 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy1Map8);

            Enemy newEnemy2Map8 = new Enemy(10, 2, 0.85, 8, 0, 2);
            newEnemy2Map8.x = 2 * gamePanel.tileSize;
            newEnemy2Map8.y = 8 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy2Map8);
        }


    }
    private void setEnemiesForMap6() {//setter for lobby entrance
        Enemy newEnemy1Map6 = new Enemy(10, 2, 0.85, 8, 0, 2);
        newEnemy1Map6.x = 12 * gamePanel.tileSize;
        newEnemy1Map6.y = 6 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy1Map6);

        Enemy newEnemy2Map6 = new Enemy(10, 2, 0.85, 8, 0, 2);
        newEnemy2Map6.x = 7 * gamePanel.tileSize;
        newEnemy2Map6.y = 6 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy2Map6);
    }

    private void setEnemiesForMap3() {//setter for weapons room
        Enemy newEnemy1Map3 = new Enemy(10, 2, 0.85, 8, 0, 2);
        newEnemy1Map3.x = 12 * gamePanel.tileSize;
        newEnemy1Map3.y = 8 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy1Map3);

        // Füge hier weitere Enemies für Map 3 hinzu

        Enemy newEnemy2Map3 = new Enemy(10, 2, 0.85, 8, 0, 2);
        newEnemy2Map3.x = 3 * gamePanel.tileSize;
        newEnemy2Map3.y = 10 * gamePanel.tileSize;
        addEnemyToCurrentMap(newEnemy2Map3);

        // Füge hier weitere Enemies für Map 3 hinzu
    }

    private void setEnemiesForMap2() {// setter for weapons room entrance
            Enemy newEnemy1Map2 = new Enemy(10, 2, 0.85, 10, 0, 4);
            newEnemy1Map2.x = 12 * gamePanel.tileSize;
            newEnemy1Map2.y = 6 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy1Map2);

            // Füge hier weitere Enemies für Map 2 hinzu

            Enemy newEnemy2Map2 = new Enemy(10, 2, 0.85, 10, 0, 3);
            newEnemy2Map2.x = 7 * gamePanel.tileSize;
            newEnemy2Map2.y = 6 * gamePanel.tileSize;
            addEnemyToCurrentMap(newEnemy2Map2);

            // Füge hier weitere Enemies für Map 2 hinzu

    }

    private void setEnemiesForMap1() {//setter for crossroad
        // Spezieller Enemy1 nur auf Map 1
            Enemy specialEnemy1Map1 = new Enemy(10, 2, 0.85, 10, 3, 2);
            specialEnemy1Map1.x = 11 * gamePanel.tileSize;
            specialEnemy1Map1.y = 4 * gamePanel.tileSize;
            addEnemyToCurrentMap(specialEnemy1Map1);

            // Spezieller Enemy2 nur auf Map 1
            Enemy specialEnemy2Map1 = new Enemy(10, 2, 0.85, 10, 3, 2);
            specialEnemy2Map1.x = 7 * gamePanel.tileSize;
            specialEnemy2Map1.y = 4 * gamePanel.tileSize;
            addEnemyToCurrentMap(specialEnemy2Map1);

    }

    private void setEnemiesForMap0() {//setter für starting map
        // Beispiel: Enemy1 auf Map 0
        Enemy enemy1Map0 = new Enemy(20, 4, 0.85, 8, 0, 0);
        enemy1Map0.x = 10 * gamePanel.tileSize -32;
        enemy1Map0.y = 3 * gamePanel.tileSize;
        addEnemyToCurrentMap(enemy1Map0);
    }
    public boolean areAllEnemiesOffMap() {//checks if all enemies are off map
        for (ArrayList<Enemy> enemies : gamePanel.getListOfEnemies()) {
            for (Enemy enemy : enemies) {
                if (enemy.isOnMap()) {
                    return false;
                }
            }
        }
        return true;
    }


    private void addEnemyToCurrentMap(Enemy enemy) {//adds enemy to current map
        if (gamePanel.currentMap < gamePanel.maxMap) {
            gamePanel.getListOfEnemies().get(gamePanel.currentMap).add(enemy);
        }
    }
}
