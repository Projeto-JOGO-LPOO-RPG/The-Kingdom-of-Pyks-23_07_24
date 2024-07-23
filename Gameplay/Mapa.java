import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Mapa {
    private int[][] mapArray;
    private Map<Integer, Tile> tileMap;

    public Mapa(int[][] mapArray) {
        this.mapArray = mapArray;
        this.tileMap = new HashMap<>();
        loadTiles();
    }

    private void loadTiles() {
        Map<Integer, String> tilePaths = Tile.getTilePaths();
        for (Map.Entry<Integer, String> entry : tilePaths.entrySet()) {
            tileMap.put(entry.getKey(), new Tile(entry.getValue(), entry.getKey()));
        }
    }

    public void drawMap(Graphics2D g2, int cameraX, int cameraY) {
        int tileSize =48;
        for (int row = 0; row < mapArray.length; row++) {
            for (int col = 0; col < mapArray[row].length; col++) {
                int tileType = mapArray[row][col];
                Tile tile = tileMap.get(tileType);
                if (tile != null) {
                    Image tileImage = tile.getImage();
                    int x = col * tileSize - cameraX;
                    int y = row * tileSize - cameraY;
                    g2.drawImage(tileImage, x, y, tileSize, tileSize, null);
                }
            }
        }
    }

    public int getRows() {
        return mapArray.length;
    }

    public int getCols() {
        return mapArray[0].length;
    }
}


               


