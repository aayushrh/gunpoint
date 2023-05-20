public class Splash extends Enemy{
    Vector2 pP;
    Cooldown sc;
    boolean shooting = false;
    public Splash(Vector2 start, double speed, int cooldown, int splashCooldown){
        super(start, speed, cooldown);
        sc = new Cooldown(splashCooldown);
    }
    public void shoot(Player player){
        if(!shooting && cd.cd()){//won't run cd if shooting true cuz priority lmao
            pP = player.getPos();
            shooting = true;

            //Bullet bullet2 = new Bullet(pos, new Vector2(direction.getAngle()+0.349066), 1);
            //Board.entities.add(bullet2);
            //Bullet bullet3 = new Bullet(pos, new Vector2(direction.getAngle()-0.349066), 1);
            //Board.entities.add(bullet3);
        }

    }
    public void update(){
        move(300,350);
        if(shooting){
            if(sc.cd()){
                Bullet bullet = new Bullet(pP, new Vector2(0,0), new int[]{1});
                Board.entities.add(bullet);
                shooting = false;
            }
        }
    }
    public void collide(Entity other){

    }
}
