public class Civilian extends Entity{
    private int speed;
    public Civilian(Vector2 start, int speed){
        super("images/villager.png", start, new int[]{2}, 25);
        image = scale(image, 0.5);
        this.speed = speed;
    }
    public void update(){
        pos.y += speed;
        if(pos.y > 570){
            death = true;
            Board.civiliansC += 1;
        }
    }
    public void collide(Entity other){
        if(other.projectile){
            death = true;
        }
    }
}
