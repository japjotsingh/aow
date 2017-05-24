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

    private BufferedImage[] walkingJig  = {Sprite.getSprite(1, 163, 148, 160, "jwalk"),
                                            Sprite.getSprite(1, 1, 150, 158, "jwalk"),
                                            Sprite.getSprite(151, 163, 152, 168, "jwalk"),
                                            Sprite.getSprite(305, 163, 156, 170, "jwalk"),
                                            Sprite.getSprite(311, 1, 152, 160, "jwalk"),
                                            Sprite.getSprite(153, 1, 156, 158, "jwalk")};

    private BufferedImage[] jigAttack = {Sprite.getSprite(1,1,192,186, "jigAtk"),
                                        Sprite.getSprite(195,1,169,175, "jigAtk"),
                                        Sprite.getSprite(1,189,196,180, "jigAtk"),
                                        Sprite.getSprite(199,189,192,174, "jigAtk"),
                                        Sprite.getSprite(1,371,204, 175, "jigAtk"),
                                        Sprite.getSprite(207,371,186,178, "jigAtk"),
                                        Sprite.getSprite(1,551,238,179, "jigAtk"),
                                        Sprite.getSprite(241,551,175,183, "jigAtk")};


    private Animation walkingJiggly = new Animation(walkingJig, frameDelay);
    private Animation walkAnimation = walkingJiggly;

    private Animation attackingJiggly = new Animation(jigAttack, frameDelay+4);
    private Animation attackAnimation = attackingJiggly;

    public void setPanelWidth(int p){
        panelWidth = p;
    }


    public Jigglypuff(String n, int h, int p) {
        panelWidth = p;
        setName(n);
        setHealth(h);

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
