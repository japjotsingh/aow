import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Created by home on 5/9/17.
 */
public class Archer extends GameObject {

    private Image image;

    public Archer(String n, int h){
        setName(n);
        setHealth(h);

        //make it so that by name you can get the respective pic
        getImage();
    }

    private void getImage() {
        //changes depending on evoltion
        URL url = Melee.class.getResource("Images/sling.png");
        try {
            image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics g){
        int w = 800;
        int h = 800;

        Random r = new Random();
        int x = r.nextInt(((w-100)-100)+100);
        int y = r.nextInt(((w-100)-100)+100);

        g.drawImage(image, x,y,100,100, null);
    }

}
