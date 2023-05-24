import java.util.Arrays;

public class Bullet extends Entity{
    public Vector2 direction;
    public boolean piercing = false;

    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer, double bulletsped) {
        super("images/player_bullet.png", pos, collLayer, 10);
        image = scale(image, 0.5);
        this.projectile = true;
        this.direction = direction.normalize();
        velo = this.direction.multiply(bulletsped);
        hp = 1;
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
        this.direction = direction.normalize();
        velo = this.direction.multiply(bulletsped);
        hp = damage;
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
        if(other instanceof Player){
            if(((Player)other).ability2&&((Player)other).classn==1){
                return;
            }
            hp=-1;
        }else if(!other.projectile){
            double t = other.hp;
            other.hp-=hp;
            if(!piercing) {
                hp -= t;
            }
        }
    }
}
