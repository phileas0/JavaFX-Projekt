package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Entity;

public class CollisionDetection {
    GamePanel gamePanel;
    public CollisionDetection(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.x + entity.solid.x;
        int entityRightWorldX = entity.x + entity.solid.x + entity.solid.width;
        int entityTopWorldY = entity.y + entity.solid.y;
        int entityBottomWorldY = entity.y + entity.solid.y + entity.solid.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collision = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collision = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNumber[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collision = true;
                }
                break;

        }
    }

    public int checkObject(Entity entity, boolean p){
        int index = 999;

        for (int i = 0; i < gamePanel.obj.length; i++) {
            if(gamePanel.obj[i]!= null){
                //get entitys solid area position
                entity.solid.x = entity.x + entity.solidAreaDefaultX;
                entity.solid.y = entity.y + entity.solidAreaDefaultY;
                //get objects solid area position
                gamePanel.obj[i].solid.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solid.x;
                gamePanel.obj[i].solid.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solid.y;

                switch(entity.direction){
                    case "up":
                        entity.solid.y -= entity.speed;
                        if(entity.solid.intersects(gamePanel.obj[i].solid)){
                            if(gamePanel.obj[i].collision){
                                entity.collision = true;
                            }
                            if(p){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solid.y += entity.speed;
                        if(entity.solid.intersects(gamePanel.obj[i].solid)){
                            if(gamePanel.obj[i].collision){
                                entity.collision = true;
                            }
                            if(p){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solid.x -= entity.speed;
                        if(entity.solid.intersects(gamePanel.obj[i].solid)){
                            if(gamePanel.obj[i].collision){
                                entity.collision = true;
                            }
                            if(p){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solid.x += entity.speed;
                        if(entity.solid.intersects(gamePanel.obj[i].solid)){
                            if(gamePanel.obj[i].collision){
                                entity.collision = true;
                            }
                            if(p){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solid.x = entity.solidAreaDefaultX;
                entity.solid.y = entity.solidAreaDefaultY;
                gamePanel.obj[i].solid.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solid.y = gamePanel.obj[i].solidAreaDefaultY;
            }

        }
            return index;
    }
}
