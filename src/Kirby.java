import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Kirby extends GameObject {

    Timer t;

    public Kirby(int h, int p, boolean idleSta, boolean isButton, boolean facingRight){

        this.facingRight = facingRight;

        unitLocX = 200;
        unitLocY = 10;

        bounds.setRect(0,651,50,50);

        panelWidth = p;
        setHealth(h);
        this.idleSta = idleSta;
        this.isButton = isButton;

        this.attack= new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            attack[i] = Sprite.getSprite(i, "katk", "katk.txt");
        }
        attackAnimation = new Animation(attack, frameDelay+5);
//
        walk = new BufferedImage[6];
        for (int i = 0; i < 6; i++) {
            walk[i] = Sprite.getSprite(i, "kwalk", "kwalk.txt");
        }
        walkAnimation = new Animation(walk, frameDelay);

        this.idle = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            this.idle[i] = Sprite.getSprite(i, "kidle", "kidle.txt");
        }
        idleAnimation = new Animation(idle, frameDelay+50);

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
}
