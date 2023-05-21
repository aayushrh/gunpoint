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
    private int clas;
    private double sped = 3.0;
    private Cooldown cd;

    public Player(int c) {
        super("images/machine_gun.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);
        inputs.put("Click", false);
        clas = c;
        classSetup();
        cd = new Cooldown(5);
    }

    private void classSetup(){

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
        if (key == KeyEvent.VK_SPACE) {
            inputs.replace("SPACE", false);
        }
    }

    public void click(boolean continuous){
        if(continuous) {
            inputs.replace("Click", true);
        }else{
            Vector2 direction = Board.mousePos.sub(pos).normalize();
        }
    }

    public void release(){
        inputs.replace("Click", false);
        cd.resetCooldown();
    }
    public void update() {
        if(inputs.get("Click")){
            Vector2 direction = Board.mousePos.sub(pos).normalize();
            if(cd.cd()) {
                Bullet bullet = new Bullet(pos, direction, new int[]{2});
                Board.entities.add(bullet);
            }
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
//        if(inputs.get("Space")){
//
//        }
        input = input.normalize();
        velo = velo.add(input.multiply(sped));
        velo = velo.multiply(0.9);
        pos = pos.add(velo);

        double angle = Math.toDegrees(Board.mousePos.sub(pos).getAngle()) + 90;
        loadImage("images/machine_gun.png", angle);
        image = scale(image, 0.5);
        //double h = image.getHeight()/Math.sin(Math.toRadians(angle));
        //double w = image.getWidth()/Math.cos(Math.toRadians(angle));
        //Vector2 offset = new Vector2(-(image.getWidth() - w)/2, -(image.getHeight() - h)/2);
        //pos = pos.add(offset);
    }

    public void collide(Entity other){
        System.out.println("hit");
    }

}
