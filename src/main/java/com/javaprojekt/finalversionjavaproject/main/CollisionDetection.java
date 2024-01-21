package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.combat.TextField;
import com.javaprojekt.finalversionjavaproject.entity.Entity;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import org.w3c.dom.Text;

import java.awt.*;

public class CollisionDetection {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    EnemySetter enemySetter;
    TextField textField;
    private Map8Switcher map8Switcher;
    Rectangle rect;
    Player player;

    public CollisionDetection(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.keyHandler = gamePanel.keyHandler;
        this.enemySetter = new EnemySetter(gamePanel);
        this.map8Switcher = new Map8Switcher(gamePanel);
        this.textField = gamePanel.textField;
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
                entity.collision = false; // Reset collision flag
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                //if(enemySetter.areAllEnemiesOffMap()){
                    checkTile3(entity);
                //}
                //else if(tileNum1 == 3 || tileNum2 == 3 && enemySetter.areAllEnemiesOffMap()){
                //    if (!textField.isDisplayingMessages()){
                //        textField.addMessage("You can't leave the map while enemies are still on it!");
                //    }
                //}
                break;
            case "down":
                entity.collision = false; // Reset collision flag
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                //if(enemySetter.areAllEnemiesOffMap()){
                  checkTile3(entity);
                //}
                //else if(tileNum1 == 3 || tileNum2 == 3&& enemySetter.areAllEnemiesOffMap()){
                //    if (!textField.isDisplayingMessages()){
                //        textField.addMessage("You can't leave the map while enemies are still on it!");
                //    }                }
                break;
            case "left":
                entity.collision = false; // Reset collision flag
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                //if(enemySetter.areAllEnemiesOffMap()){
                    checkTile3(entity);
                //}
                //else if(tileNum1 == 3 || tileNum2 == 3&& enemySetter.areAllEnemiesOffMap()){
                //if (!textField.isDisplayingMessages()){
                //    textField.addMessage("You can't leave the map while enemies are still on it!");
                //}                }
                break;
            case "right":
                entity.collision = false; // Reset collision flag
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collision = true;
                }
                //if(enemySetter.areAllEnemiesOffMap()){
                    checkTile3(entity);
                //}
                //else if(tileNum1 == 3 || tileNum2 == 3 && enemySetter.areAllEnemiesOffMap()){
                //if (!textField.isDisplayingMessages()){
                // textField.addMessage("You can't leave the map while enemies are still on it!");
                //}                }

                break;
        }

    }



    public void checkTile3(Entity entity){//check if player is on a tile[3] (red tile)
        //if(keyHandler.interacted) {
            int playerTopRow = (entity.y + entity.solidAreaDefaultY) / gamePanel.tileSize;
            int playerLeftCol = (entity.x + entity.solidAreaDefaultX) / gamePanel.tileSize;
            int tileNum = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerLeftCol][playerTopRow];
            int playerRightCol = (entity.x + entity.solidAreaDefaultX + entity.solid.width) / gamePanel.tileSize;


            // make a switch with argument gamepanel.currentmap, and in each case check if the player is in a tile[3] (red tile)
            switch (gamePanel.currentMap) {
                case 0:
                    if (tileNum== 3) {
                        // Vor dem Kartenwechsel die Feinde auf der aktuellen Karte entfernen
                        gamePanel.getListOfEnemies().get(0).clear();
                        gamePanel.background.switchLevel(0);
                        //enemySetter.setEnemies(1);
                        entity.y = 640;

                    } break;

                case 1:
                    if (tileNum== 3) {
                        gamePanel.getListOfEnemies().get(1).clear();
                        gamePanel.background.switchLevel(1);
                        entity.x = 620;
                        entity.y = 645;
                    } break;
                case 2:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(2).clear();
                        gamePanel.background.switchLevel(2);
                        entity.x = 1150;
                        entity.y = 550;
                    } break;
                case 3:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(3).clear();
                        gamePanel.background.switchLevel(3);
                        entity.x = 1150;
                        entity.y = 550;
                    } break;
                case 4:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(4).clear();
                        gamePanel.background.switchLevel(4);
                        entity.x = 50;
                        entity.y = 550;
                    } break;
                case 5:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(5).clear();
                        gamePanel.background.switchLevel(5);
                        entity.x = 600;
                        entity.y = 645;
                    } break;
                case 6:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(6).clear();
                        gamePanel.background.switchLevel(6);
                        entity.x = 615;
                        entity.y = 645;
                    } break;
                case 7:
                    if (tileNum == 3) {
                        map8Switcher.checkTileForMapSwitch(entity);
                        entity.x = 600;
                        entity.y = 645;
                    } else if (tileNum == 4) {
                        map8Switcher.checkTileForMapSwitch(entity);
                        entity.x = 600;
                        entity.y = 645;
                    } else if (tileNum == 5) {
                        map8Switcher.checkTileForMapSwitch(entity);
                        entity.x = 600;
                        entity.y = 645;

                    }break;
                case 8:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(8).clear();
                        gamePanel.background.switchLevel(6);
                        entity.x = 600;
                        entity.y = 645;
                    } break;
                case 9:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(9).clear();
                        gamePanel.background.switchLevel(6);
                        entity.x = 600;
                        entity.y = 640;
                    } break;
                case 10:
                    if (tileNum == 3) {
                        gamePanel.getListOfEnemies().get(10).clear();
                        gamePanel.background.switchLevel(10);
                        entity.x = 600;
                        entity.y = 640;
                    } break;
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
