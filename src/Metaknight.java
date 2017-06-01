import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Metaknight extends GameObject {

    Timer t;

    public Metaknight(int h, int p, boolean idleSta, boolean isButton, boolean facingRight) {

        this.facingRight = facingRight;

        unitLocX = 90;
        unitLocY = 10;

        //where it is drawn
        bounds.setRect(0, 651, 50, 50);

        panelWidth = p;
        setHealth(h);
        this.isButton = isButton;

        //attack Animation created
        this.attack = new BufferedImage[5];
        for (int i = 0; i < 5; i++) {
            attack[i] = Sprite.getSprite(i, "matk", "matk.txt");
        }
        attackAnimation = new Animation(attack, frameDelay + 5);

        //walk Animation created
        walk = new BufferedImage[10];
        for (int i = 0; i < 10; i++) {
            walk[i] = Sprite.getSprite(i, "mwalk", "mwalk.txt");
        }
        walkAnimation = new Animation(walk, frameDelay);


        //idle Animation created
        this.idle = new BufferedImage[9];
        for (int i = 0; i < 9; i++) {
            this.idle[i] = Sprite.getSprite(i, "midle", "midle.txt");
        }
        idleAnimation = new Animation(idle, frameDelay + 20);

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cycle throgh idle if it is a button, intersecting, or at the end (turret)
                if (isButton) {
                    idleAnimation.update();
                }
                else if(intersectingAtk){
                    attackAnimation.update();
                }
                else if (intersecting) {
                    idleAnimation.update();
                }
                else if (!intersecting && myX + 80 < panelWidth) {
                    walkAnimation.update();
                }
                else if (myX + 80 >= panelWidth) {
                    attackAnimation.update();
                }
                //if intersecting && facing left attack

                else {
                    System.out.println("##$% -- Animation Update Exception!");
                }
            }
        });

        t.start();
    }
}
