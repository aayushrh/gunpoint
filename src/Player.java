import javax.swing.*;
import java.util.HashMap;

public class Player extends JLabel {
    private Vector2 playerPos;
    private Vector2 velocity;
    private int sped;

    public Player(ImageIcon image){
        super(image);
        playerPos = new Vector2();
        velocity = new Vector2();
        sped = 2;
    }

    public void updatePos(HashMap<String, Boolean> inputs){
        Vector2 input_vector = new Vector2();
        if(inputs.get("W")){
            input_vector.y -= 1;
        }
        if(inputs.get("A")){
            input_vector.x -= 1;
        }
        if(inputs.get("S")){
            input_vector.y += 1;
        }
        if(inputs.get("D")){
            input_vector.x += 1;
        }
        velocity = velocity.add(input_vector.multiply(sped));
        velocity = velocity.multiply(0.9);
        playerPos = playerPos.add(velocity);
        super.setLocation((int)playerPos.x, (int)playerPos.y);
    }
}
