import java.util.Arrays;

public class Bullet extends Entity{
    private double bullet_speed;
    public Vector2 direction;
    public boolean piercing = false;
    public int damage;

    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer, double bulletsped) {
        super("images/player_bullet.png", pos, collLayer, 10);
        image = scale(image, 0.5);
        this.projectile = true;
        bullet_speed = bulletsped;
        this.direction = direction.normalize();
        velo = this.direction.multiply(bullet_speed);
        this.damage = 1;
        if(Arrays.equals(collLayer, new int[]{1})){
            loadImage("images/enemy_bullet.png");
            //image = scale(image, 2);
        }else if(Arrays.equals(collLayer, new int[]{2})){
            loadImage("images/player_bullet.png");
            //image = scale(image, 2);
        }

    }

    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer, double bulletsped, int damage) {
        super("images/coin.png", pos, collLayer, 10);
        image = scale(image, 0.5);
        this.projectile = true;
        bullet_speed = bulletsped;
        this.direction = direction.normalize();
        velo = this.direction.multiply(bullet_speed);
        //this.damage = damage;
        this.damage = 5;
        if(Arrays.equals(collLayer, new int[]{1})){
            loadImage("images/enemy_bullet.png");
            //image = scale(image, 2);
        }else if(Arrays.equals(collLayer, new int[]{2})){
            loadImage("images/player_bullet.png");
            //image = scale(image, 2);
        }
    }

    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer) {
        this(pos,direction,collLayer,15.0);
    }

    public void update(){
        pos = pos.add(velo.multiply(Board.slow));
    }

    public void collide(Entity other){
        if(!other.projectile&&!piercing) {
            hp = -1;
        }
    }
}
