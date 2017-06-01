import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by home on 5/5/17.
 */
public abstract class GameObject {

    // hit box size

    protected boolean turr = false;

    protected Rectangle2D bounds = new Rectangle2D.Double();

    protected boolean facingRight = true;

    protected int myX = 240;
    protected int myY = 665;

    protected boolean intersectingAtk = false;

    protected int unitLocX;
    protected int unitLocY;

    protected int frameDelay = 8;

    protected boolean intersecting = false;
    protected boolean isButton = false;

    private int health;
    private Weapon weapon;
    private int price;
    protected int panelWidth;

    protected BufferedImage[] walk;
    protected BufferedImage[] attack;
    protected BufferedImage[] idle;
    protected Animation walkAnimation;
    protected Animation attackAnimation;
    protected Animation idleAnimation;

    public void setIntersecting(boolean b) {
        intersecting = b;
    }

    public void setIntersectingAtk(boolean b) {
        intersectingAtk = b;
    }

//    public boolean getIntersection() {
//        return intersecting;
//    }

//    public boolean isButton() {
//        return this.isButton;
//    }

    public void setPanelWidth(int p) {
        panelWidth = p;
    }

    public void setPrice(int p) {
        price = p;
    }

    public int getPrice() {
        return price;
    }

    public void setWeapon(String name, int damage) {
        weapon = new Weapon(name, damage);
    }

    public String getWeaponName() {
        return weapon.getName();
    }

    public int getWeaponDamage() {
        return weapon.getDamage();
    }

    public void setHealth(int h) {
        health = h;
    }

    public void loseHealth(int h) {
        health = health - h;
    }

    public int getHealth() {
        return health;
    }

//    public boolean interesecting(int x, int y) {
//        return bounds.intersects(x, y, 1, 1);
//    }
//
//    public boolean collision(GameObject o) {
//        return bounds.intersects(o.getBounds());
//    }

    public void attack(GameObject o) {
        o.loseHealth(weapon.getDamage());
    }

    public void walk() {
        myX += 3;
    }

    public Rectangle2D getBounds() {
        return bounds;
    }

    public int myX() {
        return myX;
    }

    public boolean isTurr(){
        return turr;
    }

    public void draw(Graphics g) {

        if (isButton) {
            // selection button
            g.drawImage(idleAnimation.getSprite(), panelWidth - unitLocX, unitLocY, 50, 50, null);
            bounds.setRect(panelWidth - unitLocX, unitLocY, 50, 50);

        } else if(!turr){
            //add && condition that if the one infront is facing right
            //if intersecting and front side is facing left(AI) then do attack animation
            if (intersectingAtk) {
                g.drawImage(attackAnimation.getSprite(), myX, myY, 50, 50, null);
//                g.drawRect(myX, myY, 50,50);
                bounds.setRect(myX, myY, 50, 50);
            }
            else if (intersecting) {
                g.drawImage(idleAnimation.getSprite(), myX, myY, 50, 50, null);
//                g.drawRect(myX, myY, 50,50);
                bounds.setRect(myX, myY, 50, 50);
            }
            //walk
            else if (!intersecting && myX + 80 < panelWidth) {
                g.drawImage(walkAnimation.getSprite(), myX, myY, 50, 50, null);
                walk();
//                g.drawRect(myX, myY, 50,50);
                bounds.setRect(myX, myY, 50, 50);
            }
            //if at the end attack
            else if (myX + 80 >= panelWidth) {
                g.drawImage(attackAnimation.getSprite(), myX, myY, 50, 50, null);
//                g.drawRect(myX, myY, 50,50);
                bounds.setRect(myX, myY, 50, 50);
            }

            //catch some exception
            else {
                System.out.println("##$% -- EXCEPTION!!!");
            }
        }
    }

}
