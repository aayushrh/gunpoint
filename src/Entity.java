import javax.swing.*;
import java.util.HashMap;

public abstract class Entity extends JLabel {
    public Vector2 pos;
    public int collLayer;
    public int collRad;
    public Entity(ImageIcon image, Vector2 entityPos, int collLayer, int collRad){
        super(image);
        this.pos = entityPos;
        this.collLayer = collLayer;
        this.collRad = collRad;
    }

    public abstract void update();
    public abstract void collide(Entity other);
}