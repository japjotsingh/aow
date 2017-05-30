import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Jigglypuff extends GameObject {

    Timer t;

    public Jigglypuff(int h, int p, boolean idleSta, boolean isButton, boolean facingRight){

        this.facingRight = facingRight;

        unitLocX = 300;
        unitLocY = 10;

        bounds.setRect(0,651,50,50);

        panelWidth = p;
        setHealth(h);
//        this.idleSta = idleSta;
        this.isButton = isButton;

        this.attack= new BufferedImage[8];
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
//                if(idleSta){
//                    idleAnimation.update();
//                }
//                else {
//                    // depends on hitbox
//                    if (walks) {
//                        walkAnimation.update();
//                    } else {
//                        attackAnimation.update();
//                    }
//                }
            }
        });

        t.start();
    }

}
