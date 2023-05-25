import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener{
    private final int DELAY = 25;
    private static final long serialVersionUID = 490905409104883233L;

    private Timer timer;
    public static Player player;
    public static ArrayList<Entity> entities;
    public static Vector2 mousePos = new Vector2();
    public static double slow = 1;
    public static int pClassn = 0;

    public static int level;
    public static int civiliansC;
    private static int civiliansN;
    private JLabel l1;
    private ClassSelection cselect;
    private TitleScreen title;
    private Credits credits;

    public Board() {
        setPreferredSize(new Dimension(50 * 18, 50 * 12));

        setBackground(new Color(0, 0, 0));

        //player = new Player(0);
        entities = new ArrayList<Entity>();
        title = new TitleScreen();
        entities.add(title);
        //entities.add(player);
        civiliansN = 1;
        civiliansC = 0;
        level = 12;
        l1 =new JLabel("" + level);
        l1.setBounds(50,500, 100,30);
        l1.setVisible(false);
        l1.setFont(new Font("Lato", Font.BOLD, 50));
        l1.setForeground(new Color(255, 255, 255));
        add(l1);
        timer = new Timer(DELAY, this);
        timer.start();

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }
            public void mousePressed(MouseEvent e){
                if(player != null) {
                    player.click(true);
                }else if (title != null){
                    title.click();
                }
            }
            public void mouseReleased(MouseEvent e){
                if(player != null) {
                    player.release();
                }else if (title != null){
                    title.release();
                }
            }
            public void mouseEntered(MouseEvent e){

            }
            public void mouseExited(MouseEvent e){

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                mousePos = new Vector2(e.getX(), e.getY());
            }
            public void mouseDragged(MouseEvent e){
                mousePos = new Vector2(e.getX(), e.getY());
                player.click(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).update();
        }

        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i).projectile){
                if((outOfBounds(entities.get(i).pos))){
                    entities.get(i).hp = -1;
                }
            }
        }

        for(int i = 0; i < entities.size(); i++){
            for(int j = i + 1; j < entities.size(); j++){
                for (int layeri : entities.get(j).collLayer) {
                    for (int layerj : entities.get(i).collLayer) {
                        if (layeri == layerj && entities.get(i).getPos().distTo(entities.get(j).getPos()) < entities.get(i).collRad + entities.get(j).collRad) {
                            entities.get(i).collide(entities.get(j));
                            entities.get(j).collide(entities.get(i));
                        }
                    }
                }
            }
        }

        for(int i = 0; i < entities.size(); i++){
            if(entities.get(i).hp<=0){
                if(entities.get(i) instanceof ClassSelection){
                    pClassn = ((ClassSelection)entities.get(i)).classn;
                    player = new Player(pClassn);
                    entities.add(player);
                    cselect = null;
                    l1.setVisible(true);
                }
                if(entities.get(i) instanceof TitleScreen){
                    if(title.next instanceof ClassSelection) {
                        cselect = (ClassSelection)title.next;
                        entities.add(cselect);
                        title = null;
                    }else{
                        credits = (Credits)title.next;
                        entities.add(credits);
                        title = null;
                    }
                }
                if(entities.get(i) instanceof Credits){
                    title = new TitleScreen();
                    entities.add(title);
                    credits = null;
                }
                if(entities.get(i) instanceof Player){
                    player = null;
                    entities = new ArrayList<Entity>();
                    title = new TitleScreen();
                    entities.add(title);
                    entities.add(title);
                    level = 1;
                    civiliansC = 0;
                    civiliansN = 1;
                    l1.setVisible(false);
                }
                entities.remove(i);
                i--;
            }
        }
        if(player != null) {
            int num = (int) (Math.random() * 100 / slow);
            if (num == 1) {
                int x_val = (int) (Math.random() * 910) - 20;
                Civilian civ = new Civilian(new Vector2(x_val, -20), 5);
                entities.add(civ);
            }

            if (level <= 1) {
                spawn(new Regular(new Vector2(0, -20), 2, 25), 90);
            } else if (level <= 4){
                int rand2 = (int) (Math.random() * 3 / slow);
                if (rand2 <= 1) {
                    //spawn(new Regular(new Vector2(0, -20), 2, 25), 90);
                    spawn(new Regular(new Vector2(0,-20),1,25),90);
                } else {
                    spawn(new Spiral(new Vector2(0, -20), 2, 100, 25, 10,1), 120);
                }
            } else if (level <= 6){
                int rand2 = (int) (Math.random() * 8 / slow);
                if (rand2 <= 1) {
                    spawn(new Regular(new Vector2(0, -20), 2, 25), 90);
                } else if (rand2 <= 4) {
                    spawn(new Spiral(new Vector2(0, -20), 2, 100, 25, 10,1), 120);
                } else{
                    spawn(new Shield(new Vector2(0, -20), 2, 25, 2, 3, 3), 150);
                }
            } else if (level <= 8){
                int rand2 = (int) (Math.random() * 12 / slow);
                if (rand2 <= 1) {
                    spawn(new Regular(new Vector2(0, -20), 2, 25), 90);
                } else if (rand2 <= 4) {
                    spawn(new Spiral(new Vector2(0, -20), 2, 100, 25, 10,1), 120);
                } else if (rand2 <= 7){
                    spawn(new Shield(new Vector2(0, -20), 2, 25, 2, 3, 3), 150);
                } else{
                    spawn(new Splash(new Vector2(0, -20), 2, 50, 50), 175);
                }
            }  else{
                int rand2 = (int) (Math.random() * 16 / slow);
                if (rand2 <= 1) {
                    spawn(new Regular(new Vector2(0, -20), 2, 25), 90);
                } else if (rand2 <= 4) {
                    spawn(new Spiral(new Vector2(0, -20), 2, 100, 25, 10,1), 120);
                } else if (rand2 <= 7){
                    spawn(new Shield(new Vector2(0, -20), 2, 25, 2, 3, 3), 150);
                } else if (rand2 <= 11){
                    spawn(new Splash(new Vector2(0, -20), 2, 50, 50), 175);
                } else{
                    spawn(new Rocket(new Vector2(0, -20), 1, 50, 3), 200);
                }
            }


            if (civiliansC >= civiliansN) {
                civiliansC -= civiliansN;
                level += 1;
                civiliansN += 1;
            }

            l1.setText("" + level);
        }

        repaint();
    }
    public void spawn(Entity wut, int chance){
        int rand = (int) (Math.random() * 50 / slow);
        if(rand == 1) {
            int x_val = (int) (Math.random() * 910) - 20;
            wut.pos.x = x_val;
            entities.add(wut);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Entity en : entities) {
            boolean draw = true;
            if(en instanceof ShieldSummon){
                if(((ShieldSummon)en).followEntity.pos.equals(new Vector2(0, -20))){
                    draw = false;
                }
            }
            en.draw(g, this);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        if(title != null){
            title.keyPressed(e);
        }
        if(cselect != null){
            cselect.keyPressed(e);
        }
        if(player != null) {
            player.keyPressed(e);
        }
        if(credits != null){
            credits.keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
        if(title != null){
            title.keyReleased(e);
        }
        if(cselect != null){
            cselect.keyReleased(e);
        }
        if(player != null) {
            player.keyReleased(e);
        }
        if(credits != null){
            credits.keyReleased(e);
        }
    }
    public static boolean outOfBoundsX(Vector2 pos){
        return (pos.x < 40 || pos.x > 890);
    }
    public static boolean outOfBoundsY(Vector2 pos){
        return (pos.y < -20 || pos.y > 570);
    }
    public static boolean outOfBounds(Vector2 pos){
        return  outOfBoundsX(pos)||outOfBoundsY(pos);
    }

    /*private ArrayList<Coin> populateCoins() {
        ArrayList<Coin> coinList = new ArrayList<>();
        Random rand = new Random();

        // create the given number of coins in random positions on the board.
        // note that there is not check here to prevent two coins from occupying the same
        // spot, nor to prevent coins from spawning in the same spot as the player
        for (int i = 0; i < NUM_COINS; i++) {
            int coinX = rand.nextInt(COLUMNS);
            int coinY = rand.nextInt(ROWS);
            coinList.add(new Coin(coinX, coinY));
        }

        return coinList;
    }*/
}
