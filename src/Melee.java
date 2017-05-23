import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Melee extends GameObject {

    private int panelWidth;

//    private Image image;
//    private BufferedImage sheet;
//    private BufferedImage[] spritez = new BufferedImage[7];
    private int frameDelay = 8;
    private boolean attackMode = false;
    Timer t;


    private BufferedImage[] jigAttack = {Sprite.getSprite(1,1,117,132, "jigatk"),
            Sprite.getSprite(120,1,110,112, "jigatk"),
            Sprite.getSprite(232,1,121,123, "jigatk"),
            Sprite.getSprite(355,1,120,123, "jigatk"),
            Sprite.getSprite(1,135,112,125, "jigatk"),
            Sprite.getSprite(115,135,141,128, "jigatk"),
            Sprite.getSprite(258,135,119,127, "jigatk"),
            Sprite.getSprite(379,135,128,123, "jigatk")};

    private BufferedImage[] walkingJig  = {Sprite.getSprite(1, 163, 148, 160, "jwalk"),
                                            Sprite.getSprite(1, 1, 150, 158, "jwalk"),
                                            Sprite.getSprite(151, 163, 152, 168, "jwalk"),
                                            Sprite.getSprite(305, 163, 156, 170, "jwalk"),
                                            Sprite.getSprite(311, 1, 152, 160, "jwalk"),
                                            Sprite.getSprite(153, 1, 156, 158, "jwalk")};


    private Animation walkingJiggly = new Animation(walkingJig, frameDelay);
    private Animation animation = walkingJiggly;

    private Animation attackingJiggly = new Animation(jigAttack, frameDelay);
    private Animation attackAnimation = attackingJiggly;

    public void setPanelWidth(int p){
        panelWidth = p;
    }


    public Melee(String n, int h, int p) {
        panelWidth = p;
        setName(n);
        setHealth(h);

        //make it so that by name you can get the respective pic
//        getImage();
//        sprite();

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if(!attackMode) {
//                    animation.update();
//                }
//                if (attackMode) {
//                    attackAnimation.update();
//                }
            }
        });

        t.start();
    }

//    private void getImage() {
//        //different depending on which evolution you are on
//        URL url = Melee.class.getResource("Images/club.png");
//        try {
//            image = ImageIO.read(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    private void sprite(){
//        URL url = Melee.class.getResource("Images/jwalk.png");
//        try {
//            sheet = ImageIO.read(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //starting idle is 0, moving should be 1-6
////        spritez[0] = sheet.getSubimage(1, 335, 164, 172);
//
//        spritez[1] = sheet.getSubimage(1, 163, 148, 160);
//        spritez[2] = sheet.getSubimage(1, 1, 150, 158);
//        spritez[3] = sheet.getSubimage(151, 163, 152, 168);
//        spritez[4] = sheet.getSubimage(305, 163, 156, 170);
//        spritez[5] = sheet.getSubimage(311, 1, 152, 160);
//        spritez[6] = sheet.getSubimage(153, 1, 156, 158);
//    }


    int walking = 0;

    public void draw(Graphics g) {

//        if(attackMode) {
//            g.drawImage(attackAnimation.getSprite(), 10, 655, 50, 50, null);
//            System.out.println("attk");
//        }

        g.drawImage(animation.getSprite(), walking, 655, 50, 50, null);
        walking += 3;

        if (walking + 27 > panelWidth) {
            attackMode = true;
        }

    }

}
