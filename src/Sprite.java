import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 24;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        URL url = Melee.class.getResource("Images/" + file + ".png");
        try {
            sprite = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("kirbysprite");
        }

        return spriteSheet.getSubimage((xGrid * TILE_SIZE)+7, (yGrid * TILE_SIZE)+5, TILE_SIZE, TILE_SIZE);
    }

}