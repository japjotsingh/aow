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
public class Jigglypuff extends GameObject {

    private int panelWidth;
    private int frameDelay = 8;
    private boolean walkMode = true;
    private boolean walks = true;
    Timer t;

    private BufferedImage[] walk;
    private BufferedImage[] attack;

    private Animation walkAnimation;

    private Animation attackAnimation;

    public void setPanelWidth(int p){
        panelWidth = p;
    }


    public Jigglypuff(String n, int h, int p){
        panelWidth = p;
        setName(n);
        setHealth(h);

        attack= new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            attack[i] = Sprite.getSprite(i, "jigAtk", "jigAtk.txt");
        }
        attackAnimation = new Animation(attack, frameDelay+4);

        walk = new BufferedImage[7];
        for (int i = 0; i < 7; i++) {
            walk[i] = Sprite.getSprite(i, "jwalk", "jwalk.txt");
        }
        walkAnimation = new Animation(walk, frameDelay);

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(walks) {
                    walkAnimation.update();
                }
                else{
                    attackAnimation.update();
                }
            }
        });

        t.start();
    }

    int walking = 0;

    public void draw(Graphics g) {
        if(walkMode) {
            g.drawImage(walkAnimation.getSprite(), walking, 655, 50, 50, null);
            walking += 3;

            if (walking + 90 > panelWidth) {
                walkMode = false;
            }
        }

        else{
            walks = false;
            g.drawImage(attackAnimation.getSprite(), walking, 651, 50, 50, null);
        }
    }

}
