import java.awt.event.KeyEvent;
import java.util.HashMap;

public class ClassSelection extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    public int classn;
    private String[] classNames = {"sniper","machineGun","classic", "ninja", "summoner"};
    private Cooldown cd = null;

    public ClassSelection() {
        super("images/screens/sniperScreen.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("Left", false);
        inputs.put("Right", false);
        inputs.put("Enter", false);
        cd = new Cooldown(10);
        this.classn = 0;
        pos = new Vector2(image.getWidth()/4, image.getHeight()/4);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            inputs.replace("Left", true);
        }
        if (key == KeyEvent.VK_RIGHT) {
            inputs.replace("Right", true);
        }
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            inputs.replace("Left", false);
        }
        if (key == KeyEvent.VK_RIGHT) {
            inputs.replace("Right", false);
        }
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", false);
        }
    }

    public void update() {
        if(cd.cd()) {
            if (inputs.get("Left")) {
                classn = classn - 1;
                if (classn < 0) {
                    classn = classNames.length + classn;
                }
                loadImage("images/screens/" + classNames[classn] + "Screen.png");
            }
            if (inputs.get("Right")) {
                classn = (classn + 1) % classNames.length;
                loadImage("images/screens/" + classNames[classn] + "Screen.png");
            }
        }
        if(inputs.get("Enter")){
            hp = -1;
        }
    }

    public void collide(Entity other){

    }

}
