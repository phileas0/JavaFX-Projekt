package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.object.*;

public class ObjectSetter {
    GamePanel gamePanel;

        public ObjectSetter(GamePanel gamePanel) {
            this.gamePanel = gamePanel;
        }
        public void setObject() {
            int mapNumber = 0;
            gamePanel.obj[mapNumber][0] = new KeyObject();
            gamePanel.obj[mapNumber][0].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][0].worldY = 6 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][2] = new KeyObject2();
            gamePanel.obj[mapNumber][2].worldX = 17 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][2].worldY = 6 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][3] = new ShelfObject();
            gamePanel.obj[mapNumber][3].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][3].worldY = 9 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][4] = new DoorObject();
            gamePanel.obj[mapNumber][4].worldX = 7 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][4].worldY = 3 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][5] = new DoorObject2();
            gamePanel.obj[mapNumber][5].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][5].worldY = 5 * gamePanel.tileSize;

        }
}
