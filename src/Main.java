import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by home on 5/5/17.
 */
public class Main {

    //hello

    int w = 500;
    int h = 800;

    public static void main(String[] args){
        try {
            new Main().start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void start() throws Exception{
        JFrame frame = new JFrame("Age of War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel(w, h), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
