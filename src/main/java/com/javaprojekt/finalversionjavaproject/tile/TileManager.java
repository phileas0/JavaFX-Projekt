package com.javaprojekt.finalversionjavaproject.tile;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    private GamePanel gamePanel;
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
    public String meetingRoom = "/res/maps/meetingRoom.txt";
    public String kitchen = "/res/maps/kitchen.txt";
    public String lobby2 = "/res/maps/lobby2.txt";
    public String finalBossRoom = "/res/maps/finalBossRoom.txt";
    public String weaponFactory2 = "/res/maps/weaponFactory2.txt";

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNumber = new int[gamePanel.maxMap][gamePanel.maxScreenCol][gamePanel.maxScreenRow];


        getTileImages();
        loadMap(startingMap, 0);
        loadMap(startingMap1, 1);
        loadMap(weaponEntrance, 2);
        loadMap(weaponFactory, 3);
        loadMap(weaponFactory2, 4);
        loadMap(weaponOffice, 5);
        loadMap(street, 6);
        loadMap(hqFront, 7);
        loadMap(lobby, 8);
        loadMap(meetingRoom, 9);
        loadMap(kitchen, 10);
        loadMap(lobby2, 11);
        loadMap(finalBossRoom, 12);

    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/go.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/black.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/gruen.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/red.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/blue.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tiles/yellow.png")));

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