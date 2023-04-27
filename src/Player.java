import javax.swing.*;

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

    public void updatePos(boolean w_pressed, boolean a_pressed, boolean s_pressed, boolean d_pressed){
        Vector2 input_vector = new Vector2();
        if(w_pressed){
            input_vector.y -= 1;
        }
        if(a_pressed){
            input_vector.x -= 1;
        }
        if(s_pressed){
            input_vector.y += 1;
        }
        if(d_pressed){
            input_vector.x += 1;
        }
        velocity = velocity.add(input_vector.multiply(sped));
        velocity = velocity.multiply(0.9);
        playerPos = playerPos.add(velocity);
        super.setLocation((int)playerPos.x, (int)playerPos.y);
    }
}
