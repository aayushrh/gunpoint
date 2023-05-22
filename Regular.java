public class Regular extends Enemy{
    public Regular(Vector2 start, double speed, int cooldown){
        super("images/enemy.png", start, speed, cooldown);
        image = scale(image, 0.5);
    }
    public void shoot(Player player){
        if(cd.cd()){
            Vector2 direction = player.getPos().sub(pos).normalize();
            Bullet bullet = new Bullet(pos, direction, new int[]{1});
            Board.entities.add(bullet);
            //Bullet bullet2 = new Bullet(pos, new Vector2(direction.getAngle()+0.349066), 1);
            //Board.entities.add(bullet2);
            //Bullet bullet3 = new Bullet(pos, new Vector2(direction.getAngle()-0.349066), 1);
            //Board.entities.add(bullet3);
        }
    }
    public void update(){
        move(300,350);
    }
    public void collide(Entity other){
        
    }
}
