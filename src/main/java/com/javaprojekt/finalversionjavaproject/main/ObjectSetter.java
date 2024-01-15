package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.object.DoorObject;
import com.javaprojekt.finalversionjavaproject.object.KeyObject;
import com.javaprojekt.finalversionjavaproject.object.ShelfObject;

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

            gamePanel.obj[mapNumber][1] = new KeyObject();
            gamePanel.obj[mapNumber][1].worldX = 17 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][1].worldY = 6 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][2] = new ShelfObject();
            gamePanel.obj[mapNumber][2].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][2].worldY = 9 * gamePanel.tileSize;

            gamePanel.obj[mapNumber][3] = new DoorObject();
            gamePanel.obj[mapNumber][3].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[mapNumber][3].worldY = 3 * gamePanel.tileSize;

        }
}
