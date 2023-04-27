import javax.swing.*;
import java.util.HashMap;

public class Player extends Entity {
    private Vector2 velocity;
    private int sped;

    public Player(ImageIcon image){
        super(image, new Vector2(), 1, 50);
        velocity = new Vector2();
        sped = 2;
    }

    public void updatePos(){
        Vector2 input_vector = new Vector2();
        if(Main.inputs.get("W")){
            input_vector.y -= 1;
        }
        if(Main.inputs.get("A")){
            input_vector.x -= 1;
        }
        if(Main.inputs.get("S")){
            input_vector.y += 1;
        }
        if(Main.inputs.get("D")){
            input_vector.x += 1;
        }
        input_vector = input_vector.normalize();
        velocity = velocity.add(input_vector.multiply(sped));
        velocity = velocity.multiply(0.9);
        pos = (pos.add(velocity));
        super.setLocation((int)pos.x, (int)pos.y);
    }

    public void update(){
        updatePos();
    }

    public void collide(Entity other){

    }
}
