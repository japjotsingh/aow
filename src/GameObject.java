import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by home on 5/5/17.
 */
public abstract class GameObject{

    // hit box size

    protected Rectangle2D bounds = new Rectangle2D.Double();

    protected boolean facingRight = true;

    protected int myX = 0;
    protected int myY = 651;

    protected int unitLocX;
    protected int unitLocY;

    protected int frameDelay = 8;
    protected boolean walkMode = true;
    protected boolean walks = true;
    protected boolean idleSta = false;

    protected boolean isNowIdle = false;

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

    public boolean isButton(){
        return this.isButton;
    }

    public boolean isIdle(){
        return idleSta;
    }

    public void setIsNowIdle(boolean b){
        isNowIdle = b;
    }

    public void setPanelWidth(int p){
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

    public boolean interesecting(int x, int y){
        return bounds.intersects(x,y,1,1);
    }

    public boolean collision(GameObject o){
        return bounds.intersects(o.getBounds());
    }

    public void attack(GameObject o) {
        o.loseHealth(weapon.getDamage());
    }

    public void walk(){
        myX+=3;
    }

    public Rectangle2D getBounds(){
        return bounds;
    }

    public int myX(){
        return myX;
    }

    public void draw(Graphics g){


        if(idleSta){
            g.drawImage(idleAnimation.getSprite(), panelWidth-unitLocX, unitLocY, 30,30, null);
            bounds.setRect(panelWidth-300, 10, 30,30);
        }

        else {
            //modify so that it only goes into attack mode once objects hit box collides with another object
            if (walkMode) {
                g.drawImage(walkAnimation.getSprite(), myX, myY, 50, 50, null);
                walk();
                bounds.setRect(myX,myY,50,50);

                //if hitbox collides
                if (myX+80>panelWidth) {
                    walkMode = false;
                    isNowIdle = true;
                }

            } else {

                if(isNowIdle){
                    System.out.println("idle anim");
                    walks = false;
                    g.drawImage(idleAnimation.getSprite(), myX, myY, 50,50, null);
                    bounds.setRect(myX, myY, 50,50);
                }
                else {
                    walks = false;
                    g.drawImage(attackAnimation.getSprite(), myX, myY, 50, 50, null);
                    bounds.setRect(myX, myY, 50, 50);
                }
            }
        }
    };

}
