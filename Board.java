import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, KeyListener {
    private final int DELAY = 25;
    private static final long serialVersionUID = 490905409104883233L;
    private Timer timer;
    public static Player player;
    public static ArrayList<Entity> entities;
    public static Vector2 mousePos;

    public Board() {
        setPreferredSize(new Dimension(50 * 18, 50 * 12));

        setBackground(new Color(232, 232, 232));

        player = new Player();
        entities = new ArrayList<Entity>();
        entities.add(player);
        entities.add(new Shield(new Vector2(0, 10), 2, 10, 2));
        entities.add(new Shield(new Vector2(10, 10), 2, 10, 2));
        timer = new Timer(DELAY, this);
        timer.start();

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }
            public void mousePressed(MouseEvent e){
                player.click(true);
            }
            public void mouseReleased(MouseEvent e){
                player.release();
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
            if(entities.get(i).death){
                entities.remove(i);
                i--;
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Entity en : entities) {
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
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
        player.keyReleased(e);
    }

    /*private void drawScore(Graphics g) {
        // set the text to be displayed
        String text = "$" + player.getScore();
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING,
            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(
            RenderingHints.KEY_FRACTIONALMETRICS,
            RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        // set the text color and font
        g2d.setColor(new Color(30, 201, 139));
        g2d.setFont(new Font("Lato", Font.BOLD, 25));
        // draw the score in the bottom center of the screen
        // https://stackoverflow.com/a/27740330/4655368
        FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
        // the text will be contained within this rectangle.
        // here I've sized it to be the entire bottom row of board tiles
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
        // determine the x coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // determine the y coordinate for the text
        // (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // draw the string
        g2d.drawString(text, x, y);
    }*/

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
