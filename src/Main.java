import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    // create a new JLabel object with an image
    private static Player player = new Player(new ImageIcon("Images/player.png"));
    private static Vector2 mousePos = new Vector2();
    private static boolean w_pressed = false;
    private static boolean a_pressed = false;
    private static boolean s_pressed = false;
    private static boolean d_pressed = false;

    public static void main(String[] args) {
        // create a JFrame object
        JFrame frame = new JFrame();

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
                    w_pressed = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    a_pressed = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    s_pressed = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    d_pressed = true;
                }
            }
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    w_pressed = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    a_pressed = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    s_pressed = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    d_pressed = false;
                }
            }
        });

        frame.add(player);

        frame.setVisible(true);

        // create a timer to update the player position every tick
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                player.updatePos(w_pressed, a_pressed, s_pressed, d_pressed);
            }
        }, 0, 10); // schedule the timer to run every 10 milliseconds

    }
}
