import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Created by home on 5/9/17.
 */
public class Archer extends GameObject {

    private Image image;
    private BufferedImage sheet;
    private BufferedImage[] spritez = new BufferedImage[10];

    public Archer(String n, int h){
        setName(n);
        setHealth(h);

        //make it so that by name you can get the respective pic
        getImage();
        sprite();
    }

    private void getImage() {
        //changes depending on evoltion
        URL url = Jigglypuff.class.getResource("Images/sling.png");
        try {
            image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sprite(){
        URL url = Jigglypuff.class.getResource("Images/blueRunner.png");
        try {
            sheet = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //starting idle is 0, moving should be 1-6
//        spritez[0] = sheet.getSubimage(1, 335, 164, 172);
        spritez[0] = sheet.getSubimage(649,419,284,392);
        spritez[1] = sheet.getSubimage(679, 823,316,378);
        spritez[2] = sheet.getSubimage(1,1215,348,372);
        spritez[3] = sheet.getSubimage(319,1,342,414);
        spritez[4] = sheet.getSubimage(663,1,316,408);
        spritez[5] = sheet.getSubimage(1,823,308,390);
        spritez[6] = sheet.getSubimage(1,419,328,402);
        spritez[7] = sheet.getSubimage(311,823,366,382);
        spritez[8] = sheet.getSubimage(331,419,316,400);
        spritez[9] = sheet.getSubimage(1,1,316,416);
    }


    int count = 0;
    int walking = 5;

    public void draw(Graphics g) {
        g.drawImage(spritez[count], walking, 655, 50, 50, null);
        count++;
        walking += 15;
        if(count == 10){
            count = 0;
        }
        if(walking+30 > 800){
            walking = 5;
        }
    }
}
