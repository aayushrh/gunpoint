import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.Timer;

public class Main {
    // create a new JLabel object with an image
    private static Player player = new Player(new ImageIcon("Images/player.png"));
    private static Vector2 mousePos = new Vector2();

    private static HashMap<String, Boolean> inputs = new HashMap<>();

    public static void main(String[] args) {
        // create a JFrame object
        JFrame frame = new JFrame();

        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);

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
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    inputs.replace("W", true);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    inputs.replace("A", true);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    inputs.replace("S", true);
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    inputs.replace("D", true);
                }
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    inputs.replace("W", false);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    inputs.replace("A", false);
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    inputs.replace("S", false);
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    inputs.replace("D", false);
                }
            }
        });

        frame.add(player);

        frame.setVisible(true);

        // create a timer to update the player position every tick
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.updatePos(inputs);
            }
        }, 0, 10); // schedule the timer to run every 10 milliseconds

    }
}
