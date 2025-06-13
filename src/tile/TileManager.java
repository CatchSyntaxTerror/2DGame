package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNumber;

    /**
     * This constructor sets the gamePanel
     * and sets the Array that holds all the different tiles we have
     * @param gp the game panel
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    /**
     * this method is used to load maps whoch are represented as a 16x12 grid of numbers
     */
    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while(col < gp. maxScreenCol && row < gp. maxScreenRow){
                String line = br.readLine();
                while(col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * this method instantiates the tiles as well as sets their images
     */
    private void getTileImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
            tile[1] =  new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall.png"));
            tile[2] =  new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this draws the tiles
     * @param g2d graphics passed grom GamePanel
     */
    public void draw(Graphics2D g2d){
        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol &&  row < gp.maxScreenRow){
            int tileNum = mapTileNumber[col][row];
            g2d.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
