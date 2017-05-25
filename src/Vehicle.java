import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Random;

/**
 * Created by home on 5/9/17.
 */
public class Vehicle extends GameObject {

    private Image image;

    public Vehicle(String n, int h){
        setHealth(h);

        //make it so that by name you can get the respective pic
        getImage();
    }

    private void getImage() {
        //different depending on which evolution you are on
        URL url = Jigglypuff.class.getResource("Images/dino.png");
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
