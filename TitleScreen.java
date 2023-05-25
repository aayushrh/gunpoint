import java.awt.event.KeyEvent;
import java.util.HashMap;

public class TitleScreen extends Entity{
    private HashMap<String, Boolean> inputs = new HashMap<String, Boolean>();
    public Entity next = null;

    public TitleScreen() {
        super("images/screens/titleScreen.png", new Vector2(0, 0), new int[]{1}, 25);
        inputs.put("Enter", false);
        inputs.put("Click", false);
        pos = new Vector2(image.getWidth()/4, image.getHeight()/4);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            System.out.println("e");
            inputs.replace("Enter", true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            inputs.replace("Enter", false);
        }
    }

    public void click(){
        inputs.replace("Click", true);
    }

    public void release(){
        inputs.replace("Click", false);
    }

    public void update() {
        if(inputs.get("Enter")){
            hp = -1;
            next = new ClassSelection();
        }
        if (inputs.get("Click")){
            if((Board.mousePos.x < 516 && Board.mousePos.x > 369) && (Board.mousePos.y < 350 && Board.mousePos.y > 281)){
                hp = -1;
                next = new ClassSelection();
            }
            if((Board.mousePos.x < 544 && Board.mousePos.x > 351) && (Board.mousePos.y < 459 && Board.mousePos.y > 406)){
                hp = -1;
                next = new Credits();
            }
        }
    }

    public void collide(Entity other){

    }

}
