package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Entity;

public class Map8Switcher {
    private GamePanel gamePanel;

    public Map8Switcher(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTileForMapSwitch(Entity entity) {
        if (gamePanel.currentMap != 7) {
            return; // Only perform checks on map 8
        }

        int playerTopRow = (entity.y + entity.solidAreaDefaultY) / gamePanel.tileSize;
        int playerLeftCol = (entity.x + entity.solidAreaDefaultX) / gamePanel.tileSize;
        int tileNum = gamePanel.tileManager.mapTileNumber[gamePanel.currentMap][playerLeftCol][playerTopRow];

        switch (tileNum) {
            case 3: // Tile 3 brings you to map 11
                gamePanel.background.switchLevel(9);
                break;
            case 4: // Tile 4 brings you to map 9
                gamePanel.background.switchLevel(7);
                break;
            case 5: // Tile 5 brings you to map 10
                gamePanel.background.switchLevel(8);
                break;
        }
    }
}