public class Civilian extends Entity{
    private int speed;
    public Civilian(Vector2 start, int speed){
        super("images/villager.png", start, new int[]{2}, 25);
        hp = 1;
        image = scale(image, 0.5);
        this.speed = speed;
    }
    public void update(){
        pos.y += speed*Board.slow;
        if(pos.y > 570){
            hp = -1;
            Board.civiliansC += 1;
        }
    }
    public void collide(Entity other){
        if(other.projectile){
            hp = -1;
        }
    }
}
