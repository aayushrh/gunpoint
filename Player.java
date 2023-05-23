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
    private int Class;
    private String className = "<Empty>";

    private double sped = 3.0;
    private boolean ability1 = false;
    private boolean ability2 = false;


    private Cooldown[] cd = {new Cooldown(10),new Cooldown(100), new Cooldown(200), new Cooldown(400)};
    //0 = atk, 1 = dash, 2 = 1st ability(uptime), 3 = 2nd ability(uptime);
    private double pv;
    private double dmg = 1;

    public Player(int Class) {
        super("images/machine_gun.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);
        inputs.put("Click", false);
        inputs.put("SPACE",false);
        inputs.put("Q",false);
        inputs.put("E",false);
        this.Class = Class;
        pv = 5;
        classSetup();
    }

    private void classSetup(){
        String[] classNames = {"Sniper","Machine Gun","Original","Ninja","Summoner"};
        className = classNames[Class];//probably display this on the top right or smth
        switch(Class){
            case 0:
                cd[0].setCd(50);
                pv = 30;
                sped = 1.5;
                dmg = 5;
                break;
            case 1:
                cd[0].setCd(5);
                dmg = .5;
                break;
            case 2:
                break;
            case 3:
                cd[0].setCd(20);
                break;
            case 4:
                cd[0].setCd(-1);
                break;

        }
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
        if (key == KeyEvent.VK_SPACE) {
            inputs.replace("SPACE", true);
        }
        if (key == KeyEvent.VK_Q) {
            inputs.replace("Q", true);
        }
        if (key == KeyEvent.VK_E) {
            inputs.replace("E", true);
        }
    }
    public void ability1(){
        switch (Class){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                ability1 = true;
                cd[2].multCD(.5);
                cd[2].cd();
                break;

        }
    }
    public void ability2(){
        switch (Class){
            case 0:
                Board.slow = .1;
                cd[3].multCD(.25);
                cd[3].cd();
                ability2 = true;
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
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
        if (key == KeyEvent.VK_Q) {
            inputs.replace("Q", false);
        }
        if (key == KeyEvent.VK_E) {
            inputs.replace("E", false);
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
    }
    public void update() {
        if(inputs.get("Click")){
            Vector2 direction = Board.mousePos.sub(pos).normalize();
            if(cd[0].cd()) {
                Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv);
                if(ability1) bullet.piercing = true;
                Board.entities.add(bullet);
            }
        }else{
            cd[0].resetCooldown();
        }
        if(cd[2].getCd()){
            if(inputs.get("Q")) {
                ability1();
            }
        }else if(ability1){
            cd[2].resetCooldown();
            if(cd[2].getCd()){
                ability1=false;
                cd[2].multCD(2);
                cd[2].cd();
            }
        }else{
            cd[2].resetCooldown();
        }

        if(cd[3].getCd()){
            if(inputs.get("E")) {
                ability2();
            }
        }else if(ability2){
            cd[3].resetCooldown();
            if(cd[3].getCd()){
                ability2=false;
                Board.slow = 1;
                for(Entity e: Board.entities){
                    e.velo.multiply(Board.slow);
                }
                cd[3].multCD(4);
                cd[3].cd();
            }
        }else{
            cd[3].resetCooldown();
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
        velo = velo.add(input.multiply(sped*Board.slow));
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
