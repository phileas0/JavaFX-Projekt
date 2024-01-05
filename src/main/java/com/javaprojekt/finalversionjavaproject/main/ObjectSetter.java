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
            gamePanel.obj[0] = new KeyObject();
            gamePanel.obj[0].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[0].worldY = 6 * gamePanel.tileSize;

            gamePanel.obj[1] = new KeyObject();
            gamePanel.obj[1].worldX = 19 * gamePanel.tileSize;
            gamePanel.obj[1].worldY = 6 * gamePanel.tileSize;

            gamePanel.obj[2] = new ShelfObject();
            gamePanel.obj[2].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[2].worldY = 9 * gamePanel.tileSize;

            gamePanel.obj[3] = new DoorObject();
            gamePanel.obj[3].worldX = 10 * gamePanel.tileSize;
            gamePanel.obj[3].worldY = 3 * gamePanel.tileSize;




        }
}
