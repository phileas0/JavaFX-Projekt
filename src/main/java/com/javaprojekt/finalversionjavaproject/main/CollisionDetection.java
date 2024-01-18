package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Entity;
import com.javaprojekt.finalversionjavaproject.entity.Player;

import java.awt.*;

public class CollisionDetection {
    GamePanel gamePanel;
    Rectangle rect;
    Player player;

    public CollisionDetection(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.x + entity.solid.x;
        int entityRightWorldX = entity.x + entity.solid.x + entity.solid.width;
        int entityTopWorldY = entity.y + entity.solid.y;
        int entityBottomWorldY = entity.y + entity.solid.y + entity.solid.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                break;

        }

        int playerTopRow = (entity.y + entity.solidAreaDefaultY) / gamePanel.tileSize;
        int playerLeftCol = (entity.x + entity.solidAreaDefaultX) / gamePanel.tileSize;
        int playerRightCol = (entity.x + entity.solidAreaDefaultX + entity.solid.width) / gamePanel.tileSize;

        // Spieler kollidiert mit den obersten zwei Reihen der Karte
        if (gamePanel.currentMap == 0) {
            if ((playerTopRow == 0 || playerTopRow == 1) ||
                    (gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerLeftCol][playerTopRow] == 1 ||
                            gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerRightCol][playerTopRow] == 1 ||
                            gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerLeftCol][playerTopRow + 1] == 1 ||
                            gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerRightCol][playerTopRow + 1] == 1)) {
                gamePanel.background.switchLevel();
                entity.y = 660;
            }
        }
        else if (gamePanel.currentMap == 1) {
            // Überprüfen, ob der Spieler die äußerste rechte Spalte der Karte berührt, wenn currentMap == 1
            if (gamePanel.currentMap == 1 &&
                    (playerRightCol == gamePanel.maxScreenCol - 1 ||
                            playerRightCol + 1 == gamePanel.maxScreenCol - 1)) {
                // Hier können Sie den Code hinzufügen, der ausgeführt werden soll, wenn die Bedingung erfüllt ist
                // Zum Beispiel: gamePanel.background.switchLevel();
                gamePanel.background.switchLevel();
                entity.x = 64;
                entity.y = 360;
            }


        }


    }

    public int checkObject(Entity entity, boolean p) {
        int index = 999;

        for (int i = 0; i < gamePanel.obj[1].length; i++) {
            if (gamePanel.obj[gamePanel.currentMap][i] != null) {
                //get entitys solid area position
                entity.solid.x = entity.x + entity.solidAreaDefaultX;
                entity.solid.y = entity.y + entity.solidAreaDefaultY;
                //get objects solid area position
                gamePanel.obj[gamePanel.currentMap][i].solid.x = gamePanel.obj[gamePanel.currentMap][i].worldX + gamePanel.obj[gamePanel.currentMap][i].solid.x;
                gamePanel.obj[gamePanel.currentMap][i].solid.y = gamePanel.obj[gamePanel.currentMap][i].worldY + gamePanel.obj[gamePanel.currentMap][i].solid.y;

                switch (entity.direction) {
                    case "up":
                        entity.solid.y -= entity.speed;
                        if (entity.solid.intersects(gamePanel.obj[gamePanel.currentMap][i].solid)) {
                            if (gamePanel.obj[gamePanel.currentMap][i].collision) {
                                entity.collision = true;
                            }
                            if (p) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solid.y += entity.speed;
                        if (entity.solid.intersects(gamePanel.obj[gamePanel.currentMap][i].solid)) {
                            if (gamePanel.obj[gamePanel.currentMap][i].collision) {
                                entity.collision = true;
                            }
                            if (p) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solid.x -= entity.speed;
                        if (entity.solid.intersects(gamePanel.obj[gamePanel.currentMap][i].solid)) {
                            if (gamePanel.obj[gamePanel.currentMap][i].collision) {
                                entity.collision = true;
                            }
                            if (p) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solid.x += entity.speed;
                        if (entity.solid.intersects(gamePanel.obj[gamePanel.currentMap][i].solid)) {
                            if (gamePanel.obj[gamePanel.currentMap][i].collision) {
                                entity.collision = true;
                            }
                            if (p) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.solid.x = entity.solidAreaDefaultX;
                entity.solid.y = entity.solidAreaDefaultY;
                gamePanel.obj[gamePanel.currentMap][i].solid.x = gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultX;
                gamePanel.obj[gamePanel.currentMap][i].solid.y = gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultY;
            }
        }


        return index;

    }
}
