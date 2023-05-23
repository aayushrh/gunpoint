import java.util.ArrayList;

public class SplashSummon extends Entity{

    private Cooldown start = null;
    private boolean started = false;

    public SplashSummon(Vector2 pos, int cooldown) {
        super("images/potion.png", pos, new int[]{}, 10);
        image = scale(image, 0.5);
        this.projectile = true;
        start = new Cooldown(cooldown);
        hp = 20;
    }

    public void update(){
        if(start.cd()){
            started = true;
            super.loadImage("images/break.png");
            ArrayList<Integer> i = new ArrayList<Integer>();
            i.add(1);
            super.collLayer = i;
        }
        if(started){
            hp--;
        }
    }

    public void collide(Entity other){
        if(!other.projectile && started){
            hp = -1;
        }
    }
}
