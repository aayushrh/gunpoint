public class Bullet extends Entity{
    public static final double BULLET_SPEED = 10.0;
    public Vector2 direction;
    public Bullet(Vector2 pos, Vector2 direction) {
        // load the assets
        super("images/coin.png", pos, 2, 10);
        this.direction = direction;
        velo = this.direction.multiply(BULLET_SPEED);
    }

    public void update(){
        pos = pos.add(velo);
    }

    public void collide(Entity other){

    }
}