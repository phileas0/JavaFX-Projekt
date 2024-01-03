package com.javaprojekt.finalversionjavaproject.tile;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNumber[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];


        getTileImages();
        loadMap();
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/go.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/black.png"));
            tile[1].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenCol&& row < gamePanel.maxScreenRow){

            int tileNum = mapTileNumber[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;

            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenCol){
                col = 0;
                row++;
                x = 0;
                y += gamePanel.tileSize;

            }
        }

    }

    public void loadMap(){
        try {
            InputStream stream = getClass().getResourceAsStream("/res/maps/Starting.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
                String line = reader.readLine();

                while(col < gamePanel.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxScreenCol){
                    col = 0;
                    row++;
                }

            }reader.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}