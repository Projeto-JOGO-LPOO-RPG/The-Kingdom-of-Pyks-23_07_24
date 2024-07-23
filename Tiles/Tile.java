import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Tile {
    private Image image;
    private int type;

    public Tile(String imagePath, int type) {
        this.image = new ImageIcon(imagePath).getImage();
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public static Map<Integer, String> getTilePaths() {
        Map<Integer, String> tilePaths = new HashMap<>();
        tilePaths.put(1, "Tiles/grassTile.png");
        tilePaths.put(2, "Tiles/brickTile.png");
        tilePaths.put(3, "Tiles/waterTile.png");
        tilePaths.put(4, "Tiles/treeTile.png");
        tilePaths.put(5, "Tiles/pathTile.png");
        tilePaths.put(6, "Tiles/rockTile.png");
        tilePaths.put(7, "Tiles/flowerTile.png");

        return tilePaths;
    }
}
