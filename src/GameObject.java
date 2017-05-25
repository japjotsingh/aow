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

    protected int unitLocX;
    protected int unitLocY;

    protected int frameDelay = 8;
    protected boolean walkMode = true;
    protected boolean walks = true;
    protected boolean idleSta = false;
    protected boolean isButton = false;
    protected int walking = 0;
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

    public void attack(GameObject o) {
        o.loseHealth(weapon.getDamage());
    }

    public void draw(Graphics g){
        if(idleSta){
            g.drawImage(idleAnimation.getSprite(), panelWidth-unitLocX, unitLocY, 30,30, null);
            bounds.setRect(panelWidth-300, 10, 30,30);
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
    };

}
