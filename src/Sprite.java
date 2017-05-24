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

    public static void getSprite(int i, String file, String infoFileName) {

        int x,y,w,h;

        x = 2;
        y = 2;
        w = 2;
        h = 2;


        spriteSheet = loadSprite(file);

        String line = null;
        try{
            FileReader reader = new FileReader(infoFileName);
            BufferedReader breader = new BufferedReader(reader);

            while((line = breader.readLine()) != null){
                if(line.equalsIgnoreCase("<sprite n=" + i)){
                    System.out.println(line);
                }
            }

            breader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return spriteSheet.getSubimage(x,y,w,h);
    }

}