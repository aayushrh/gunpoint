import javax.swing.*;
public class Enemy extends Entity{
    int type;
    double distance;
    int speed = 10;
    double shootDirection = 0;
    double attackVelo;
    public Enemy(ImageIcon image, int t, double dist) {
        super(image, new Vector2(), 2, 50);
        type = t;
        distance = dist;
    }
    public void shoot(){
        Main.entities.add(new Bullet(new ImageIcon("Images/player.png"), attackVelo,shootDirection,true,false));
    }
    public void update(){//the distance range is arbitrary probably change them lol
        double t = Math.pow(distance,2) - Main.player.pos.sub(pos).getMag();
        if(t>160){//too far
            pos = pos.add(pos.sub(Main.player.pos).normalize().multiply(speed));
        }
        else if(t<160){//too close
            pos = pos.add(Main.player.pos.sub(pos).normalize().multiply(speed));
        }else{//within range of player, randomly moves
            pos = pos.add(new Vector2(Math.random()*360).multiply(speed));

        }

        switch(type){

            case 1://normal enemy
                shootDirection = pos.sub(Main.player.pos).getAngle();
                shoot();
                break;
            case 2:
                shootDirection +=0.5;
                shootDirection = shootDirection % 360;
                shoot();
                break;
        }

        super.setLocation((int)pos.x, (int)pos.y);

        super.setLocation((int)pos.x, (int)pos.y);
    }
    public void collide(Entity other){
        //*dies*
    }
}
