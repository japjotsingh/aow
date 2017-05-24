import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
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

    protected Rectangle2D bounds = new Rectangle2D.Double();

    private int panelWidth;
    private int frameDelay = 8;
    private boolean walkMode = true;
    private boolean walks = true;
    private boolean idleSta = false;
    Timer t;

    private BufferedImage[] walk;
    private BufferedImage[] attack;
    private BufferedImage[] idle;


    private Animation walkAnimation;
    private Animation attackAnimation;
    private Animation idleAnimation;

    public void setPanelWidth(int p){
        panelWidth = p;
    }

    public Jigglypuff(String n, int h, int p, boolean idleSta){
        bounds.setRect(0,651,50,50);
        panelWidth = p;
        setName(n);
        setHealth(h);
        this.idleSta = idleSta;

        attack= new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            attack[i] = Sprite.getSprite(i, "jatk", "jatk.txt");
        }
        attackAnimation = new Animation(attack, frameDelay+4);

        walk = new BufferedImage[7];
        for (int i = 0; i < 7; i++) {
            walk[i] = Sprite.getSprite(i, "jwalk", "jwalk.txt");
        }
        walkAnimation = new Animation(walk, frameDelay);

        this.idle = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            this.idle[i] = Sprite.getSprite(i, "jidle", "jidle.txt");
        }
        idleAnimation = new Animation(idle, frameDelay+10);

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idleSta){
                    idleAnimation.update();
                }
                else {
                    // depends on hitbox
                    if (walks) {
                        walkAnimation.update();
                    } else {
                        attackAnimation.update();
                    }
                }
            }
        });

        t.start();
    }

    int walking = 0;

    public boolean interesecting(int x, int y){
        return bounds.intersects(x,y,1,1);
    }

    public boolean isIdle(){
        return idleSta;
    }

    public void draw(Graphics g) {

        if(idleSta){
            g.drawImage(idleAnimation.getSprite(), panelWidth-300, 50, 30,30, null);
            bounds.setRect(panelWidth-300, 50, 30,30);
        }

        else {
            //modify so that i tonly goes into attack mode once objects hit box collides with another object
            if (walkMode) {
                g.drawImage(walkAnimation.getSprite(), walking, 651, 50, 50, null);
                walking += 3;
                bounds.setRect(walking,651,50,50);

                if (walking + 90 > panelWidth) {
                    walkMode = false;
                }
            } else {
                walks = false;
                g.drawImage(attackAnimation.getSprite(), walking, 651, 50, 50, null);
                bounds.setRect(walking,651,50,50);

            }
        }
    }
}
