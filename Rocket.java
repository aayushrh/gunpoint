public class Rocket extends Enemy{
    private int bullets;
    public Rocket(Vector2 start, double speed, int cooldown, int bullets){
        super(start, speed, cooldown);
        this.bullets = bullets;
    }
    public void shoot(Player player){
        if(cd.cd()){
            for(int i = 0; i < bullets; i++){
                Vector2 offset = new Vector2(Math.random() * 200, Math.random() * 200);
                RocketBullet rocket = new RocketBullet(pos, pos.add(offset), 3, Math.PI/36);
                Board.entities.add(rocket);
            }
        }
    }
    public void update(){
        move(300,350);
    }
    public void collide(Entity other){
        
    }
}
