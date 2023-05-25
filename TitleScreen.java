import java.awt.event.KeyEvent;
import java.util.HashMap;

public class TitleScreen extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();

    public TitleScreen() {
        super("images/screens/titleScreen.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("Enter", false);
        pos = new Vector2(image.getWidth()/4, image.getHeight()/4);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", false);
        }
    }

    public void update() {
        if(inputs.get("Enter")){
            hp = -1;
        }
    }

    public void collide(Entity other){

    }

}
