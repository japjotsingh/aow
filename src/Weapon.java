/**
 * Created by home on 5/9/17.
 */
public class Weapon{

    private String name;
    private int damage;

    public Weapon(String s, int d){
        name = s;
        damage = d;
    }

    public int getDamage(){
        return damage;
    }

    public String getName(){
        return name;
    }
}
