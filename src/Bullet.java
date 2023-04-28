import javax.swing.*;

public class Bullet extends Entity{
    Vector2 velo;
    boolean pierce;
    boolean enemy;
    public Bullet(ImageIcon image, double v, double angle, boolean enemy, boolean p){
        super(image, new Vector2(), 1, 50);
        velo = new Vector2(angle).multiply(v);
        pierce = p;
        this.enemy = enemy;
        if(!enemy){
            collLayer = 2;
        }
    }
    public void update(){
        pos = pos.add(velo);
    }
    public void collide(Entity other){
        if(!pierce){
        }
    }
}
