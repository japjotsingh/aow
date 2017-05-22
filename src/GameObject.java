import java.awt.*;

/**
 * Created by home on 5/5/17.
 */
public abstract class GameObject {

    // hit box size
    // health - Archers can hit turrets
    // attack mehthod
    // maybe a class of weapons
    // draw

    private int health;
    private String name;
    private Weapon weapon;
    private int price;

    public void setPrice(int p) {
        price = p;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
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

    public void attack(GameObject o) {
        o.loseHealth(weapon.getDamage());
    }

    ;

    public abstract void draw(Graphics g);

}
