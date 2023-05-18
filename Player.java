import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Player extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    private double sped = 3.0;
    public Player() {
        super("images/player.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);
        inputs.put("Click", false);
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            inputs.replace("W", true);
        }
        if (key == KeyEvent.VK_D) {
            inputs.replace("D", true);
        }
        if (key == KeyEvent.VK_S) {
            inputs.replace("S", true);
        }
        if (key == KeyEvent.VK_A) {
            inputs.replace("A", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            inputs.replace("W", false);
        }
        if (key == KeyEvent.VK_D) {
            inputs.replace("D", false);
        }
        if (key == KeyEvent.VK_S) {
            inputs.replace("S", false);
        }
        if (key == KeyEvent.VK_A) {
            inputs.replace("A", false);
        }
    }

    public void click(boolean continuous){
        if(continuous) {
            inputs.replace("Click", true);
        }else{
            Vector2 direction = Board.mousePos.sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, 2);
            Board.entities.add(bullet);
        }
    }

    public void release(){
        inputs.replace("Click", false);
    }

    public void update() {

        if(inputs.get("Click")){
            Vector2 direction = Board.mousePos.sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, 2);
            Board.entities.add(bullet);
        }

        Vector2 input = new Vector2();
        if(inputs.get("W")){
            input = input.add(new Vector2(0, -1));
        }
        if(inputs.get("A")){
            input = input.add(new Vector2(-1, 0));
        }
        if(inputs.get("S")){
            input = input.add(new Vector2(0, 1));
        }
        if(inputs.get("D")){
            input = input.add(new Vector2(1, 0));
        }
        input = input.normalize();
        velo = velo.add(input.multiply(sped));
        velo = velo.multiply(0.9);
        pos = pos.add(velo);
    }

    public void collide(Entity other){
        System.out.println("hit");
    }

}
