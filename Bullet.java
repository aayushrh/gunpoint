public class Bullet extends Entity{
    private double bullet_speed;
    public Vector2 direction;

    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer, double bulletsped) {
        super("images/coin.png", pos, collLayer, 10);
        this.projectile = true;
        bullet_speed = bulletsped;
        this.direction = direction.normalize();
        velo = this.direction.multiply(bullet_speed);
    }
    public Bullet(Vector2 pos, Vector2 direction, int[] collLayer) {
        this(pos,direction,collLayer,10.0);
    }

    public void update(){
        pos = pos.add(velo);
    }

    public void collide(Entity other){
        if(!other.projectile) {
            death = true;
        }
    }
}
