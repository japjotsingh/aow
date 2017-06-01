import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/**
 * Created by home on 5/9/17.
 */
public class CAmerica extends GameObject {

    Timer t;

    public CAmerica(int h, int p, boolean idleSta, boolean isButton, boolean facingRight){

        this.facingRight = facingRight;
        panelWidth = p;

        this.myX = 1330;

        //where it is drawn
        bounds.setRect(myX,651,50,50);

        setHealth(h);
        this.isButton = isButton;

        //attack Animation created
        this.attack= new BufferedImage[8];
        for (int i = 0; i < 8; i++) {
            attack[i] = Sprite.getSprite(i, "caatk", "caatk.txt");
        }
        attackAnimation = new Animation(attack, frameDelay+12);

        //walk Animation created
        walk = new BufferedImage[12];
        for (int i = 0; i < 12; i++) {
            walk[i] = Sprite.getSprite(i, "cawalk", "cawalk.txt");
        }
        walkAnimation = new Animation(walk, frameDelay+2);


        //idle Animation created
        this.idle = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            this.idle[i] = Sprite.getSprite(i, "caidle", "caidle.txt");
        }
        idleAnimation = new Animation(idle, frameDelay+20);

        t = new javax.swing.Timer(frameDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cycle throgh idle if it is a button, intersecting, or at the end (turret)
                if(isButton){
                    idleAnimation.update();
                }
                else if(intersectingAtk){
                    attackAnimation.update();
                }
                else if(intersecting){
                    idleAnimation.update();
                }
                //if not intersecting anything and not at the end walk
                else if(!intersecting && myX-80>0){
                    walkAnimation.update();
                }
                else if(myX-80<=0){
                    attackAnimation.update();
                }
                //if intersecting && facing right attack

                else{
                    System.out.println("##$% -- Animation Update Exception!");
                }
            }
        });

        t.start();
    }

    @Override
    public void walk(){
        myX-=3;
    }

    @Override
    public void draw(Graphics g){

        //won't ever be button cuz this is AI side
        if(isButton){
            // selection button
            g.drawImage(idleAnimation.getSprite(), panelWidth-unitLocX, unitLocY, 30,30, null);
            bounds.setRect(panelWidth-300, 10, 30,30);
        }

        else{
            //add && condition that if the one infront is facing right
            if (intersectingAtk) {
                g.drawImage(attackAnimation.getSprite(), myX, myY, 50, 50, null);
//                g.drawRect(myX, myY, 30,30);
                bounds.setRect(myX, myY, 50, 50);
            }
            else if(intersecting){
                g.drawImage(idleAnimation.getSprite(), myX, myY, 50,50, null);
//                g.drawRect(myX, myY, 30,30);
                bounds.setRect(myX,myY,50,50);
            }
            //walk
            else if(!intersecting && myX-80>0){
                g.drawImage(walkAnimation.getSprite(),myX,myY,50,50,null);
                walk();
//                g.drawRect(myX, myY, 30,30);
                bounds.setRect(myX,myY,50,50);
            }
            //if at the end attack
            else if(myX-80<=0){
                g.drawImage(attackAnimation.getSprite(), myX, myY, 50,50, null);
//                g.drawRect(myX, myY, 30,30);
                bounds.setRect(myX,myY,50,50);
            }
            //if intersecting and front side is facing left(AI) then do attack animation

            //catch some exception
            else{
                System.out.println("##$% -- EXCEPTION!!!");
            }
        }
    }
}
