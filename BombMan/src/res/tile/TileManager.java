package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int MapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        MapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/world_test.txt");
    }

    public void getTileImage() {

        for (int i = 0; i <= 4; ++i) { // Number of objects
            tile[i] = new Tile();
        }
        // tile[0].image = ImageIO.read(getClass().getResourceAsStream("grass01.png"));
        setup(0, "grass01", false);
        // tile[1].image = ImageIO.read(getClass().getResourceAsStream("steel.png")); //
        setup(1, "steel", true);

        // tile[2].image = ImageIO.read(getClass().getResourceAsStream("brick.png")); //
        setup(2, "brick", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tile/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void loadMap(String map_path) {
        try {
            InputStream is = getClass().getResourceAsStream(map_path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    MapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = MapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // STOP moving the camera at the edge
            // if (gp.player.screenX > gp.player.worldX) {
            // screenX = worldX;
            // }
            // if (gp.player.screenY > gp.player.worldY) {
            // screenY = worldY;
            // }
            // int RightOffset = gp.screenWidth - gp.player.screenX;
            // if (RightOffset > gp.worldWidth - gp.player.worldX) {
            // screenX = gp.screenWidth - (gp.worldWidth - worldX);
            // }
            // int BottomOffset = gp.screenHeight - gp.player.screenY;
            // if (BottomOffset > gp.worldHeight - gp.player.worldY) {
            // screenY = gp.screenHeight - (gp.worldHeight - worldY);
            // }

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            // } else {
            // if (gp.player.screenX > gp.player.worldX || gp.player.screenX >
            // gp.player.worldY
            // || RightOffset > gp.worldWidth - gp.player.worldX
            // || BottomOffset > gp.worldHeight - gp.player.worldY) {
            // g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,
            // null);
            // }
            // }
            // ========================================================================

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
