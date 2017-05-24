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
    private int frameDelay = 8;
    private boolean attackMode = false;
    Timer t;

    private BufferedImage[] walkingJig  = {Sprite.getSprite(1, 163, 148, 160, "jwalk"),
                                            Sprite.getSprite(1, 1, 150, 158, "jwalk"),
                                            Sprite.getSprite(151, 163, 152, 168, "jwalk"),
                                            Sprite.getSprite(305, 163, 156, 170, "jwalk"),
                                            Sprite.getSprite(311, 1, 152, 160, "jwalk"),
                                            Sprite.getSprite(153, 1, 156, 158, "jwalk")};

    private BufferedImage[] jigAttack = {Sprite.getSprite(1,1,117,132, "jigatk"),
            Sprite.getSprite(120,1,110,112, "jigatk"),
            Sprite.getSprite(232,1,121,123, "jigatk"),
            Sprite.getSprite(355,1,120,123, "jigatk"),
            Sprite.getSprite(1,135,112,125, "jigatk"),
            Sprite.getSprite(115,135,141,128, "jigatk"),
            Sprite.getSprite(258,135,119,127, "jigatk"),
            Sprite.getSprite(379,135,128,123, "jigatk")};


    private Animation walkingJiggly = new Animation(walkingJig, frameDelay);
    private Animation walkAnimation = walkingJiggly;

    private Animation attackingJiggly = new Animation(jigAttack, frameDelay);
    private Animation attackAnimation = attackingJiggly;

    public void setPanelWidth(int p){
        panelWidth = p;
    }


    public Melee(String n, int h, int p) {
        panelWidth = p;
        setName(n);
        setHealth(h);

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                walkAnimation.update();
            }
        });

        t.start();
    }

    int walking = 0;

    public void draw(Graphics g) {

//        if(attackMode) {
//            g.drawImage(attackAnimation.getSprite(), 10, 655, 50, 50, null);
//            System.out.println("attk");
//        }
        g.drawImage(walkAnimation.getSprite(), walking, 655, 50, 50, null);
        walking += 3;

        if (walking + 27 > panelWidth) {
            System.out.println("atattack!");
        }

    }

}
