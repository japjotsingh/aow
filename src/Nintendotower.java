import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by home on 6/1/17.
 */
public class Nintendotower extends GameObject{
    Image bkgd;

    public Nintendotower(int h) {

        facingRight = true;
        turr = true;


        //where it is drawn
        bounds.setRect(0, 151, 400, 600);

        setHealth(h);
        openImage();
    }

    public void openImage(){
        URL url = Jigglypuff.class.getResource("Images/wii.png");
        try {
            bkgd = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(bkgd, -100, 211, 400,600, null);
        g.drawString(Integer.toString(getHealth()) + " hp", 30, 271);
        if(getHealth()<=0){
            //spam them ;)
            JOptionPane.showMessageDialog(null, "You have been defeated by Marvel!");
        }

    }
}
