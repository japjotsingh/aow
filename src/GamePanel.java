import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

/**
 * Created by home on 5/5/17.
 */
public class GamePanel extends JPanel implements MouseListener{
    Image bkgd;
    Graphics g;
    private int panelWidth;

    List<Melee> meleeList = new ArrayList<>();
    List<Archer> archerList = new ArrayList<>();
    List<Vehicle> vehicleList = new ArrayList<>();

    Timer t;
    JButton unit, melee, archer, vehicle, back;
    boolean mainScreen = true;

    public GamePanel(int w, int h) {
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.GREEN);

        initialize();
        getBkgd();
//        getInfo(a);
//        getInfo(b);

        t = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        // once you select difficulty
        t.start();
    }

    public void getInfo(GameObject o) {
        String[] info = new String[5];
        info[0] = "Name: " + o.getName();
        info[1] = "Health: " + String.valueOf(o.getHealth());
        info[2] = "Unit Cost: " + String.valueOf(o.getPrice());
        info[3] = "Weapon: " + o.getWeaponName();
        info[4] = "Weapon Dmg: " + String.valueOf(o.getWeaponDamage());

        for (String s : info) {
            System.out.println(s);
        }
    }

    // every time user clicks to get a new one, if the user has enough money they can buy a new one
    public void initialize() {

        addMouseListener(this);
        initButtons();
    }

    public void initButtons(){
        setLayout(null);

        back = new JButton("back");
        back.setBounds(500, 100,70,20);
        add(back);
        back.setVisible(true);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen = true;
            }
        });

        // in unit menu
        melee = new JButton("Club Man");
        melee.setBounds(500, 50,70, 20);
        add(melee);
        melee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //opens the unit menu
                //open panel to buy units
                //System.out.println("open units panel");

                // Make a new unit!
                //hashmap, with type and evoltion get info
                // get the name from the radio that is selected,
//                String t = "";
//                int hp = -1;// base this on the t from above and
//                int price = -1;// but based on the choices above
                //have the right unit costs but not hp and weapon damage
                Melee ma = new Melee("Club Man", 10, panelWidth);
                ma.setPrice(15);
                ma.setWeapon("Club", 5);
                meleeList.add(ma);
//                repaint();

                /*
                If clicks archer icon
                String t = "";
                int hp = -1;
                int price = -1;
                Archer aa = new Archer (t,hp);
                aa.setPrice(price);
                aa.setWeapon("Bow", 15);
                archerList.add(aa);
                 */
            }
        });

        archer = new JButton("Slingshot Man");
        archer.setBounds(600, 50,70, 20);
        add(archer);
        archer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String t = "";
//                int hp = -1;
//                int price = -1;
                Archer aa = new Archer ("Slingshot Man", 35);
                aa.setPrice(25);
                aa.setWeapon("Bow", 15);
                archerList.add(aa);
            }
        });

        vehicle = new JButton("Dino Rider");
        vehicle.setBounds(700, 50,70, 20);
        add(vehicle);
        vehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String t = "";
//                int hp = -1;
//                int price = -1;
                Vehicle v = new Vehicle ("Dino Rider", 35);
                v.setPrice(100);
                v.setWeapon("Spear", 15);
                vehicleList.add(v);
            }
        });

        unit = new JButton("UnitMenu");
        unit.setBounds(500,50, 70, 20);
        add(unit);
        unit.setVisible(true);
        unit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainScreen = false;
            }
        });
    }

    public void getBkgd() {
        // different depending on which evolution
        URL url = Melee.class.getResource("Images/prehistoric.png");
        try {
            bkgd = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {

        panelWidth = this.getWidth();

//        System.out.println(panelWidth);

        g.drawImage(bkgd, 0, 0, 1500, 800, null);

        //drawing menus
        g.fillRect(0, 0, 200, 100); // xp and gold
        g.fillRect(panelWidth - 300, 0, 300, 100); // main menu area
        g.fillRect(panelWidth - 200, 100, 200, 30); // evolution special
        g.fillRect(0, this.getHeight()-100, this.getWidth(), 100);// bottom walking platformer
        back.setLocation(panelWidth-300, 100);

        // a boolean switch for if we are in the unit menu or main menu, boolean switches with back button

        if(mainScreen) {
            melee.setVisible(false);
            archer.setVisible(false);
            vehicle.setVisible(false);

            unit.setLocation(panelWidth - 300, 30);
            unit.setVisible(true);
        }
        else{
            unit.setVisible(false);

            melee.setLocation(panelWidth-300, 30);
            archer.setLocation(panelWidth-200, 30);
            vehicle.setLocation(panelWidth-100, 30);

            melee.setVisible(true);
            archer.setVisible(true);
            vehicle.setVisible(true);
        }


        for(Melee m: this.meleeList){
            m.setPanelWidth(panelWidth);
            m.draw(g);
        }
//
//        for(Archer a: this.archerList){
//            a.draw(g);
//        }
//
//        for(Vehicle v: this.vehicleList){
//            v.draw(g);
//        }
    }

    public void mouseClicked(MouseEvent arg0) {
        System.out.println(arg0.getX() + " " + arg0.getY());
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {

    }

    public void mousePressed(MouseEvent arg0) {

    }

    public void mouseReleased(MouseEvent arg0) {

    }

    public void mouseDragged(MouseEvent arg0) {

    }

    public void mouseMoved(MouseEvent arg0) {

    }
}



