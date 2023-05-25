import java.util.ArrayList;

public class RocketBullet extends Entity{
    private Vector2 posToGo;
    private int speed;
    private boolean started = false;
    private Cooldown start;
    private double angle;
    private double turnSpeed;
    public RocketBullet(Vector2 pos, Vector2 posToGo, int speed, double turnSpeed) {
        super("images/rocketBullet.png", pos, new int[]{1}, 5);
        this.projectile = true;
        this.posToGo = posToGo;
        this.speed = speed;
        start = new Cooldown(30);
        this.turnSpeed = turnSpeed;
    }

    public void update(){
        if(start.cd()){
            started = true;
            angle = Board.player.pos.sub(pos).getAngle();
        }else{
            if(!(pos.distTo(posToGo) < 1)){
                Vector2 direction = posToGo.sub(pos).normalize();
                velo = velo.add(direction);
                velo = velo.multiply(0.9);
                pos = pos.add(velo);
            }
        }
        if(started){
            if(angle < Board.player.pos.sub(pos).getAngle()){
                angle += turnSpeed;
            } else if(angle > Board.player.pos.sub(pos).getAngle()){
                angle -= turnSpeed;
            }
            Vector2 input = new Vector2(angle);
            velo = velo.add(input.multiply(speed));
            velo = velo.multiply(0.9);
            pos = pos.add(velo);
        }
        loadImage("images/rocketBullet.png", Math.toDegrees(angle) + 90);
        image = scale(image, 0.5);
    }

    public void collide(Entity other){
        if(!other.projectile){
            hp = 0;
        }
    }
}
