import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    private int classn;
    private String className = "<Empty>";

    private double sped = 1.5;
    private boolean ability1 = false;
    private boolean ability2 = false;
    private double spread = 0;
    private double maxSpread = 0;
    private Vector2 dash = null;

    private Cooldown[] cd = {new Cooldown(10),new Cooldown(100), new Cooldown(200), new Cooldown(400)};
    //0 = atk, 1 = dash, 2 = 1st ability(uptime), 3 = 2nd ability(uptime).
    private double pv;
    private double dmg = 1;

    public Player(int classn) {
        super("images/machine_gun.png", new Vector2(400, 250), new int[]{1}, 25);
        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);
        inputs.put("Click", false);
        inputs.put("SPACE",false);
        inputs.put("Q",false);
        inputs.put("E",false);
        this.classn = classn;
        hp = 10;
        pv = 5;
        classSetup();
    }

    private void classSetup(){
        String[] classNames = {"Sniper","Machine Gun","Original","Ninja","Summoner"};
        className = classNames[classn];//probably display this on the top right or smth
        cd[1].cd = 0;
        switch(classn){
            case 0:
                cd[0].setCd(50);
                pv = 30;
                sped *= .5;
                dmg = 5;
                break;
            case 1:
                cd[0].setCd(5);
                dmg = .5;
                sped *= 2;
                maxSpread = 80;
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
        switch (classn){
            case 1:
                cd[0].multCD(.5);
                ability1 = true;
                cd[2].multCD(.5);
                cd[2].cd();
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
        switch (classn){
            case 0:
                Board.slow = .1;
                ability2 = true;
                cd[3].multCD(.25);
                cd[3].cd();
            case 1:
                sped *= .5;
                ability2 = true;
                cd[3].multCD(.25);
                cd[3].cd();
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
            /*if(classn != 0) {
                if(cd[0].cd()) {
                    Vector2 direction = Board.mousePos.sub(pos).normalize();
                    Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv);
                    Board.entities.add(bullet);
                }
            }*/
        }
    }

    public void release(){
        inputs.replace("Click", false);
    }
    public void update() {
        if(inputs.get("Click")){
            System.out.println("Sus");
            Vector2 direction = new Vector2(Math.toRadians(Math.toDegrees(Board.mousePos.sub(pos).getAngle())+Math.random()*spread-spread/2));
            switch (classn) {
                default:
                    if (cd[0].cd()) {
                        Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv, (int)dmg);
                        if (ability1) bullet.piercing = true;
                        Board.entities.add(bullet);
                        if(spread<maxSpread){
                            spread+=(maxSpread-spread)*.005;
                        }
                    }
            }
        }else{
            if(spread>0){
                spread*=.95;
                if(spread<maxSpread*.001){
                    spread = 0;
                }
            }
            cd[0].resetCooldown();
        }
        if(cd[2].getCd()){
            if(Board.level>7&&inputs.get("Q")) {
                ability2();
            }
        }else if(ability1){
            cd[2].resetCooldown();
            if(cd[2].getCd()){
                ability1=false;
                if(classn==1){
                    cd[0].multCD(.5);
                }
                cd[2].multCD(2);
                cd[2].cd();
            }
        }else{
            cd[2].resetCooldown();
        }

        if(cd[3].getCd()){
            if(Board.level>2&&inputs.get("E")) {
                ability1();
            }
        }else if(ability2){
            cd[3].resetCooldown();
            if(cd[3].getCd()){
                ability2=false;
                switch(classn) {
                    case 0:
                        Board.slow = 1;
                        break;
                    case 1:
                        sped *=2;
                        break;
                }
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
        if(inputs.get("SPACE")&&cd[1].getCd()){
            if(dash==null) dash = input.normalize().multiply(50);
            cd[1].cd();
        }else{
            cd[1].resetCooldown();
        }
//        if(inputs.get("Space")){
//
//        }
        input = input.normalize();
        if(dash!=null){
            velo = dash;
            dash = null;
        }else {
            velo = velo.add(input.multiply(sped * Board.slow));
        }
        velo = velo.multiply(0.9);
        pos = pos.add(velo);

        double angle = Math.toDegrees(Board.mousePos.sub(pos).getAngle()) + 90;
        loadImage("images/sniper.png", angle);
        image = scale(image, 0.5);

        //double h = image.getHeight()/Math.sin(Math.toRadians(angle));
        //double w = image.getWidth()/Math.cos(Math.toRadians(angle));
        //Vector2 offset = new Vector2(-(image.getWidth() - w)/2, -(image.getHeight() - h)/2);
        //pos = pos.add(offset);

        if(Board.outOfBounds(pos)){
            pos = pos.sub(velo);
        }

    }

    public void collide(Entity other){
        System.out.println("hit");
        if(!ability2||classn!=1) {
            hp--;
        }
    }

}
