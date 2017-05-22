import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;

/**
 * Created by home on 5/9/17.
 */
public class Melee extends GameObject {

    private Image image;
    private Animation animation;
    private BufferedImage[] idleSprite;
    private BufferedImage[] walkingSprite;
    private boolean facingRight;


    public Melee(String n, int h) {
        setName(n);
        setHealth(h);

        //make it so that by name you can get the respective pic
        getImage();

        try {
            idleSprite = new BufferedImage[2];
            walkingSprite = new BufferedImage[10];

            idleSprite[0] = Sprite.getSprite(0, 0);
            idleSprite[1] = Sprite.getSprite(1, 0);

            for (int i = 0; i < 9; i++) {
                walkingSprite[i] = Sprite.getSprite(i, 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        facingRight = true;
    }

    private void getImage() {
        //different depending on which evolution you are on
        URL url = Melee.class.getResource("Images/club.png");
        try {
            image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
    }


    public void draw(Graphics g) {

        int w = 800;
        int h = 800;

        Random r = new Random();
        int x = r.nextInt(((w - 100) - 100) + 100);
        int y = r.nextInt(((w - 100) - 100) + 100);

        g.drawImage(image, 10, 300, 100, 100, null);

    }

}
