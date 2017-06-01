import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by home on 5/9/17.
 */
public class Turret extends GameObject {

    Image bkgd;

    public Turret(int h) {

        facingRight = false;
        turr = true;


        //where it is drawn
        bounds.setRect(1330, 151, 400, 600);

        setHealth(h);
        openImage();

    }

    public void openImage(){
        URL url = Jigglypuff.class.getResource("Images/stark.png");
        try {
            bkgd = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(bkgd, 1200, 151, 400,600, null);
        g.drawString(Integer.toString(getHealth()) + " hp", 1270, 171);

        if(getHealth()<=0){
            //spam them ;)
            JOptionPane.showMessageDialog(null, "CONGRATULATIONS! You have defeated Marvel!");
        }

    }
}