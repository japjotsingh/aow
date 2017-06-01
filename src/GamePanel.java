import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.awt.event.*;
import java.net.URL;
import java.util.Set;

/**
 * Created by home on 5/5/17.
 */
public class GamePanel extends JPanel implements MouseListener {
    Image bkgd, gold;
    Graphics g;
    private int panelWidth;
    protected boolean wentToAtk;

    // gold you start with
    protected int goldCount = 100;

    List<GameObject> allChars = new ArrayList<>();

    List<Jigglypuff> jigglypuffList = new ArrayList<>();
    List<Kirby> kirbyList = new ArrayList<>();
    List<Metaknight> metaList = new ArrayList<>();

    List<CAmerica> captList = new ArrayList<>();
    List<Hulk> hulkList = new ArrayList<>();

    Timer t, ts, capt, hulk;

    public GamePanel(int w, int h) {
        this.setPreferredSize(new Dimension(w, h));
        this.setBackground(Color.GREEN);

        initialize();
        getBkgd();

        ts = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get 1 gold every second
                goldCount++;
            }
        });
        ts.start();

        capt = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CAmerica ca = new CAmerica(100, panelWidth, false, false, false);
                ca.setPrice(15);
                ca.setWeapon("shield", 4);
                captList.add(ca);
                allChars.add(ca);
            }
        });

        hulk = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hulk h = new Hulk(105, panelWidth, false, false, false);
                h.setPrice(15);
                h.setWeapon("shield", 6);
                //next one does 8
                hulkList.add(h);
                allChars.add(h);
            }
        });

        t = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        // cue to select difficulty
        t.start();
        capt.start();
        hulk.start();
    }

    public void getInfo(GameObject o) {
        String[] info = new String[5];
        info[1] = "Health: " + String.valueOf(o.getHealth());
        info[2] = "Unit Cost: " + String.valueOf(o.getPrice());
        info[3] = "Weapon: " + o.getWeaponName();
        info[4] = "Weapon Dmg: " + String.valueOf(o.getWeaponDamage());

        for (String s : info) {
            System.out.println(s);
        }
    }

    public boolean buyUnit(int cost) {
        if (goldCount - cost >= 0) {
            goldCount = goldCount - cost;
            return true;
        }
        return false;
    }

    // every time user clicks to get a new one, if the user has enough money they can buy a new one
    public void initialize() {
        addMouseListener(this);
        initButtons();
    }

    //clickable units
    Jigglypuff idleJig;
    Kirby idleKirby;
    Metaknight idleMeta;

    public void initButtons() {
        setLayout(null);

        //clickable units
        idleJig = new Jigglypuff(10, panelWidth, true, true, false);
        jigglypuffList.add(idleJig);

        idleKirby = new Kirby(10, panelWidth, true, true, false);
        kirbyList.add(idleKirby);

        idleMeta = new Metaknight(10, panelWidth, true, true, false);
        metaList.add(idleMeta);
    }

    public void getBkgd() {
        // different depending on which evolution
        URL url = Jigglypuff.class.getResource("Images/prehistoric.png");
        URL url2 = Jigglypuff.class.getResource("Images/gold.png");
        try {
            bkgd = ImageIO.read(url);
            gold = ImageIO.read(url2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        panelWidth = this.getWidth();
        g.drawImage(bkgd, 0, 0, 1500, 800, null);


        //currency
        g.drawImage(gold, 10, 10, 25, 25, null);
        g.drawString(Integer.toString(goldCount), 35, 28);


        //drawing menus
        g.fillRect(panelWidth - 300, 0, 300, 100); // main menu area

        //can't use the fast loops because concurrent modification exception :(

        for (int i = 0; i < jigglypuffList.size(); i++) {
            Jigglypuff j = jigglypuffList.get(i);
            if (j.getHealth() <= 0) {
                jigglypuffList.remove(j);
            } else {
                j.setPanelWidth(panelWidth);
                j.draw(g);
            }
        }

        for (int i = 0; i < kirbyList.size(); i++) {
            Kirby k = kirbyList.get(i);
            if (k.getHealth() <= 0) {
                kirbyList.remove(k);
            } else {
                k.setPanelWidth(panelWidth);
                k.draw(g);
            }
        }

        for (int i = 0; i < metaList.size(); i++) {
            Metaknight k = metaList.get(i);
            if (k.getHealth() <= 0) {
                metaList.remove(k);
            } else {
                k.setPanelWidth(panelWidth);
                k.draw(g);
            }
        }

        for (int i = 0; i < captList.size(); i++) {
            CAmerica c = captList.get(i);
            if (c.getHealth() <= 0) {
                captList.remove(c);
            } else {
                c.setPanelWidth(panelWidth);
                c.draw(g);
            }
        }

        for (int i = 0; i < hulkList.size(); i++) {
            Hulk h = hulkList.get(i);
            if (h.getHealth() <= 0) {
                hulkList.remove(h);
            } else {
                h.setPanelWidth(panelWidth);
                h.draw(g);
            }
        }

        for (int i = 0; i < allChars.size(); i++) {
            GameObject o = allChars.get(i);
            if (o.getHealth() <= 0) {
                allChars.remove(o);
            }
        }

        for (int i = 0; i < allChars.size(); i++) {
            for (int j = i + 1; j < allChars.size(); j++) {
                if (j != i) {
                    GameObject f = allChars.get(i);
                    GameObject s = allChars.get(j);



//                    if (f.getBounds().intersects(s.getBounds()) && (f.facingRight == s.facingRight)) {
//                        if (s.facingRight == true) {
//                            if (f.getBounds().getX() < s.getBounds().getX()) {
//                                f.setIntersecting(true);
//                            } else {
//                                s.setIntersecting(true);
//                            }
//                        }
//
//                        else if (s.facingRight == false) {
//                            if (f.getBounds().getX() > s.getBounds().getX()) {
//                                f.setIntersecting(true);
//                            } else {
//                                s.setIntersecting(true);
//                            }
//                        }
//                    }
//                    else if(f.getBounds().intersects(s.getBounds()) && (f.facingRight != s.facingRight)){
//                        f.setIntersectingAtk(true);
//                        s.setIntersectingAtk(true);
//                        f.attack(s);
//                        s.attack(f);
//                    }
//                    else{
//                        for(GameObject o: allChars){
//                            o.setIntersecting(false);
//                            o.setIntersectingAtk(false);
//                        }
//                    }
                    if (f.getBounds().intersects(s.getBounds())) {
                        if(f.facingRight == s.facingRight){
                            s.setIntersecting(true);
                        }
                        else if(f.facingRight!=s.facingRight){
                            wentToAtk = true;
                            f.setIntersectingAtk(true);
                            s.setIntersectingAtk(true);
                            f.attack(s);
                            s.attack(f);
                        }
                        else{
                            if(wentToAtk){
                                s.setIntersectingAtk(false);
                                f.setIntersectingAtk(false);
                            }
                            s.setIntersecting(false);
                        }
                    }
//                        if (s.facingRight == f.facingRight) {
//                            s.setIntersecting(true);
//                        } else {
//                            f.setIntersectingAtk(true);
//                            s.setIntersectingAtk(true);
//                            f.attack(s);
//                            s.attack(f);
//                        }
//                    } else {
//                        s.setIntersecting(false);
//                        f.setIntersectingAtk(false);
//                        s.setIntersectingAtk(false);
//                    }
                }
            }
        }
        if(allChars.size() == 1){
            allChars.get(0).setIntersectingAtk(false);
        }
    }


    public void mouseClicked(MouseEvent e) {
//        System.out.println(e.getX() + " " + e.getY());
        Rectangle2D mouse = new Rectangle2D.Double();
        mouse.setRect(e.getX(), e.getY(), 1, 1);

        if(mouse.intersects(idleJig.getBounds())){
            Jigglypuff ma = new Jigglypuff(100, panelWidth, false, false, true);
            ma.setPrice(15);
            ma.setWeapon("hands", 1);
            if(buyUnit(ma.getPrice())) {
                jigglypuffList.add(1, ma);
                allChars.add(ma);
            }
        }

        if(mouse.intersects(idleKirby.getBounds())){
            Kirby ma = new Kirby(110, panelWidth, false, false, true);
            ma.setPrice(25);
            ma.setWeapon("hammer", 3);
            if (buyUnit(ma.getPrice())) {
                kirbyList.add(1, ma);
                allChars.add(ma);
            }
        }

        if(mouse.intersects(idleMeta.getBounds())){
            Metaknight m = new Metaknight(120,panelWidth,false,false,true);
            m.setPrice(35);
            m.setWeapon("blades", 5);
            if (buyUnit(m.getPrice())) {
                metaList.add(1, m);
                allChars.add(m);
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent arg0) {

    }

    public void mousePressed(MouseEvent arg0) {

    }

    public void mouseReleased(MouseEvent arg0) {

    }
}



