import java.util.ArrayList;

public class SplashSummon extends Entity{

    private Cooldown start = null;
    private Cooldown end = null;
    private boolean started = false;

    public SplashSummon(Vector2 pos, int cooldown) {
        super("images/green-circle.png", pos, new int[]{}, 10);
        this.projectile = true;
        start = new Cooldown(cooldown);
    }

    public void update(){
        if(start.cd()){
            started = true;
            super.loadImage("images/coin.png");
            ArrayList<Integer> i = new ArrayList<Integer>();
            i.add(1);
            super.collLayer = i;
            end = new Cooldown(20);
        }
        if(started){
            if(end.cd()){
                death = true;
            }
        }
    }

    public void collide(Entity other){
        if(!other.projectile && started){
            death = true;
        }
    }
}
