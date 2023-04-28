import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.Timer;

public class Main {
    // create a new JLabel object with an image
    public static Player player = new Player(new ImageIcon("Images/player.png"));
    //public static Enemy tempenemy = new Enemy(new ImageIcon("Images/Player.png"),1,10);
    private static Vector2 mousePos = new Vector2();

    public static HashMap<String, Boolean> inputs = new HashMap<>();
    public static ArrayList<Entity> entities = new ArrayList<Entity>();

    public static void main(String[] args) {
        // create a JFrame object
        JFrame frame = new JFrame();

        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);

        entities.add(player);

        // set the title of the window
        frame.setTitle("Gunpoint But Better");

        // set the size of the window
        frame.setSize(800, 600);

        // set the background color to white
        frame.getContentPane().setBackground(java.awt.Color.BLACK);

        // add a mouse motion listener to the frame
        frame.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(MouseEvent e) {
                // set the location of the player label to the current mouse position
                mousePos = new Vector2(e.getX(), e.getY());
            }
            public void mouseDragged(MouseEvent e) {
                // do nothing
            }
        });

        // add a key listener to the frame
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                // do nothing
            }
            public void keyPressed(KeyEvent e) {
                int a = e.getKeyCode();
                for(int i = 65;i<91;i++){
                    if(a==i){
                        inputs.replace(Character.toString((char) i),true);
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
                int a = e.getKeyCode();
                for(int i = 65;i<91;i++){
                    if(a==i){
                        inputs.replace(Character.toString((char) i),false);
                    }
                }
            }
        });
//        for(Entity e: entities){
//            frame.add(e);
//        }
        frame.add(player);

        frame.setVisible(true);

        frame.setFocusable(true);

        // create a timer to update the player position every tick
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                for(Entity e : entities){
                    for(Entity r : entities){
                        if(!e.equals(r)){
                            if(e.collLayer == r.collLayer && (e.pos.sub(r.pos).getMag() < e.collRad + r.collRad)){
                                e.collide(r);
                                r.collide(e);
                            }
                        }
                    }

                }
                player.update();
            }
        }, 0, 10); // schedule the timer to run every 10 milliseconds

    }
}
