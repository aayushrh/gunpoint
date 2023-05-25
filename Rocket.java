public class Rocket extends Enemy{
    private int bullets;
    public Rocket(Vector2 start, double speed, int cooldown, int bullets){
        super("images/rocket.png", start, speed, cooldown, 5);
        image = scale(image, 0.75);
        this.bullets = bullets;
    }
    public void shoot(Player player){
        if(cd.cd()){
            for(int i = 0; i < bullets; i++){
                Vector2 offset = new Vector2(Math.random() * 100, Math.random() * 100);
                int num = (int)(Math.random() * 4);
                if(num == 1){
                    offset.x *= -1;
                }else if (num == 2){
                    offset.y *= -1;
                }else if (num == 3){
                    offset.x *= -1;
                    offset.y *= -1;
                }
                RocketBullet rocket = new RocketBullet(pos, pos.add(offset), 2, Math.PI/180);
                Board.entities.add(rocket);
            }
        }
    }
    public void update(){
        move(300,350);
    }
}
