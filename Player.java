import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Player extends Entity{
    public HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    public int classn;
    private String className = "<Empty>";

    public double sped = 1.5;
    public boolean ability1 = false;
    public boolean ability2 = false;
    private double spread = 0;
    private double maxSpread = 0;
    public Vector2 dash = null;

    public Cooldown[] cd = {new Cooldown(10),new Cooldown(100), new Cooldown(200), new Cooldown(400)};
    //0 = atk, 1 = dash, 2 = 1st ability(uptime), 3 = 2nd ability(uptime).
    private double pv;
    private double dmg = 1;
    private int gun = 0;
    private int maxGun = 1;
    public double angle;
    public int swing;

    public Player(int classn) {
        super("images/players/ninja.png", new Vector2(400, 250), new int[]{1}, 25);
        this.classn = classn;
        classSetup();
        System.out.println(className);
        loadImage("images/players/" + className + ".png");
        image = scale(image, 0.75);
        inputs.put("W", false);
        inputs.put("A", false);
        inputs.put("S", false);
        inputs.put("D", false);
        inputs.put("Click", false);
        inputs.put("SPACE",false);
        inputs.put("Q",false);
        inputs.put("E",false);
        hp = 10;
    }

    private void classSetup(){
        String[] classNames = {"sniper","machine_gun","classic","ninja","summoner"};
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
                dmg = 1;
                sped *= 2;
                pv = 10;
                maxSpread = 80;
                break;
            case 2:
                pv = 10;
                cd[1].setCd(100);
                cd[2].setCd(100);
                break;
            case 3:
                Board.entities.add(new NinjaSwing());
                cd[0].setCd(13);
                //cd[0].setCd(1);
                cd[1].setCd(0);
                cd[2].setCd(80);
                angle = 40;
                break;
            case 4:
                cd[0].setCd(-1);
                break;
        }
        loadImage("images/players/" + className + ".png");
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
                gun++;
                gun = gun%maxGun;
                cd[2].cd();
                switch (gun){
                    case 0:
                        cd[0].setCd(10);
                    case 1:
                        cd[0].setCd(40);
                    case 2:
                        cd[0].setCd(120);
                }
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
                break;
            case 1:
                sped *= .5;
                ability2 = true;
                cd[3].multCD(.25);
                cd[3].cd();
                break;
            case 2:
                gun--;
                if(gun<0){
                    gun+=maxGun;
                }
                gun = gun%maxGun;
                cd[3].cd();
                switch (gun){
                    case 0:
                        cd[0].setCd(10);
                    case 1:
                        cd[0].setCd(40);
                    case 2:
                        cd[0].setCd(120);
                }
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
                    Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv, (int)dmg);
                    Board.entities.add(bullet);
                }
            }*/
        }
    }

    public void release(){
        inputs.replace("Click", false);
    }
    public void update() {
        if(maxGun<3&&classn==2){
            if(Board.level>0){
                maxGun = 3;
            } else if (Board.level>2) {
                maxGun = 2;
            }
        }
        if(inputs.get("Click")){
            Vector2 direction = new Vector2(Math.toRadians(Math.toDegrees(Board.mousePos.sub(pos).getAngle())+Math.random()*spread-spread/2));
            switch (classn) {
                case 2:
                    if(cd[0].cd()){
                        switch(gun){
                            case 0:
                                Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv, (int)dmg);
                                Board.entities.add(bullet);
                                break;
                            case 1:
                                Bullet bullet1 = new Bullet(pos, direction, new int[]{2}, pv, (int)dmg);
                                Board.entities.add(bullet1);
                                Bullet bullet2 = new Bullet(pos, new Vector2(Board.mousePos.sub(pos).getAngle()+0.349066), new int[]{2}, pv, (int)dmg);
                                Board.entities.add(bullet2);
                                Bullet bullet3 = new Bullet(pos, new Vector2(Board.mousePos.sub(pos).getAngle()-0.349066), new int[]{2}, pv, (int)dmg);
                                Board.entities.add(bullet3);
                                break;
                            case 2:
                                Boomerang boomerang = new Boomerang(pos,pv);
                                Board.entities.add(boomerang);
                                break;
                        }
                    }
                    break;
                case 3:
                    if(cd[0].cd()){
                        swing = 5;
                    }
                default:
                    if (cd[0].cd()) {
                        Bullet bullet = new Bullet(pos, direction, new int[]{2}, pv, (int)dmg);
                        if (ability1&&classn==0) bullet.piercing = true;
                        Board.entities.add(bullet);
                        if(spread<maxSpread){
                            spread+=(maxSpread-spread)*.005;
                        }
                    }
                    break;
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
            if((maxGun>1||Board.level>7)&&inputs.get("Q")) {
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
            if((maxGun>0||Board.level>2)&&inputs.get("E")) {
                ability1();
            }
        }else if(ability2){
            cd[3].resetCooldown();
            if(cd[3].getCd()){
                ability2=false;
                for(Entity e: Board.entities){
                    e.velo = e.velo.multiply(1/Board.slow);
                }
                switch(classn) {
                    case 0:
                        Board.slow = 1;
                        break;
                    case 1:
                        sped *=2;
                        break;
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
        loadImage("images/players/" + className + ".png", angle);
        image = scale(image, 0.5);

        //double h = image.getHeight()/Math.sin(Math.toRadians(angle));
        //double w = image.getWidth()/Math.cos(Math.toRadians(angle));
        //Vector2 offset = new Vector2(-(image.getWidth() - w)/2, -(image.getHeight() - h)/2);
        //pos = pos.add(offset);

        if(Board.outOfBoundsX(pos)){
            pos = pos.sub(new Vector2(velo.x,0));
        }
        if(Board.outOfBoundsY(pos)){
            pos = pos.sub(new Vector2(0,velo.y));
        }

    }

    public void collide(Entity other){
        System.out.println("hit");
        if((!ability2||classn!=1)&&!(other instanceof NinjaSwing)) {
            hp--;
        }
    }

}
