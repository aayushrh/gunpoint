import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Credits extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();

    public Credits() {
        super("images/screens/credits.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("Shift", false);
        pos = new Vector2(image.getWidth()/4, image.getHeight()/4);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            System.out.println("e");
            inputs.replace("Shift", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT) {
            inputs.replace("Shift", false);
        }
    }

    public void update() {
        if(inputs.get("Shift")){
            hp = -1;
        }
    }

    public void collide(Entity other){

    }

}
