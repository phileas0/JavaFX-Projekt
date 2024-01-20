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
    public int mapTileNumber[][][];

    public String startingMap = "/res/maps/Starting0.txt";
    public String startingMap1 = "/res/maps/Starting1.txt";
    public String weaponEntrance = "/res/maps/weaponEntrance.txt";
    public String weaponFactory = "/res/maps/weaponFactory.txt";
    public String weaponOffice = "/res/maps/weaponOffice.txt";
    public String street = "/res/maps/street.txt";
    public String hqFront = "/res/maps/hqFront.txt";
    public String lobby = "/res/maps/lobby.txt";

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxMap][gamePanel.maxScreenCol][gamePanel.maxScreenRow];


        getTileImages();
        loadMap(startingMap, 0);
        loadMap(startingMap1, 1);
        loadMap(weaponEntrance, 2);
        loadMap(weaponFactory, 3);
        loadMap(weaponOffice, 4);
        loadMap(street, 5);
        loadMap(hqFront, 6);
        loadMap(lobby, 7);
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/go.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/black.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/gruen.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/red.png"));

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

            int tileNum = mapTileNumber[gamePanel.currentMap][col][row];

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


    public void loadMap(String map, int mapNumber){
        try {
            InputStream stream = getClass().getResourceAsStream(map);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){
                String line = reader.readLine();

                while(col < gamePanel.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[mapNumber][col][row] = num;
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