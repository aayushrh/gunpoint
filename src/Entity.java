import javax.swing.*;
import java.util.HashMap;

public abstract class Entity extends JLabel {
    public Vector2 pos;
    public Entity(ImageIcon image, Vector2 entityPos){
        super(image);
        this.pos = entityPos;
    }

    public abstract void update();
}