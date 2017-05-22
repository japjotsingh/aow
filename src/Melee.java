import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Melee extends GameObject {

    private Image image;
    private BufferedImage sheet;
    private BufferedImage[] spritez = new BufferedImage[7];


    public Melee(String n, int h) {
        setName(n);
        setHealth(h);

        //make it so that by name you can get the respective pic
        getImage();
        sprite();
    }

    private void getImage() {
        //different depending on which evolution you are on
        URL url = Melee.class.getResource("Images/club.png");
        try {
            image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sprite(){
        URL url = Melee.class.getResource("Images/jwalk.png");
        try {
            sheet = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        spritez[0] = sheet.getSubimage(1, 335, 164, 172);
        spritez[1] = sheet.getSubimage(1, 163, 148, 160);
        spritez[2] = sheet.getSubimage(1, 1, 150, 158);
        spritez[3] = sheet.getSubimage(151, 163, 152, 168);
        spritez[4] = sheet.getSubimage(305, 163, 156, 170);
        spritez[5] = sheet.getSubimage(311, 1, 152, 160);
        spritez[6] = sheet.getSubimage(153, 1, 156, 158);
    }




    public void draw(Graphics g) {

        g.drawImage(spritez[(int)(Math.random()*7)], 100, 500, 100, 100, null);
    }

}
