import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

    private static BufferedImage spriteSheet;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        URL url = Jigglypuff.class.getResource("Images/"+ file +".png");
        try {
            sprite = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public Sprite() {
    }

    public static BufferedImage getSprite(int i, String file, String infoFileName){
        try {
            spriteSheet = loadSprite(file);
            FileReader fileR = new FileReader("/Users/home/Desktop/CompSci/aow/src/res/" + infoFileName);
            BufferedReader reader = new BufferedReader(fileR);

            int x = 0;
            int y = 0;
            int w = 0;
            int h = 0;

            String line = reader.readLine();

            while (line != null) {
                if(line.contains("<sprite")) {
                    int spriteN = Integer.parseInt(line.substring(line.indexOf("n=\"") + 3, line.indexOf(".png")));
                    if (spriteN == i+1) {

                        x = Integer.parseInt(line.substring(line.indexOf("x=\"") + 3, line.indexOf("\" y=")));
                        y = Integer.parseInt(line.substring(line.indexOf("y=\"") + 3, line.indexOf("\" w=")));
                        w = Integer.parseInt(line.substring(line.indexOf("w=\"") + 3, line.indexOf("\" h=")));
                        h = Integer.parseInt(line.substring(line.indexOf("h=\"") + 3, line.indexOf("\" pX=")));
                        return spriteSheet.getSubimage(x, y, w, h);
                    }
                }
                line = reader.readLine();

            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

}